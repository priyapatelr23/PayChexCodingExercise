package com.paychex.timeclock.timeClock.repository;

import com.paychex.timeclock.timeClock.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShiftRepository extends JpaRepository<Shift, Long> {
    List<Shift> findByEmployeeIdOrderByStartTimeDesc(Long employeeId);
    Shift findByEmployeeIdAndEndTimeIsNull(Long employeeId);
}
