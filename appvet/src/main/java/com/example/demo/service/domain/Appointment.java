package com.example.demo.service.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Appointment extends BaseEntity{

  @Column(nullable = false)
  private String petName;

  @Column(nullable = false)
  private String diagnostic;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private AppointmentStatus status;

  @Column(nullable = false)
  private String doctorName;

  @Temporal(TemporalType.TIMESTAMP)
  private Date dateTime;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appointments")
  private List<ServiceType> services;

  public Appointment(String _id, String petName, String diagnostic, AppointmentStatus status, String doctorName, Date dateTime, List<ServiceType> services) {
    this.set_id(_id);
    this.petName = petName;
    this.diagnostic = diagnostic;
    this.status = status;
    this.doctorName = doctorName;
    this.dateTime = dateTime;
    this.services = services;
  }

  public Appointment() {
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

  public AppointmentStatus getStatus() {
    return status;
  }

  public void setStatus(AppointmentStatus status) {
    this.status = status;
  }

  public String getDoctorName() {
    return doctorName;
  }

  public void setDoctorName(String doctorName) {
    this.doctorName = doctorName;
  }

  public Date getDateTime() {
    return dateTime;
  }

  public void setDateTime(Date dateTime) {
    this.dateTime = dateTime;
  }

  public List<ServiceType> getServices() {
    return services;
  }

  public void setServices(List<ServiceType> services) {
    this.services = services;
  }

  public void addService(ServiceType service) {
    if(services == null) {
      services = new ArrayList<>();
    }
    services.add(service);
  }


}
