package com.example.demo.service.repository;

import com.example.demo.service.domain.Appointment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AppointmentPagedRepository extends PagingAndSortingRepository<Appointment, String> {

}
