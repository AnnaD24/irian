package com.example.demo.service.service;

import com.example.demo.service.dto.MedicalServiceDto;
import com.example.demo.service.service.impl.MedicalServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class MedicalServiceValidator implements Validator {
  @Autowired
  private MedicalServiceService medicalServiceService;

  @Override
  public boolean supports(Class<?> aClass) {
    return MedicalServiceDto.class.equals(aClass);
  }

  @Override
  public void validate(Object target, Errors errors) {
    ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");

    MedicalServiceDto serviceType = (MedicalServiceDto) target;

    if(serviceType.price <= 0) {
      errors.rejectValue("price", "negativeOrZeroValue");
    }

    if (medicalServiceService.existsByName(serviceType.name)) {
      errors.rejectValue("name", "notUnique");
    }
  }
}
