package com.paychex.timeclock.timeClock.service;

import com.paychex.timeclock.timeClock.model.Employee;
import com.paychex.timeclock.timeClock.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee)
    {
        return employeeRepository.save(employee);
    }

    public Employee getEmployeebyId(Long employeeId)
    {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + employeeId));
    }

    public List<Employee> listEmployees()
    {
        return employeeRepository.findAll();
    }
}
