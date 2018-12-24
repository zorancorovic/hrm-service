package com.synergysuite.hrmservice.rest;

import com.synergysuite.hrmservice.model.Branch;
import com.synergysuite.hrmservice.model.Manager;
import com.synergysuite.hrmservice.service.ManagerService;
import com.synergysuite.hrmservice.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@Controller
@RequestMapping("/managers")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping("/")
    public ResponseEntity getAllManagers() {
        try {
            return ResponseEntity.ok(this.managerService.getAllManagers());
        } catch (ServiceException e) {
            return e.toResponse();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getManagerById(@PathVariable("id") Long id) throws ServiceException {
        try {
            return ResponseEntity.ok(managerService.getManagerbyId(id));
        } catch (ServiceException e) {
            return e.toResponse();
        }
    }

    @PutMapping("/update/{id}/{name}/{lastname}/{fk_branch_id}/{email}/{active}/{password}")
    public ResponseEntity updateManager(@PathVariable("id") Long id, @PathVariable("name") String name, @PathVariable("lastname") String lastname, @PathVariable("fk_branch_id") Long fk_branch_id, @PathVariable("email") String email, @PathVariable("active") boolean active, @PathVariable("password") String password) throws ServiceException {
        try {
            return ResponseEntity.ok(managerService.updateManager(id, fk_branch_id, password, email, name, lastname, active));
        } catch (ServiceException e) {
            return e.toResponse();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMenager(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(managerService.deleteManager(id));
        } catch (ServiceException e) {
            return e.toResponse();
        }
    }

    @PostMapping("/save/{name}/{lastname}/{email}/{password}/{fk_branch_id}/{active}")
    public ResponseEntity saveManager(@PathVariable("name") String name, @PathVariable("lastname") String lastname, @PathVariable("email") String email, @PathVariable("password") String password, @PathVariable("fk_branch_id") Long fk_branch_id, @PathVariable("active") boolean active) {
        Manager m = new Manager(name,lastname,email,password,fk_branch_id,active);
        try {
            return ResponseEntity.ok(this.managerService.saveManager(m));
        } catch (ServiceException e) {
            return e.toResponse();
        }
    }
}
