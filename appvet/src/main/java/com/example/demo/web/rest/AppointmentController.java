package com.example.demo.web.rest;

import com.example.demo.service.domain.Appointment;
import com.example.demo.service.dto.AppointmentDto;
import com.example.demo.service.service.AppointmentValidator;
import com.example.demo.service.service.IAppointmentService;
import com.example.demo.service.service.MedicalServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


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
  public ResponseEntity<?> addAppointment(@RequestBody AppointmentDto appointmentDto, BindingResult result) {
    appointmentValidator.validate(appointmentDto, result);

    if(result.hasErrors()) {
      return ResponseEntity.badRequest()
          .body(result.getAllErrors());
    }

    return appointmentService.add(appointmentDto)
        .map(value -> new ResponseEntity<>(value + " added", HttpStatus.CREATED))
        .orElseGet(() -> new ResponseEntity<>("Error when adding new appointment", HttpStatus.BAD_REQUEST));
  }

  @PutMapping
  @ResponseBody
  public ResponseEntity<?> modifyAppointment(@RequestBody AppointmentDto appointmentDto, BindingResult result) {
    appointmentValidator.validate(appointmentDto, result);

    if(result.hasErrors()) {
      return ResponseEntity.badRequest()
          .body(result.getAllErrors());
    }

    return appointmentService.modifyAppointment(appointmentDto)
        .map(value -> new ResponseEntity<>(value + " modified.", HttpStatus.CREATED))
        .orElseGet(() -> new ResponseEntity<>("Error when modifying appointment", HttpStatus.BAD_REQUEST));
  }
}
