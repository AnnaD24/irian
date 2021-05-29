package com.example.demo.service.service.impl;

import com.example.demo.service.domain.Appointment;
import com.example.demo.service.repository.AppointmentPagedRepository;
import com.example.demo.service.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentService implements IAppointmentService {
 @Autowired
 private AppointmentPagedRepository appointmentRepository;

  @Override
  public Page<Appointment> getAppointments(Pageable pageable) {
    return appointmentRepository.findAll(pageable);
  }

 @Override
 public Optional<Appointment> add(Appointment appointment) {
  return Optional.of(appointmentRepository.save(appointment));
 }
}
