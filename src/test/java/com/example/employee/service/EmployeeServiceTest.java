package com.example.employee.service;

import com.example.employee.model.Employee;
import com.example.employee.repository.IEmployeeRepository;
import com.example.employee.utils.dtos.RegisterEmployee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {
    @Mock
    private IEmployeeRepository employeeRepositoryMock;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void getAll_withSomeElements() {

        when(employeeRepositoryMock.findAll()).thenReturn(Arrays.asList(new Employee(UUID.fromString("bc6bd171-790d-4f67-8943-a9f57ff47b2d"),"kalisa bella","kalisa@gmail.com","0788112233","Kigali","kalisa"),
                new Employee(UUID.fromString("b88c06c3-ae03-4deb-8724-9f74c51e0737"),"uwera koko","uwera@gmail.com","0788112234","Kigali","uwera")));
        assertEquals("kalisa@gmail.com",employeeService.getAll().get(0).getEmail());
    }

    @Test
    public void getOnebyid() {
        when(employeeRepositoryMock.findById(UUID.fromString("bc6bd171-790d-4f67-8943-a9f57ff47b2d"))).thenReturn(Optional.of(new Employee(UUID.fromString("bc6bd171-790d-4f67-8943-a9f57ff47b2d"), "kalisa bella", "kalisa@gmail.com", "0788112233", "Kigali", "kalisa")));
        assertEquals("kalisa@gmail.com",employeeService.getById(UUID.fromString("bc6bd171-790d-4f67-8943-a9f57ff47b2d")).getEmail());
    }

    @Test(expected = RuntimeException.class)
    public void getOnebyid_notFound(){
        doThrow(new RuntimeException()).when(employeeRepositoryMock).getOne(UUID.fromString("bc6bd171-790d-4f07-8943-a9f57ff47b2d"));
        employeeService.getById(UUID.fromString("bc6bd171-790d-4f07-8943-a9f57ff47b2d"));
    }

    @Test
    public  void createEmployee(){
        RegisterEmployee dto=new RegisterEmployee("kalisa bella","kalisa@gmail.com","0788112233","Kigali","kalisa");
        Employee employee=new Employee(UUID.fromString("bc6bd171-790d-4f67-8943-a9f57ff47b2d"),"kalisa bella","kalisa@gmail.com","0788112233","Kigali","kalisa");

        when(employeeRepositoryMock.save(ArgumentMatchers.any(Employee.class))).thenReturn(employee);
        assertEquals("kalisa@gmail.com",employeeService.createEmployee(dto).getEmail());
    }

    @Test
    public void updateEmployee(){
        RegisterEmployee dto=new RegisterEmployee("kalisa bella","yg@gmail.com","0788112233","Kigali","kalisa");
        Employee employee=new Employee(UUID.fromString("bc6bd171-790d-4f67-8943-a9f57ff47b2d"),"kalisa bella","kalisa@gmail.com","0788112233","Kigali","kalisa");
        Employee updatedEmployee=new Employee(UUID.fromString("bc6bd171-790d-4f67-8943-a9f57ff47b2d"),"kalisa bella","yg@gmail.com","0788112233","Kigali","kalisa");
//        UUID userId = UUID.fromString("bc6bd171-790d-4f67-8943-a9f57ff47b2d");

        when(employeeRepositoryMock.getById(employee.getId())).thenReturn(employee);
        when(employeeRepositoryMock.save(updatedEmployee)).thenReturn(updatedEmployee);


        assertEquals("yg@gmail.com",employeeService.updateEmployee(employee.getId(), dto).getEmail());
    }

    @Test(expected = RuntimeException.class)
    public void updateEmployee_notfound(){
        doThrow(new RuntimeException()).when(employeeRepositoryMock).getOne(UUID.fromString("bc6bd171-790d-4f07-8943-a9f57ff47b2d"));
        RegisterEmployee dto = new RegisterEmployee("kalisa bella","yg@gmail.com","0788112233","Kigali","kalisa");
        employeeService.updateEmployee(UUID.fromString("bc6bd171-790d-4f07-8943-a9f57ff47b2d"),dto);
    }

    @Test
    public void deleteEmployee(){
        Employee employee = new Employee(UUID.fromString("bc6bd171-790d-4f67-8943-a9f57ff47b2d"),"kalisa bella","kalisa@gmail.com","0788112233","Kigali","kalisa");
        when(employeeRepositoryMock.findById(employee.getId())).thenReturn(Optional.of(employee));
        employeeService.deleteEmployee(employee.getId());
        verify(employeeRepositoryMock).deleteById(employee.getId());
    }
}
