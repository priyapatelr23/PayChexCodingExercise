package com.paychex.timeclock.timeClock.service;

import com.paychex.timeclock.timeClock.model.Employee;
import com.paychex.timeclock.timeClock.repository.EmployeeRepository;
import com.paychex.timeclock.timeClock.repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.paychex.timeclock.timeClock.model.Shift;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShiftService {
    @Autowired
    private ShiftRepository shiftRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Shift startShiftForEmployee(Long employeeId)
    {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + employeeId));
        if(isEmployeeCurrentlyOnShift(employeeId))
        {
            throw new IllegalArgumentException("Employee "+ employeeId+ " is currently already on shift");
        }
        Shift shift = new Shift();
        shift.setStartTime(LocalDateTime.now());
        shift.setEmployee(employee);
        return shiftRepository.save(shift);
    }

    public Shift endShift(Long shiftId)
    {
        Shift shift = shiftRepository.findById(shiftId).orElseThrow(() -> new IllegalArgumentException("There is no shift found with the id: "+ shiftId));
        shift.setEndTime(LocalDateTime.now());
        return shiftRepository.save(shift);
    }

    public List<Shift> getShiftSummaryForEmployee(Long employeeId)
    {
        return shiftRepository.findByEmployeeIdOrderByStartTimeDesc(employeeId);
    }

    private boolean isEmployeeCurrentlyOnShift(Long employeeId)
    {
        return (shiftRepository.findByEmployeeIdAndEndTimeIsNull(employeeId) == null) ?  false : true;
    }
}
