package com.synergysuite.hrmservice.rest;

import com.synergysuite.hrmservice.service.EmployeeService;
import com.synergysuite.hrmservice.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @GetMapping("/{id}")
    public ResponseEntity getEmployeeById(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(this.service.getEmployeeById(id));
        } catch (ServiceException e) {
            return e.toResponse();
        }
    }
}
