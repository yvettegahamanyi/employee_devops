package com.example.employee.controller;

import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeService;
import com.example.employee.utils.APIResponse;
import com.example.employee.utils.dtos.RegisterEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all-employee")
    public List<Employee> getAll() {

        return employeeService.getAll();
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") UUID id) {

        Employee employee = employeeService.getById(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(false, "Employee not found"));
    }

    @PostMapping("/add-employee")
    public ResponseEntity<?> addEmployee(@RequestBody RegisterEmployee employee){
        Employee createdEmployee = employeeService.createEmployee(employee);
        if (createdEmployee != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(employee);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(false, "Employee not created"));

//        return employeeService.createEmployee(employee);
    }

    @PutMapping("/update-employee")
    public ResponseEntity<?> updateEmployee(@RequestBody RegisterEmployee employee, @RequestParam UUID id){
        Employee updatedEmployee = employeeService.updateEmployee(id,employee);
        if (updatedEmployee != null) {
            return ResponseEntity.status(HttpStatus.OK).body(employee);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(false, "Employee not found"));

//        return employeeService.createEmployee(employee);
    }

    @DeleteMapping("/delete_employee")
    public ResponseEntity<?> deleteEmployee(@RequestParam UUID id ){
          Employee deleteEmployee =  employeeService.deleteEmployee(id);
          return ResponseEntity.ok(deleteEmployee);
    }
}
