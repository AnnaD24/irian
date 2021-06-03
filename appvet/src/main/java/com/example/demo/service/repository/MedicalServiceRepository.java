package com.example.demo.service.repository;

import com.example.demo.service.domain.MedicalService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicalServiceRepository extends JpaRepository<MedicalService, String> {
  Optional<MedicalService> findByName(String name);
  boolean existsByName(String name);
}
