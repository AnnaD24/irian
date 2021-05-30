package com.example.demo.web.rest;

import com.example.demo.service.domain.ServiceType;
import com.example.demo.service.dto.ServiceTypeDto;
import com.example.demo.service.service.MedicalServiceValidator;
import com.example.demo.service.service.impl.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.URI;
import java.util.Collection;

@EnableSwagger2
@RestController
@RequestMapping("/rest/services")
public class MedicalServiceController {
  @Autowired
  private ServiceTypeService serviceTypeService;

  @Autowired
  MedicalServiceValidator medicalServiceValidator;

  @GetMapping
  public Collection<ServiceTypeDto> getServices() {
    return serviceTypeService.getServices();
  }

  @PostMapping
  @ResponseBody
  public ResponseEntity<?> addService(@RequestBody ServiceTypeDto serviceTypeDto, BindingResult result) {
    medicalServiceValidator.validate(serviceTypeDto, result);

    if(result.hasErrors()) {
      return ResponseEntity.badRequest()
          .body(result.getAllErrors());
    }

    return serviceTypeService.add(serviceTypeDto)
        .map(value -> new ResponseEntity<>(value + " added", HttpStatus.CREATED))
        .orElseGet(() -> new ResponseEntity<>("Error when adding new service", HttpStatus.INTERNAL_SERVER_ERROR));
  }
}
