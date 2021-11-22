package com.example.employee.repository;

import com.example.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IEmployeeRepository extends JpaRepository<Employee, UUID> {

    Optional<Employee> findByEmailOrPhoneNumber(String email, String phoneNumber);

    Optional<Employee> findByEmail(String email);

    Optional<Employee> findByPhoneNumber(String email);
}
