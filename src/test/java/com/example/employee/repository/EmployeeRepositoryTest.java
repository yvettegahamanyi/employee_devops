package com.example.employee.repository;

import com.example.employee.model.Employee;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void findOne_success() throws JSONException {
        Optional<Employee> itemOption = employeeRepository.findById(UUID.fromString("bc6bd171-790d-4f67-8943-a9f57ff47b2d"));
        assertTrue(itemOption.isPresent());

		/*JSONObject expected = null;
		expected.put("id",101);
		expected.put("name","Item1");
		expected.put("price",10);
		expected.put("quantity",100);
		*/
        //JSONAssert.assertEquals(expected, new JSONObject(itemOption.get()), true);
    }

    @Test
    public void findOne_failed() {
        Optional<Employee> itemOption = employeeRepository.findById(UUID.fromString("bc6bd171-798d-4f67-8943-a9f57ff47b2d"));
        assertFalse(itemOption.isPresent());
    }

    @Test
    public void createOne_success(){
        Employee employeeToCreate =new Employee("kalisaa bella","kalisaa@gmail.com","0788112239","Kigalii","kalisaa");
        Employee result = employeeRepository.save(employeeToCreate);
        assertTrue(result.getId() != null);
    }

    @Test
    public void deleteOne_success(){
        employeeRepository.deleteById(UUID.fromString("bc6bd171-790d-4f67-8943-a9f57ff47b2d"));
        List<Employee> items = employeeRepository.findAll();
        assertEquals(2, items.size());
    }

//    @Test
//    public void deleteOne_404(){
//        employeeRepository.deleteById(UUID.fromString("bc6bd171-790d-4f67-8903-a9f57ff47b2d"));
//        List<Employee> items = employeeRepository.findAll();
//        assertEquals(3, items.size());
//    }
}
