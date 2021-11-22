package com.example.employee.service;

import com.example.employee.model.Employee;
import com.example.employee.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    public List<Employee> getAll() {

        List<Employee> employees = employeeRepository.findAll();

        return employees;
    }

    public Employee getById(UUID id) {
        Optional<Employee> findById = employeeRepository.findById(id);
        if(findById.isPresent()) {
            Employee employee = findById.get();
            return employee;
        }
        return null;
    }
}
