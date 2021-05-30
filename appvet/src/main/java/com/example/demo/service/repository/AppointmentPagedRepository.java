package com.example.demo.service.repository;

import com.example.demo.service.domain.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.Optional;

public interface AppointmentPagedRepository extends PagingAndSortingRepository<Appointment, String> {
  Page<Appointment> findByDoctorName(String doctorName, Pageable pageable);
  Optional<Appointment> findByPetNameAndDateTime(String petName, Date dateTime);
}
