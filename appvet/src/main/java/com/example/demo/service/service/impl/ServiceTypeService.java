package com.example.demo.service.service.impl;

import com.example.demo.service.domain.ServiceType;
import com.example.demo.service.dto.ServiceTypeDto;
import com.example.demo.service.repository.ServiceTypeRepository;
import com.example.demo.service.service.IServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceTypeService implements IServiceTypeService {

  @Autowired
  private ServiceTypeRepository serviceTypeRepository;

  @Override
  public Collection<ServiceTypeDto> getServices() {
    return serviceTypeRepository.findAll()
        .stream()
        .map(this::mapToModel)
        .collect(Collectors.toList());
  }

  @Override
  public Optional<ServiceTypeDto> add(ServiceTypeDto serviceTypeDto) {
    ServiceType serviceType = new ServiceType();
    serviceType.setName(serviceTypeDto.name);
    serviceType.setPrice(serviceTypeDto.price);
    return Optional.of(mapToModel(serviceTypeRepository.save(serviceType)));
  }

  @Override
  public boolean existsByName(String name) {
    return serviceTypeRepository.existsByName(name);
  }

  //TODO: change to MappingService
  public ServiceTypeDto mapToModel(ServiceType serviceType) {
    return new ServiceTypeDto(serviceType.get_id(), serviceType.getName(), serviceType.getPrice());
  }
}
