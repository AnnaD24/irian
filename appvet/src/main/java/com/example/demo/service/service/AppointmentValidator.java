package com.example.demo.service.service;

import com.example.demo.service.dto.AppointmentDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AppointmentValidator implements Validator {

  @Override
  public boolean supports(Class<?> aClass) {
    return AppointmentDto.class.equals(aClass);
  }

  @Override
  public void validate(Object target, Errors errors) {
    ValidationUtils.rejectIfEmpty(errors, "petName", "petName.empty");
    ValidationUtils.rejectIfEmpty(errors, "doctorName", "doctorName.empty");
  }
}
