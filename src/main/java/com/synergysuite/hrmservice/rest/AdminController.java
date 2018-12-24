package com.synergysuite.hrmservice.rest;

import com.synergysuite.hrmservice.service.AdminService;
import com.synergysuite.hrmservice.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService service;

    @GetMapping("")
    public ResponseEntity getAllAdmins() {
        return ResponseEntity.ok(this.service.getAllAdmins());
    }

    @GetMapping("/{id}")
    public ResponseEntity getAdminById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(this.service.getAdminById(id));
        } catch (ServiceException e) {
            return e.toResponse();
        }
    }

    @GetMapping("/update/{id}/{email}/{password}")
    public ResponseEntity updateEmployeeRest(@PathVariable("id") Long id, @PathVariable("email") String email, @PathVariable("password") String password) {
        try {
            return ResponseEntity.ok(this.service.updateAdminRest(id, email, password));
        } catch (ServiceException e) {
            return e.toResponse();
        }
    }


}
