package com.example.demo.web.rest;

import com.example.demo.service.dto.AppointmentDto;
import com.example.demo.service.service.AppointmentValidator;
import com.example.demo.service.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;


@EnableSwagger2
@RestController
@RequestMapping("/rest/appointments")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AppointmentController {
  @Autowired
  IAppointmentService appointmentService;

  @Autowired
  AppointmentValidator appointmentValidator;

  @GetMapping
  public Page<AppointmentDto> getAppointments(
      @RequestParam(defaultValue = "0") Integer pageNo,
      @RequestParam(defaultValue = "5") Integer pageSize,
      @RequestParam(defaultValue = "dateTime") String sortBy,
      @RequestParam(defaultValue = "DESC") String direction
  ) {
    return appointmentService.getAppointments(pageNo, pageSize, sortBy, direction);
  }

  @GetMapping("/doctors")
  public Collection<String> getDoctors() {
    return this.appointmentService.getDoctors();
  }


  @GetMapping("/{doctorName}")
  public Page<AppointmentDto> getAppointmentsForDoctor(
      @RequestParam(defaultValue = "0") Integer pageNo,
      @RequestParam(defaultValue = "5") Integer pageSize,
      @RequestParam(defaultValue = "dateTime") String sortBy,
      @RequestParam(defaultValue = "DESC") String direction,
      @PathVariable("doctorName") String doctorName
  ) {
    return appointmentService.getAppointmentsForDoctor(doctorName, pageNo, pageSize, sortBy, direction);
  }

  @PostMapping
  @ResponseBody
  public ResponseEntity<?> addAppointment(@RequestBody AppointmentDto appointmentDto, BindingResult result) throws URISyntaxException {
    appointmentValidator.validate(appointmentDto, result);

    if (result.hasErrors()) {
      return ResponseEntity.badRequest()
          .body(result.getAllErrors());
    }
    Optional<AppointmentDto> newAppointment = appointmentService.add(appointmentDto);

    if (newAppointment.isEmpty())
      return new ResponseEntity<>("Error when persisting new appointment.", HttpStatus.INTERNAL_SERVER_ERROR);

    return ResponseEntity.created(new URI("/rest/appointments"))
        .body(newAppointment);
  }

  @PutMapping
  @ResponseBody
  public ResponseEntity<?> modifyAppointment(@RequestBody AppointmentDto appointmentDto, BindingResult result) throws URISyntaxException {
    appointmentValidator.validate(appointmentDto, result);

    if (result.hasErrors()) {
      return ResponseEntity.badRequest()
          .body(result.getAllErrors());
    }

    Optional<AppointmentDto> updatedAppointment = appointmentService.modifyAppointment(appointmentDto);

    if (updatedAppointment.isEmpty())
      return new ResponseEntity<>("Error when persisting modified appointment.", HttpStatus.INTERNAL_SERVER_ERROR);

    return ResponseEntity.created(new URI("/rest/appointments"))
        .body(updatedAppointment);
  }
}
