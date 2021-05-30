package com.example.demo.service.service;

import com.example.demo.service.domain.Appointment;
import com.example.demo.service.dto.AppointmentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.Optional;


public interface IAppointmentService {
  Page<Appointment> getAppointments(Pageable pageable);

  Optional<AppointmentDto> add(AppointmentDto appointmentDto);

  Page<Appointment> getAppointmentsForDoctor(String doctorName, Pageable pageable);

  Optional<Appointment> getAppointment(String id);

  Optional<AppointmentDto> modifyAppointment(AppointmentDto appointmentDto);
}
