package com.example.demo.web.rest;

import com.example.demo.service.domain.Appointment;
import com.example.demo.service.dto.AppointmentDto;
import com.example.demo.service.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@RestController
@RequestMapping("/rest/appointments")
public class AppointmentController {
  @Autowired
  IAppointmentService appointmentService;

  @GetMapping("/paged")
  public Page<Appointment> getAppointments(Pageable pageable) {
    return appointmentService.getAppointments(pageable);
  }

  @GetMapping("/{doctorName}")
  public Page<Appointment> getAppointmentsForDoctor(@PathVariable("doctorName") String doctorName, Pageable pageable) {
    return appointmentService.getAppointmentsForDoctor(doctorName, pageable);
  }

  @PostMapping
  @ResponseBody
  public ResponseEntity<String> addAppointment(@RequestBody AppointmentDto appointmentDto) {
    //TODO add validation
    //TODO check duplicates by petName & date
    return appointmentService.add(appointmentDto)
        .map(value -> new ResponseEntity<>(value + " added", HttpStatus.CREATED))
        .orElseGet(() -> new ResponseEntity<>("Error when adding new appointment", HttpStatus.BAD_REQUEST));
  }

  @PutMapping
  @ResponseBody
  public ResponseEntity<String> modifyAppointment(@RequestBody AppointmentDto appointmentDto) {
        return appointmentService.modifyAppointment(appointmentDto)
        .map(value -> new ResponseEntity<>(value + " modified.", HttpStatus.CREATED))
        .orElseGet(() -> new ResponseEntity<>("Error when modifying appointment", HttpStatus.BAD_REQUEST));
  }
}
