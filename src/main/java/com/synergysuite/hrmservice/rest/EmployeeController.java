package com.synergysuite.hrmservice.rest;

import com.synergysuite.hrmservice.model.Employee;
import com.synergysuite.hrmservice.service.EmployeeService;
import com.synergysuite.hrmservice.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService service;


    @GetMapping("")
    public ResponseEntity getAllEmployees() {
       return ResponseEntity.ok(this.service.getAllEmployees());
    }
//
//    @Transactional
    @PostMapping("/insert")
    public ResponseEntity saveEmployeeRest(@RequestBody Employee e) {
        try {
            return ResponseEntity.ok(this.service.saveEmployeeRest(e));

        } catch (ServiceException ex) {
            return ex.toResponse();
        }
    }


//    @Transactional
    @GetMapping("/delete/{id}")
    public ResponseEntity deleteEmployeeRest(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.service.deleteEmployeeRest(id));
        } catch (ServiceException ex) {
            return ex.toResponse();
        }
    }

//    @Transactional
    @GetMapping("/retrieve/{id}")
    public ResponseEntity retrieveEmployeeRest(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.service.retrieveEmployeeRest(id));
        } catch (ServiceException e) {
            return e.toResponse();
        }
    }

//    @Transactional
    @GetMapping("/update/{id}/{firstName}/{lastName}/{email}")
    public ResponseEntity updateEmployeeRest(@PathVariable("id") Long id, @PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName, @PathVariable("email") String email) {
        try {
            return ResponseEntity.ok(this.service.updateEmployeeRest(id, firstName, lastName, email));
        } catch (ServiceException e) {
            return e.toResponse();
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity getEmployeeById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(this.service.getEmployeeById(id));
        } catch (ServiceException e) {
            return e.toResponse();
        }
    }
}
