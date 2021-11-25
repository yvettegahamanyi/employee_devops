package com.example.employee.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeService;
import com.example.employee.utils.dtos.RegisterEmployee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
    @MockBean
    private EmployeeService employeeServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAll_success() throws Exception {
        List<Employee> asList = Arrays.asList(
                new Employee(UUID.fromString("b88c06c3-ae03-4deb-8724-9f74c51e0737"),"uwera koko","uwera@gmail.com","0788112234","Kigali"),
                new Employee(UUID.fromString("bc6bd171-790d-4f67-8943-a9f57ff47b2d"),"kalisa bella","kalisa@gmail.com","0788112233","Kigali")
        );
        when(employeeServiceMock.getAll()).thenReturn(asList);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/all-employee")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[" +
                        "{\"id\":\"b88c06c3-ae03-4deb-8724-9f74c51e0737\",\"fullNames\":\"uwera koko\",\"email\":\"uwera@gmail.com\",\"phoneNumber\":\"0788112234\",\"location\":\"Kigali\"},{\"id\":\"bc6bd171-790d-4f67-8943-a9f57ff47b2d\",\"fullNames\":\"kalisa bella\",\"email\":\"kalisa@gmail.com\",\"phoneNumber\":\"0788112233\",\"location\":\"Kigali\"}]"))
                .andReturn();
    }

    @Test
    public void getById_Success() throws Exception {
        Employee employee = new Employee(UUID.fromString("b88c06c3-ae03-4deb-8724-9f74c51e0737"),"uwera koko","uwera@gmail.com","0788112234","Kigali");

        when(employeeServiceMock.getById(employee.getId())).thenReturn(employee);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/employee/b88c06c3-ae03-4deb-8724-9f74c51e0737")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":\"b88c06c3-ae03-4deb-8724-9f74c51e0737\",\"fullNames\":\"uwera koko\",\"email\":\"uwera@gmail.com\",\"phoneNumber\":\"0788112234\",\"location\":\"Kigali\"}"))
                .andReturn();
    }


    @Test
    public void getById_WhenGivenIdNotFound() throws Exception {
        Employee employee = new Employee(UUID.fromString("b88c06c3-ae03-4deb-8724-9f74c51e0737"),"uwera koko","uwera@gmail.com","0788112234","Kigali");

        when(employeeServiceMock.getById(employee.getId())).thenReturn(employee);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/employee/8f352825-e13f-4f3f-b0ad-e3d2fcebcfbc")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc
                .perform(request)
                .andExpect(status().isNotFound())
                .andExpect(content().json("{\"status\":false,\"message\":\"Employee not found\"}"))
                .andReturn();
    }

    @Test
    public void updateEmployee_success() throws Exception {
        Employee employee = new Employee(UUID.fromString("b88c06c3-ae03-4deb-8724-9f74c51e0737"),"uwera koko","gy@gmail.com","0788112234","Kigali","uwera");
        RegisterEmployee employeeDto = new RegisterEmployee("uwera koko","gy@gmail.com","0788112234","Kigali","uwera");


        when(employeeServiceMock.updateEmployee(employee.getId(), employeeDto)).thenReturn(employee);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put("/update-employee/b88c06c3-ae03-4deb-8724-9f74c51e0737")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"fullNames\":\"uwera koko\",\"email\":\"gy@gmail.com\",\"phoneNumber\":\"0788112234\",\"location\":\"Kigali\",\"password\":\"uwera\"}");

        MvcResult result = mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"fullNames\":\"uwera koko\",\"email\":\"gy@gmail.com\",\"phoneNumber\":\"0788112234\",\"location\":\"Kigali\",\"password\":\"uwera\"}"))
                .andReturn();
    }

    @Test
    public void updateEmployee_notFound() throws Exception {
        Employee employee = new Employee(UUID.fromString("b88c06c3-ae03-4deb-8724-9f74c51e0737"),"uwera koko","gy@gmail.com","0788112234","Kigali","uwera");
        RegisterEmployee employeeDto = new RegisterEmployee("uwera koko","gy@gmail.com","0788112234","Kigali","uwera");


        when(employeeServiceMock.updateEmployee(employee.getId(), employeeDto)).thenReturn(employee);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put("/update-employee/b88c06c3-ae03-4deb-8724-9f74c51e8737")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"fullNames\":\"uwera koko\",\"email\":\"gy@gmail.com\",\"phoneNumber\":\"0788112234\",\"location\":\"Kigali\",\"password\":\"uwera\"}");

        MvcResult result = mockMvc
                .perform(request)
                .andExpect(status().isNotFound())
                .andExpect(content().json("{\"status\":false,\"message\":\"Employee not found\"}"))
                .andReturn();
    }

    @Test
    public void deleteEmployee_Success() throws Exception {
        Employee employee = new Employee(UUID.fromString("b88c06c3-ae03-4deb-8724-9f74c51e0737"),"uwera koko","uwera@gmail.com","0788112234","Kigali","uwera");

        when(employeeServiceMock.deleteEmployee(employee.getId())).thenReturn(employee);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete("/delete_employee?id=b88c06c3-ae03-4deb-8724-9f74c51e0737")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"fullNames\":\"uwera koko\",\"email\":\"uwera@gmail.com\",\"phoneNumber\":\"0788112234\",\"location\":\"Kigali\"}"))
                .andReturn();
    }
}
