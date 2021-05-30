package com.example.demo.service.service.impl;

import com.example.demo.service.domain.Appointment;
import com.example.demo.service.domain.AppointmentStatus;
import com.example.demo.service.domain.ServiceType;
import com.example.demo.service.dto.AppointmentDto;
import com.example.demo.service.dto.ServiceTypeDto;
import com.example.demo.service.repository.AppointmentPagedRepository;
import com.example.demo.service.repository.ServiceTypeRepository;
import com.example.demo.service.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService implements IAppointmentService {
  @Autowired
  private ServiceTypeService serviceTypeService;
 @Autowired
 private AppointmentPagedRepository appointmentRepository;
  @Autowired
  private ServiceTypeRepository serviceTypeRepository;
  @Override
  public Page<Appointment> getAppointments(Pageable pageable) {
    return appointmentRepository.findAll(pageable);
  }

 @Override
 public Optional<AppointmentDto> add(AppointmentDto appointmentDto) {

    Appointment appointment = new Appointment(
        appointmentDto.petName,
        appointmentDto.diagnostic,
        AppointmentStatus.CREATED,
        appointmentDto.doctorName,
        appointmentDto.dateTime
    );
    //TODO: check service exists
    for(ServiceType serviceType: appointmentDto.services) {
      Optional<ServiceType> foundService = serviceTypeRepository.findByName(serviceType.getName());
      appointment.addService(foundService.get());
    }

    return Optional.of(mapToModel(appointmentRepository.save(appointment)));
 }

  //TODO: change to MappingService
  public AppointmentDto mapToModel(Appointment appointment) {
    return new AppointmentDto(appointment.get_id(),
        appointment.getDoctorName(),
        appointment.getPetName(),
        appointment.getDiagnostic(),
        appointment.getDateTime(),
        appointment.getStatus(),
        appointment.getServices());
  }
}
