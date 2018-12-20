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


    @Transactional
    @GetMapping("/employee/{id}")
    public Employee deleteEmployeeRest(@PathVariable Long id) throws ServiceException {
        if (id != null && employeeJPARepository.findById(id) != null) {
            Optional<Employee> e = employeeJPARepository.findById(id);
            Employee ee = e.get();
            ee.setActive(false);
            System.out.println("INFO: " + ee);
            Employee eee = employeeJPARepository.save(ee);
            return eee;
        }
        throw new ServiceException("Employee doesn't exitst");

    }


    private void validateEmployee(Employee e) throws ServiceException {
        if (e == null || e.getFk_branch_id() == null || e.getFirstName() == null || e.getLastName() == null || e.getEmail() == null)
            throw new ServiceException("Invalid data sent");
    }

}
