package com.example.demo.service.dto;

public class ServiceTypeDto {
  public String id;
  public String name;
  public Float price;

  public ServiceTypeDto(String id, String name, Float price) {
    this.id = id;
    this.name = name;
    this.price = price;
  }
}
