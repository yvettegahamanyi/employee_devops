package com.example.employee.service;

import com.example.employee.model.Employee;
import com.example.employee.repository.IEmployeeRepository;
import com.example.employee.utils.dtos.RegisterEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
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
        throw new RuntimeException("Employee not found with id"+ id);
    }

    public Employee createEmployee(RegisterEmployee dto){

        Employee employee= new Employee();
        employee.setFullNames(dto.getFullNames());
        employee.setEmail(dto.getEmail());
        employee.setLocation(dto.getLocation());
        employee.setPassword(dto.getPassword());
        employee.setPhoneNumber(dto.getPhoneNumber());

        return employeeRepository.save(employee);
    }

    public Employee deleteEmployee(UUID id){
       Optional<Employee> foundEmployee = employeeRepository.findById(id);
        if(foundEmployee!= null){
            employeeRepository.deleteById(foundEmployee.get().getId());
            return foundEmployee.get();
        }
        throw new RuntimeException("Employee not found with id"+ id);
    }

    public Employee updateEmployee(UUID id, RegisterEmployee dto){

       Employee foundEmployee = employeeRepository.getById(id);
       if(foundEmployee!= null){

           foundEmployee.setFullNames(dto.getFullNames());
           foundEmployee.setEmail(dto.getEmail());
           foundEmployee.setLocation(dto.getLocation());
           foundEmployee.setPassword(dto.getPassword());
           foundEmployee.setPhoneNumber(dto.getPhoneNumber());
           return employeeRepository.save(foundEmployee);
       }
          throw new RuntimeException("Employee not found with id"+ id);
//                .orElseThrow(()-> new RuntimeException("Employee not found with id"+ id));
//        employee.setId(id);
    }
}
