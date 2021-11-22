package com.example.employee.controller;

import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeService;
import com.example.employee.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all-employee")
    public List<Employee> getAll() {

        return employeeService.getAll();
    }

    @GetMapping("/all-employee/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") UUID id) {

        Employee employee = employeeService.getById(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(false, "Item not found"));
    }
}
