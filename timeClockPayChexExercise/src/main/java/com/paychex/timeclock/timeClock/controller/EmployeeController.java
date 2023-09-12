package com.paychex.timeclock.timeClock.controller;

import com.paychex.timeclock.timeClock.model.Employee;
import com.paychex.timeclock.timeClock.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee)
    {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
           Employee employee = employeeService.getEmployeebyId(id);
           return ResponseEntity.ok(employee);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> listEmployees()
    {
        List<Employee> employeeList = employeeService.listEmployees();
        return ResponseEntity.status(HttpStatus.OK).body(employeeList);
    }
}
