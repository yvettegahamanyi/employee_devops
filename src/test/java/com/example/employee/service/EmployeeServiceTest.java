package com.example.employee.service;

import com.example.employee.model.Employee;
import com.example.employee.repository.IEmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
}
