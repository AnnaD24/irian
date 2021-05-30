package com.example.demo.service.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ServiceType extends BaseEntity  {

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private Float price;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "services")
  private List<Appointment> appointments = new ArrayList<>();

  public ServiceType() {
  }

  public ServiceType(String name, Float price) {
    this.name = name;
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
