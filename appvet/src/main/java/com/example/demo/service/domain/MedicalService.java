package com.example.demo.service.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MedicalService extends BaseEntity {

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private Float price;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "services")
  @JsonBackReference
  private List<Appointment> appointments = new ArrayList<>();

  public MedicalService() {
  }

  public MedicalService(String _id, String name, Float price) {
    this.set_id(_id);
    this.name = name;
    this.price = price;
  }

  public MedicalService(String _id, String name, Float price, List<Appointment> appointments) {
    this.set_id(_id);
    this.name = name;
    this.price = price;
    this.appointments = appointments;
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
