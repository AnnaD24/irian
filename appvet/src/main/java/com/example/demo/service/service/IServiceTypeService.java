package com.example.demo.service.service;

import com.example.demo.service.domain.ServiceType;
import com.example.demo.service.dto.ServiceTypeDto;

import java.util.Collection;
import java.util.Optional;

public interface IServiceTypeService {
  Collection<ServiceTypeDto> getServices();

  Optional<ServiceTypeDto> add(ServiceTypeDto serviceTypeDto);
}
