package com.paychex.timeclock.timeClock.controller;

import com.paychex.timeclock.timeClock.model.Shift;
import com.paychex.timeclock.timeClock.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shifts")
public class ShiftController {
    @Autowired
    private ShiftService shiftService;

    @PostMapping("/start")
    public ResponseEntity<Shift> startShift(@RequestParam Long employeeId)
    {
        Shift shift = shiftService.startShiftForEmployee(employeeId);
        return ResponseEntity.ok(shift);
    }

    @PostMapping("/end")
    public ResponseEntity<Shift>  endShift(@RequestParam Long shiftId)
    {
        Shift shift = shiftService.endShift(shiftId);
        return ResponseEntity.ok(shift);
    }

    @GetMapping("/summary")
    public ResponseEntity<List<Shift>> getShiftHistory(@RequestParam Long employeeId)
    {
        List<Shift> shiftList = shiftService.getShiftSummaryForEmployee(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(shiftList);
    }

}
