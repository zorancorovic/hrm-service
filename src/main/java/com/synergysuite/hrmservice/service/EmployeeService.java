package com.synergysuite.hrmservice.service;

import com.synergysuite.hrmservice.JPARepository.EmployeeJPARepository;
import com.synergysuite.hrmservice.model.Employee;
import com.synergysuite.hrmservice.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
@RestController
public class EmployeeService {
date
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
    public Employee deleteEmployee(Integer id) throws ServiceException {
        List<Employee> employees = getAllEmployees();
        Employee e = null;
        for (Employee emp : employees) {
            if (emp.getId().equals(id)) {
                e = emp;
                break;
            }
        }
        validateEmployee(e);
        if (e.getId() != null) {
            e.setActive(0);
            System.out.println(e);
            this.entityManager.merge(e);
            return e;
        }

        throw new ServiceException("User : (" + e.toString() + ") doesn't exist!");
    }



    @Transactional
    @GetMapping("/employee/{id}")
    public Employee deleteEmployeeRest(@PathVariable Integer id) throws ServiceException {
        if(id != null && employeeJPARepository.findById(id) != null) {
            Optional<Employee> e = employeeJPARepository.findById(id);
            Employee ee = e.get();
            ee.setActive(0);
            System.out.println("INFO: " + ee);
            Employee eee = employeeJPARepository.save(ee);
            return eee;
        }
        throw new ServiceException("Employee doesn't exitst");

    }




    private void validateEmployee(Employee e) throws ServiceException {
        if (e == null || e.getFk_branch_id() == null || e.getFirstName() == null || e.getLastName() == null || e.getEmail() == null || (e.getActive() < 0 || e.getActive() > 1))
            throw new ServiceException("Invalid data sent");
    }

}
