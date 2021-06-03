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
  public List<MedicalServiceDto> services;
  public Float totalCost;

  public AppointmentDto(String id, String doctorName, String petName, String diagnostic, Date dateTime, AppointmentStatus status, List<MedicalServiceDto> services, Float totalCost) {
    this.id = id;
    this.doctorName = doctorName;
    this.petName = petName;
    this.diagnostic = diagnostic;
    this.dateTime = dateTime;
    this.status = status;
    this.services = services;
    this.totalCost = totalCost;
  }

  //needed for validation
  public String getDoctorName() {
    return doctorName;
  }

  public String getPetName() {
    return petName;
  }
}
