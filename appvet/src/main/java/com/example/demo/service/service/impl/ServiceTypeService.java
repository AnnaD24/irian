package com.example.demo.service.service.impl;

import com.example.demo.service.domain.ServiceType;
import com.example.demo.service.repository.ServiceTypeRepository;
import com.example.demo.service.service.IServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ServiceTypeService implements IServiceTypeService {
  @Autowired
  private ServiceTypeRepository serviceTypeRepository;

  @Override
  public Collection<ServiceType> getServices() {
    return serviceTypeRepository.findAll();
  }

  @Override
  public Optional<ServiceType> add(ServiceType serviceType) {
    return Optional.of(serviceTypeRepository.save(serviceType));
  }
}
