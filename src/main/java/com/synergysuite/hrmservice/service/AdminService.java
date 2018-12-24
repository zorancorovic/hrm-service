package com.synergysuite.hrmservice.service;


import com.synergysuite.hrmservice.model.Admin;
import com.synergysuite.hrmservice.model.Employee;
import com.synergysuite.hrmservice.service.exceptions.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@RestController
public class AdminService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Admin> getAllAdmins() {
        return this.entityManager.createNamedQuery(Admin.GET_ALL, Admin.class).getResultList();
    }


    //REST
    @Transactional
    public Admin updateAdminRest(Long id, String email, String password) throws ServiceException {
        Admin a = getAdminById(id);
        a.setPassword(password);
        a.setEmail(email);
        validateAdmin(a);
        return this.entityManager.merge(a);
    }

    private void validateAdmin(Admin a) throws ServiceException {
        if (a == null || a.getEmail() == null || a.getPassword() == null)
            throw new ServiceException("Invalid data sent");
    }


    public Admin getAdminById(Long id) throws ServiceException {
        if (id == null) {
            throw new ServiceException("Id supplied is null.");
        }

        Admin a = entityManager.find(Admin.class, id);
        if (a == null) {
            throw new ServiceException("Admin with id: " + id + " doesn't exist");
        }
        return a;
    }


}
