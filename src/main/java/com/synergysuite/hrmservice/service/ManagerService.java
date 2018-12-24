package com.synergysuite.hrmservice.service;

import com.synergysuite.hrmservice.model.Branch;
import com.synergysuite.hrmservice.model.Manager;
import com.synergysuite.hrmservice.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.List;

@Service
@RestController
public class ManagerService {

    @Autowired
    EntityManager entityManager;

    public List<Manager> getAllManagers() throws ServiceException {
        return this.entityManager.createNamedQuery(Manager.GET_ALL_MANAGERS, Manager.class).getResultList();
    }

    public Manager getManagerbyId(Long id) throws ServiceException {
        if (id == null) {
            throw new ServiceException("Id suplied is invalid");
        }
        Manager man = this.entityManager.createNamedQuery(Manager.GET_BY_ID, Manager.class).setParameter(1, id).setMaxResults(1).getSingleResult();
        if (man == null) {
            throw new ServiceException("Manager with this id doesn't exist.");
        }
        return man;
    }

    public Manager validateManager(Manager m) throws ServiceException {
        if (m == null || m.getEmail() == null || m.getFk_branch_id() == null || m.getLast_name() == null || m.getName() == null || m.getPassword() == null) {
            throw new ServiceException("Data suplied for manager are not valid.");
        }
        List<Manager> managers = this.entityManager.createNamedQuery(Manager.GET_BY_MAIL, Manager.class).setParameter(1, m.getEmail()).getResultList();
        if (!managers.isEmpty()) {
            throw new ServiceException("This email already exist.");
        }
        return m;
    }

    @Transactional
    public Manager updateManager(Long id,Long fk_branch_id, String password, String email, String name, String lastName, boolean active ) throws ServiceException {

        Manager m = new Manager(id ,name, lastName, email, password,fk_branch_id,active);
        if (m == null || m.getEmail() == null || m.getFk_branch_id() == null || m.getLast_name() == null || m.getName() == null || m.getPassword() == null) {
            throw new ServiceException("Data suplied for manager are not valid.");
        }
        getManagerbyId(id);
        return this.entityManager.merge(m);
    }
    @Transactional
    public Manager deleteManager(Long id) throws ServiceException {
        if (id == null) {
            throw new ServiceException("Id suplied is null.");
        }
        Manager m = null;
        try {
            m = this.entityManager.createNamedQuery(Manager.GET_BY_ID, Manager.class).setParameter(1, id).setMaxResults(1).getSingleResult();
            m.setActive(false);
            return this.entityManager.merge(m);
        } catch (NoResultException ex) {
            throw new ServiceException("Manager with the id of " + id + "doesn't exists");
        }
    }

    @Transactional
    public Manager saveManager(Manager m) throws ServiceException{
        validateManager(m);
        this.entityManager.persist(m);
        return m;
    }
}