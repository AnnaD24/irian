package com.example.demo.service.repository;

import com.example.demo.service.domain.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceTypeRepository extends JpaRepository<ServiceType, String> {
  Optional<ServiceType> findByName(String name);
  boolean existsByName(String name);
}
