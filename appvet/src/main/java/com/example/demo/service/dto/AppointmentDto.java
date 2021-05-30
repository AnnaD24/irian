package com.example.demo.service.dto;

import com.example.demo.service.domain.AppointmentStatus;
import com.example.demo.service.domain.ServiceType;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class AppointmentDto {
  public String id;
  public String doctorName;
  public String petName;
  public String diagnostic;
  public Date dateTime;
  public AppointmentStatus status;
  public List<ServiceType> services;

  public AppointmentDto(String id, String doctorName, String petName, String diagnostic, Date dateTime, AppointmentStatus status, List<ServiceType> services) {
    this.id = id;
    this.doctorName = doctorName;
    this.petName = petName;
    this.diagnostic = diagnostic;
    this.dateTime = dateTime;
    this.status = status;
    this.services = services;
  }
}
