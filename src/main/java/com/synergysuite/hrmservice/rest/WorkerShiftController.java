package com.synergysuite.hrmservice.rest;


import com.synergysuite.hrmservice.model.Employee;
import com.synergysuite.hrmservice.model.Worker_shift;
import com.synergysuite.hrmservice.service.WorkerShiftService;
import com.synergysuite.hrmservice.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller
@RequestMapping("/worker_shift")
public class WorkerShiftController {

    @Autowired
    private WorkerShiftService service;

    @GetMapping("")
    public ResponseEntity getAllWorkerShiftsRest() {
        return ResponseEntity.ok(this.service.getAllWorkerShifts());
    }


    @GetMapping("/{id}")
    public ResponseEntity getWorkerShiftById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(this.service.getWorkerShiftById(id));
        } catch (ServiceException e) {
            return e.toResponse();
        }
    }


    @GetMapping("/date/{date}")
    public ResponseEntity getWorkerShiftsByDate(@PathVariable("date") String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate datee = LocalDate.parse(date, formatter);
        try {
            return ResponseEntity.ok(this.service.getWorkerShiftsByDate(datee));
        } catch (ServiceException ex) {
            return ex.toResponse();
        }
    }

    @PostMapping("/insert")
    public ResponseEntity saveWorkerShiftRest(@RequestBody Worker_shift ws) {
        try {
            return ResponseEntity.ok(this.service.saveWorkerShiftRest(ws));

        } catch (ServiceException ex) {
            return ex.toResponse();
        }
    }


    @GetMapping("/update/{id}/{clock_in}/{clock_out}/{worker_id}/{shift_id}/{branch_id}/{date}")
    public ResponseEntity updateWorkerShiftRest(@PathVariable("id") Long id, @PathVariable("clock_in") LocalDate clock_in, @PathVariable("clock_out") LocalDate clock_out, @PathVariable("worker_id") Long worker_id, @PathVariable("shift_id") Long shift_id, @PathVariable("branch_id") Long branch_id, @PathVariable("date") LocalDate date) {
        try {
            return ResponseEntity.ok(this.service.updateWorkerShiftRest(id, clock_in, clock_out, worker_id, shift_id, branch_id, date));
        } catch (ServiceException e) {
            return e.toResponse();
        }
    }
}