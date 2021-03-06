package com.example.demo.service.repository;

import com.example.demo.service.domain.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AppointmentPagedRepository extends PagingAndSortingRepository<Appointment, String> {
  Page<Appointment> findByDoctorName(String doctorName, Pageable pageable);
}
