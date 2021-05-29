package com.example.demo.service.repository;

import com.example.demo.service.domain.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceTypeRepository extends JpaRepository<ServiceType, String> {
}
