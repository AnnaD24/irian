package com.example.demo.service.service;

import com.example.demo.service.domain.Appointment;
import com.example.demo.service.dto.AppointmentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface IAppointmentService {
  Page<Appointment> getAppointments(Pageable pageable);

  Optional<AppointmentDto> add(AppointmentDto appointmentDto);
}
