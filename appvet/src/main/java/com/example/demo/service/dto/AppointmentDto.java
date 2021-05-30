package com.example.demo.service.dto;

import com.example.demo.service.domain.AppointmentStatus;

import java.util.Date;
import java.util.List;

public class AppointmentDto {
  public String id;
  public String doctorName;
  public String petName;
  public String diagnostic;
  public Date dateTime;
  public AppointmentStatus status;
  public List<ServiceTypeDto> services;

  public AppointmentDto(String id, String doctorName, String petName, String diagnostic, Date dateTime, AppointmentStatus status, List<ServiceTypeDto> services) {
    this.id = id;
    this.doctorName = doctorName;
    this.petName = petName;
    this.diagnostic = diagnostic;
    this.dateTime = dateTime;
    this.status = status;
    this.services = services;
  }

  public String getDoctorName() {
    return doctorName;
  }

  public void setDoctorName(String doctorName) {
    this.doctorName = doctorName;
  }

  public String getPetName() {
    return petName;
  }

  public void setPetName(String petName) {
    this.petName = petName;
  }

  public String getDiagnostic() {
    return diagnostic;
  }

  public void setDiagnostic(String diagnostic) {
    this.diagnostic = diagnostic;
  }

  public Date getDateTime() {
    return dateTime;
  }

  public void setDateTime(Date dateTime) {
    this.dateTime = dateTime;
  }

  public AppointmentStatus getStatus() {
    return status;
  }

  public void setStatus(AppointmentStatus status) {
    this.status = status;
  }

  public List<ServiceTypeDto> getServices() {
    return services;
  }

  public void setServices(List<ServiceTypeDto> services) {
    this.services = services;
  }
}
