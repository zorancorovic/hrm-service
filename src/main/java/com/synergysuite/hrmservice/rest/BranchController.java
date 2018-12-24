package com.synergysuite.hrmservice.rest;

import com.synergysuite.hrmservice.model.Branch;
import com.synergysuite.hrmservice.service.BranchService;
import com.synergysuite.hrmservice.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityManager;

@Controller
@RequestMapping(path = "/branches")
public class BranchController {

    @Autowired
    private BranchService branchservice;

    @GetMapping("/{id}")
    private ResponseEntity getBranchById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(this.branchservice.getBranchById(id));
        } catch (ServiceException e) {
            return e.toResponse();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBranchById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(this.branchservice.deleteBranch(id));
        } catch (ServiceException e) {
            return e.toResponse();
        }
    }

    @GetMapping("/")
    public ResponseEntity getAllBranches() {
        try {
            return ResponseEntity.ok(this.branchservice.getAllBranches());
        } catch (ServiceException e) {
            return e.toResponse();
        }
    }

    @PutMapping("/{id}/{address}/{city}/{email}/{name}/{password}/{active}")
    public ResponseEntity updateBranch(@PathVariable("id") Long id, @PathVariable("address") String address, @PathVariable("city") String city, @PathVariable("email") String email, @PathVariable("name") String name, @PathVariable("password") String password, @PathVariable("active") boolean active) {
        try {
            return ResponseEntity.ok(this.branchservice.updateBranch(id,address, city, email, name, password, active));
        } catch (ServiceException e) {
            return e.toResponse();
        }
    }

    @PostMapping("/save/{address}/{city}/{email}/{name}/{password}/{active}")
    public ResponseEntity saveBranch(@PathVariable("address") String address, @PathVariable("city") String city, @PathVariable("email") String email, @PathVariable("name") String name, @PathVariable("password") String password, @PathVariable("active") boolean active) {
        Branch b = new Branch(address, city, email, name, password, active);
        try {
            return ResponseEntity.ok(this.branchservice.saveBranch(b));
        } catch (ServiceException e) {
            return e.toResponse();
        }
    }
}