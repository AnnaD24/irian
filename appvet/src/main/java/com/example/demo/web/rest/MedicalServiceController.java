package com.example.demo.web.rest;

import com.example.demo.service.dto.MedicalServiceDto;
import com.example.demo.service.service.MedicalServiceValidator;
import com.example.demo.service.service.impl.MedicalServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@EnableSwagger2
@RestController
@RequestMapping("/rest/services")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MedicalServiceController {
  @Autowired
  MedicalServiceValidator medicalServiceValidator;
  @Autowired
  private MedicalServiceService medicalServiceService;

  @GetMapping
  public Collection<MedicalServiceDto> getServices() {
    return medicalServiceService.getServices();
  }

  @PostMapping
  @ResponseBody
  public ResponseEntity<?> addService(@RequestBody MedicalServiceDto medicalServiceDto, BindingResult result) throws URISyntaxException {
    medicalServiceValidator.validate(medicalServiceDto, result);

    if (result.hasErrors()) {
      return ResponseEntity.badRequest()
          .body(result.getAllErrors());
    }

    Optional<MedicalServiceDto> newService = medicalServiceService.add(medicalServiceDto);

    if (newService.isEmpty())
      return new ResponseEntity<>("Error when persisting new service.", HttpStatus.INTERNAL_SERVER_ERROR);

    return ResponseEntity.created(new URI("/rest/services"))
        .body(newService);
  }
}
