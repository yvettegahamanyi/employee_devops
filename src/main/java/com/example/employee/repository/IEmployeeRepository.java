package com.example.employee.repository;

import com.example.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, UUID> {

    Optional<Employee> findByEmailOrPhoneNumber(String email, String phoneNumber);

    Optional<Employee> findByEmail(String email);

    Optional<Employee> findByPhoneNumber(String email);
}
