package com.example.demo.service.service;

import com.example.demo.service.domain.Appointment;
import com.example.demo.service.domain.ServiceType;
import com.example.demo.service.dto.ServiceTypeDto;
import com.example.demo.service.service.impl.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class MedicalServiceValidator implements Validator {
  @Autowired
  private ServiceTypeService serviceTypeService;

  @Override
  public boolean supports(Class<?> aClass) {
    return ServiceTypeDto.class.equals(aClass);
  }

  @Override
  public void validate(Object target, Errors errors) {
    ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");

    ServiceTypeDto serviceType = (ServiceTypeDto) target;

    if(serviceType.price <= 0) {
      errors.rejectValue("price", "negativeOrZeroValue");
    }

    if (serviceTypeService.existsByName(serviceType.name)) {
      errors.rejectValue("name", "notUnique");
    }
  }
}
