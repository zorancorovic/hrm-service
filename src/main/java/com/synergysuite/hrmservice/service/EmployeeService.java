package com.synergysuite.hrmservice.service;

import com.synergysuite.hrmservice.JPARepository.EmployeeJPARepository;
import com.synergysuite.hrmservice.model.Employee;
import com.synergysuite.hrmservice.service.exceptions.ServiceException;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController
public class EmployeeService {

    @Autowired
    private EmployeeJPARepository employeeJPARepository;


    @PersistenceContext
    private EntityManager entityManager;

    public List<Employee> getAllEmployees() {
        return this.entityManager.createNamedQuery(Employee.GET_ALL, Employee.class).getResultList();
    }

    @Transactional
    public Employee saveEmployee(Employee e) throws ServiceException {
        validateEmployee(e);
        if (e.getId() == null) {
            this.entityManager.persist(e);
            return e;
        } else {
            return this.entityManager.merge(e);
        }
    }


    @Transactional
    public Employee deleteEmployee(Long id) throws ServiceException {
        if (id == null)
            throw new ServiceException("Id supplied is null.");
        Employee employee = null;
        try {
            employee = this.entityManager.createNamedQuery(Employee.GET_BY_ID, Employee.class).setParameter(1, id).setMaxResults(1).getSingleResult();
            employee.setActive(false);
            return this.entityManager.merge(employee);
        } catch (NoResultException e) {
            throw new ServiceException("Employee with " + id + " does not exist");
        }
    }


    //REST
    @Transactional
    @GetMapping("/employee/delete/{id}")
    public Employee deleteEmployeeRest(@PathVariable Long id) throws ServiceException {
        if (id == null) {
            throw new ServiceException("Id supplied is null.");
        }
        Employee employee = null;
        try {
            employee = this.entityManager.createNamedQuery(Employee.GET_BY_ID, Employee.class).setParameter(1, id).setMaxResults(1).getSingleResult();
            employee.setActive(false);
            return this.entityManager.merge(employee);
        }catch( NoResultException ex){
            throw new ServiceException("Employee with " + id + " does not exist!");
        }
    }

    @Transactional
    @GetMapping("/employee/update/{id}/{firstName}/{lastName}/{email}")
    public Employee updateEmployeeRest(@PathVariable Long id, @PathVariable String firstName, @PathVariable  String lastName, @PathVariable String email) throws ServiceException {
        Employee e = getEmployeeById(id);
        e.setFirstName(firstName);
        e.setLastName(lastName);
        e.setEmail(email);
        validateEmployee(e);
        return this.entityManager.merge(e);

    }


    private void validateEmployee(Employee e) throws ServiceException {
        if (e == null || e.getFk_branch_id() == null || e.getFirstName() == null || e.getLastName() == null || e.getEmail() == null)
            throw new ServiceException("Invalid data sent");
    }

    public Employee getEmployeeById(Long id) throws ServiceException {
        if(id == null){
            throw new ServiceException("Id supplied is null.");
        }

        Employee e = entityManager.find(Employee.class, id);
            if(e==null){
                throw new ServiceException("Employee with id: " + id +" doesn't exist");
            }
        return e;
    }
}
