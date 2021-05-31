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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
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
  public Page<AppointmentDto> getAppointments(Integer pageNo, Integer pageSize, String sortBy, String direction) {
    Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.fromString(direction) ,sortBy));
    Page<Appointment> appointmentsPage = appointmentRepository.findAll(pageable);
    return appointmentsPage.map(this::mapToModel);
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
    for(ServiceTypeDto serviceType: appointmentDto.services) {
      Optional<ServiceType> foundService = serviceTypeRepository.findById(serviceType.id);
      appointment.addService(foundService.get());
    }

    return Optional.of(mapToModel(appointmentRepository.save(appointment)));
 }

  @Override
  public Page<AppointmentDto> getAppointmentsForDoctor(String doctorName, Integer pageNo, Integer pageSize, String sortBy, String direction) {
    Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.fromString(direction) ,sortBy));
    Page<Appointment> appointmentsPage = appointmentRepository.findByDoctorName(doctorName, pageable);
    return appointmentsPage.map(this::mapToModel);
  }

  @Override
  public Optional<Appointment> getAppointment(String id) {
    return appointmentRepository.findById(id);
  }

  @Override
  public Optional<AppointmentDto> modifyAppointment(AppointmentDto appointmentDto) {
    Optional<Appointment> newAppointment = appointmentRepository.findById(appointmentDto.id);

    if(newAppointment.isEmpty())
      return Optional.empty();

    Appointment foundAppointment = newAppointment.get();

    List<ServiceType> serviceTypeList = appointmentDto.services.stream()
        .map(serviceTypeDto -> new ServiceType(serviceTypeDto.id, serviceTypeDto.name, serviceTypeDto.price))
        .collect(Collectors.toList());

    foundAppointment.setDateTime(appointmentDto.dateTime);
    foundAppointment.setDiagnostic(appointmentDto.diagnostic);
    foundAppointment.setDoctorName(appointmentDto.doctorName);
    foundAppointment.setServices(serviceTypeList);
    foundAppointment.setStatus(appointmentDto.status);
    foundAppointment.setPetName(appointmentDto.petName);

    return Optional.of(mapToModel(appointmentRepository.save(foundAppointment)));
  }

  @Override
  public boolean existsAppointmentByPetNameAndDate(String petName, Date dateTime) {
    return appointmentRepository.existsAppointmentByPetNameAndDateTime(petName, dateTime);
  }

  //TODO: change to MappingService
  public AppointmentDto mapToModel(Appointment appointment) {
    List<ServiceTypeDto> serviceDtos = appointment.getServices().stream()
        .map(serviceType -> serviceTypeService.mapToModel(serviceType))
        .collect(Collectors.toList());

    return new AppointmentDto(appointment.get_id(),
        appointment.getDoctorName(),
        appointment.getPetName(),
        appointment.getDiagnostic(),
        appointment.getDateTime(),
        appointment.getStatus(),
        serviceDtos,
        this.computeServicesTotalCost(serviceDtos));
  }


  private Float computeServicesTotalCost(List<ServiceTypeDto> services) {
    return services.stream()
        .map(serviceTypeDto -> serviceTypeDto.price)
        .reduce((float) 0, Float::sum);
  }
}
