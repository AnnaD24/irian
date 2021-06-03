package com.example.demo.service.service.impl;

import com.example.demo.service.domain.MedicalService;
import com.example.demo.service.dto.MedicalServiceDto;
import com.example.demo.service.repository.MedicalServiceRepository;
import com.example.demo.service.service.IMedicalServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicalServiceService implements IMedicalServiceService {

  @Autowired
  private MedicalServiceRepository medicalServiceRepository;

  @Override
  public Collection<MedicalServiceDto> getServices() {
    return medicalServiceRepository.findAll()
        .stream()
        .map(this::mapToModel)
        .collect(Collectors.toList());
  }

  @Override
  public Optional<MedicalServiceDto> add(MedicalServiceDto medicalServiceDto) {
    MedicalService medicalService = new MedicalService();
    medicalService.setName(medicalServiceDto.name);
    medicalService.setPrice(medicalServiceDto.price);
    return Optional.of(mapToModel(medicalServiceRepository.save(medicalService)));
  }

  @Override
  public boolean existsByName(String name) {
    return medicalServiceRepository.existsByName(name);
  }

  public MedicalServiceDto mapToModel(MedicalService medicalService) {
    return new MedicalServiceDto(medicalService.get_id(), medicalService.getName(), medicalService.getPrice());
  }
}
