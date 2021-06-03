package com.example.demo.service.service;

import com.example.demo.service.dto.MedicalServiceDto;

import java.util.Collection;
import java.util.Optional;

public interface IMedicalServiceService {
  Collection<MedicalServiceDto> getServices();

  Optional<MedicalServiceDto> add(MedicalServiceDto medicalServiceDto);

  boolean existsByName(String name);
}
