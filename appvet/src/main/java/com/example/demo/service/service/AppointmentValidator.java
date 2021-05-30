package com.example.demo.service.service;

import com.example.demo.service.dto.AppointmentDto;
import com.example.demo.service.service.impl.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AppointmentValidator implements Validator {
  @Autowired
  private AppointmentService appointmentService;

  @Override
  public boolean supports(Class<?> aClass) {
    return AppointmentDto.class.equals(aClass);
  }

  @Override
  public void validate(Object target, Errors errors) {
    ValidationUtils.rejectIfEmpty(errors, "petName", "petName.empty");
    ValidationUtils.rejectIfEmpty(errors, "doctorName", "doctorName.empty");

    AppointmentDto appointment = (AppointmentDto) target;

    if (appointmentService.existsAppointmentByPetNameAndDate(appointment.petName, appointment.dateTime)) {
      errors.rejectValue("petName", "notUnique");
    }
  }
}
