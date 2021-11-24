package com.example.employee.repository;

import com.example.employee.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Test
    public void findAll_success () {
        List<Employee> items = employeeRepository.findAll();
        assertEquals(3, items.size());
    }
}
