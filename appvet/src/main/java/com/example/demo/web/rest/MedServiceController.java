package com.example.demo.web.rest;

import com.example.demo.service.domain.ServiceType;
import com.example.demo.service.service.impl.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collection;

@EnableSwagger2
@RestController
@RequestMapping("/rest/services")
public class MedServiceController {
  @Autowired
  private ServiceTypeService serviceTypeService;

  @GetMapping
  public Collection<ServiceType> getServices() {
    return serviceTypeService.getServices();
  }

  @PostMapping
  @ResponseBody
  public ResponseEntity<String> addService(@RequestBody ServiceType serviceType) {
    return serviceTypeService.add(serviceType)
        .map(value -> new ResponseEntity<>(value + " added", HttpStatus.CREATED))
        .orElseGet(() -> new ResponseEntity<>("Error when adding new service", HttpStatus.BAD_REQUEST));
  }
}
