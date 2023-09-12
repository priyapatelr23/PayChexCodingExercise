package com.paychex.timeclock.timeClock.controller;

import com.paychex.timeclock.timeClock.model.Employee;
import com.paychex.timeclock.timeClock.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

public class EmployeeControllerTest {

    @InjectMocks
    EmployeeController employeeController;

    @Mock
    EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateEmployee()
    {
        Employee employee = createEmployee();

        when(employeeService.createEmployee(employee)).thenReturn(employee);
        ResponseEntity<Employee> responseEntity = employeeController.createEmployee(employee);

        assert(responseEntity.getStatusCode() == HttpStatus.CREATED);
        assert(responseEntity.getBody() != null);
        //Can verfiy the contents of the body example email, firstname and etc...
        assert(responseEntity.getBody().getEmail().equals(employee.getEmail()));
        verify(employeeService, times(1)).createEmployee(employee);

    }

    @Test
    public void getEmployeeInvalidReuqest()
    {
        when(employeeService.getEmployeebyId(any(Long.class))).thenThrow(new IllegalArgumentException("Employee id is invalid"));
        ResponseEntity<Employee> responseEntity = employeeController.getEmployee(1L);
        assert(responseEntity.getStatusCode() == HttpStatus.NOT_FOUND);
        verify(employeeService, times(1)).getEmployeebyId(1L);
    }

    @Test
    public void getEmployeeValidRequest()
    {
        when(employeeService.getEmployeebyId(any(Long.class))).thenReturn(createEmployee());
        ResponseEntity<Employee> responseEntity = employeeController.getEmployee(1L);
        assert(responseEntity.getStatusCode() == HttpStatus.OK);
        verify(employeeService, times(1)).getEmployeebyId(1L);
        assert(responseEntity.getBody() != null);
        //Can also verfiy the contents of the body

    }

    private Employee createEmployee()
    {
        Employee employee = new Employee();
        employee.setEmail("priya@gmail.com");
        employee.setFirstName("priya");
        employee.setLastName("patel");
        employee.setId(1L);
        return employee;
    }
}
