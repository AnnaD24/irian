package com.example.demo.service.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class ServiceType extends BaseEntity  {

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private Float price;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(
      name = "appointment_service",
      joinColumns = @JoinColumn(name = "service_id"),
      inverseJoinColumns = @JoinColumn(name = "appointment_id")
  )
  private List<Appointment> appointments;

  public ServiceType() {
  }

  public ServiceType(String _id, String name, List<Appointment> appointments, Float price) {
    this.set_id(_id);
    this.name = name;
    this.appointments = appointments;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Appointment> getAppointments() {
    return appointments;
  }

  public void setAppointments(List<Appointment> appointments) {
    this.appointments = appointments;
  }

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }
}
