package com.example.demo.web.rest;

import com.example.demo.service.domain.Appointment;
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

  @PostMapping
  @ResponseBody
  public ResponseEntity<String> addAppointment(@RequestBody Appointment appointment) {
    return appointmentService.add(appointment)
        .map(value -> new ResponseEntity<>(value + " added", HttpStatus.CREATED))
        .orElseGet(() -> new ResponseEntity<>("Error when adding new appointment", HttpStatus.BAD_REQUEST));
  }

}
