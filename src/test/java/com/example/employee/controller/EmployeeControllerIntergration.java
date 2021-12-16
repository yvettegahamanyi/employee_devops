package com.example.employee.controller;

import com.example.employee.model.Employee;
import com.example.employee.utils.APIResponse;
import com.example.employee.utils.dtos.RegisterEmployee;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIntergration {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAll_success () throws JSONException {
        String response = this.restTemplate.getForObject("/all-employee", String.class);
        System.out.println(response);

        JSONAssert.assertEquals("[{\"id\":\"bc6bd171-790d-4f67-8943-a9f57ff47b2d\"}," +
                "{\"id\":\"b88c06c3-ae03-4deb-8724-9f74c51e0737\"}," +
                "{\"id\":\"b3664e8f-c742-4d74-a46e-6ab651176a4d\"}]",
                response,
                false);
    }

    @Test
    public void getById_successObject(){
        ResponseEntity<Employee> employee = this.restTemplate.getForEntity("/employee/bc6bd171-790d-4f67-8943-a9f57ff47b2d", Employee.class);

        assertTrue(employee.getStatusCode().is2xxSuccessful());
        assertEquals("kalisa@gmail.com", employee.getBody().getEmail());
        assertEquals("Kigali", employee.getBody().getLocation());
    }

    @Test
    public void getById_404() {
        ResponseEntity<APIResponse> response =
                this.restTemplate.getForEntity("/employee/bc6bd171-780d-4f67-8943-a9f57ff47b2", APIResponse.class);

//        assertTrue(response.getStatusCodeValue()==404);
//        assertFalse(response.getBody().isStatus());
//        assertEquals("Item not found", response.getBody().getMessage());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void createEmployee_success(){
        RegisterEmployee request = new RegisterEmployee("Gahamanyi Yvette","gahamanyi@gmail.com","0788112203","Huye","yvette");
        ResponseEntity<Employee> response = this.restTemplate.postForEntity("/add-employee", request, Employee.class);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("gahamanyi@gmail.com", response.getBody().getEmail());
    }

    @Test
    public void updateEmployee_success(){
        RegisterEmployee request = new RegisterEmployee("Gahamanyi Yvette","gahamanyi@gmail.com","0788112203","Huye","yvette");
        ResponseEntity<Employee> response = this.restTemplate.exchange("/update-employee/bc6bd171-790d-4f67-8943-a9f57ff47b2d", HttpMethod.PUT, new HttpEntity<>(request), Employee.class);

        assertEquals(Objects.requireNonNull(response.getBody()).getEmail(), "gahamanyi@gmail.com");
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void delete_test() {
        ResponseEntity<String> response = restTemplate.exchange("/delete_employee?id=bc6bd171-790d-4f67-8943-a9f57ff47b2d", HttpMethod.DELETE, null, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Removed", response.getBody());
    }
}

