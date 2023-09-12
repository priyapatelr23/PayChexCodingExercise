package com.paychex.timeclock.timeClock.repository;

import com.paychex.timeclock.timeClock.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmail(String email);
}
