package com.example.demo.service.service;

import com.example.demo.service.domain.ServiceType;

import java.util.Collection;
import java.util.Optional;

public interface IServiceTypeService {
  Collection<ServiceType> getServices();

  Optional<ServiceType> add(ServiceType serviceType);
}
