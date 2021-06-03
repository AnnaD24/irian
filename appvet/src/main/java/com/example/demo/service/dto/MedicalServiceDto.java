package com.example.demo.service.dto;

public class MedicalServiceDto {
  public String id;
  public String name;
  public Float price;

  public MedicalServiceDto(String id, String name, Float price) {
    this.id = id;
    this.name = name;
    this.price = price;
  }

  //needed for validation
  public String getName() {
    return name;
  }

  public Float getPrice() {
    return price;
  }
}
