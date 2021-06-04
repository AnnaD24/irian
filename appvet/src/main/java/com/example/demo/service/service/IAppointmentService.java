package com.example.demo.service.service;

import com.example.demo.service.dto.AppointmentDto;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.Optional;


public interface IAppointmentService {
  Page<AppointmentDto> getAppointments(Integer pageNo, Integer pageSize, String sortBy, String direction);

  Optional<AppointmentDto> add(AppointmentDto appointmentDto);

  Page<AppointmentDto> getAppointmentsForDoctor(String doctorName, Integer pageNo, Integer pageSize, String sortBy, String direction);

  Optional<AppointmentDto> modifyAppointment(AppointmentDto appointmentDto);

  Collection<String> getDoctors();
}
