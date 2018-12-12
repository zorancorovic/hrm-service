package com.synergysuite.hrmservice.service;

import com.synergysuite.hrmservice.model.Employee;
import com.synergysuite.hrmservice.service.exceptions.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class EmployeeService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Employee> getAllEmployees() {
        return this.entityManager.createNamedQuery(Employee.GET_ALL, Employee.class).getResultList();
    }

    @Transactional
    public Employee saveEmployee(Employee e) throws ServiceException {
        validateEmployee(e);
        if(e.getId() == null) {
            this.entityManager.persist(e);
            return e;
        }else{
            return this.entityManager.merge(e);
        }
    }

    private void validateEmployee(Employee e) throws ServiceException {
        if(e == null || e.getFirstName() == null || e.getLastName() == null)
            throw new ServiceException("Invalid data sent");
    }
}
