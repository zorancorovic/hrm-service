package com.synergysuite.hrmservice.service;

import com.synergysuite.hrmservice.model.Branch;
import com.synergysuite.hrmservice.model.Employee;
import com.synergysuite.hrmservice.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Service
@RestController
public class BranchService {

    @Autowired
    private EntityManager entityManager;


    public Branch getBranchById(Long id) throws ServiceException {

        if (id == null) {
            throw new ServiceException("id suplied is invalid.");
        }
        Branch b = this.entityManager.createNamedQuery(Branch.GET_BY_ID, Branch.class).setParameter(1, id).setMaxResults(1).getSingleResult();
        if (b == null) {
            throw new ServiceException("Brach with this id don't exist.");
        }
        return b;
    }

    public List<Branch> getAllBranches() throws ServiceException {
        return this.entityManager.createNamedQuery(Branch.GET_ALL, Branch.class).getResultList();
    }


    @Transactional
    public Branch deleteBranch(Long id) throws ServiceException {
        if (id == null) {
            throw new ServiceException("Id suplied is null.");
        }
        Branch branch = null;
        try {
            branch = this.entityManager.createNamedQuery(Branch.GET_BY_ID, Branch.class).setParameter(1, id).setMaxResults(1).getSingleResult();
            branch.setActive(false);
            return this.entityManager.merge(branch);
        } catch (NoResultException ex) {
            throw new ServiceException("Branch with the id of " + id + "doesn't exists");
        }
    }

    public void validateBrandh(Branch b) throws ServiceException {
        if (b.getAddress() == null || b.getCity() == null || b.getEmail() == null || b.getName() == null || b.getPassword() == null) {
            throw new ServiceException("Data suplied is not valid.");
        }
        List<Branch> branches = this.entityManager.createNamedQuery(Branch.GET_BY_MAIL, Branch.class).setParameter(1, b.getEmail()).getResultList();
        if (!branches.isEmpty()) {
            throw new ServiceException("This email already exist.");
        }
    }

    @Transactional
    public Branch saveBranch(Branch b) throws ServiceException{
        validateBrandh(b);
        this.entityManager.persist(b);
        return b;
    }

    @Transactional
    public Branch updateBranch(Long id, String address, String city, String email, String name, String password, boolean active) throws ServiceException {



        Branch b = new Branch();
        b.setId(id);
        b.setAddress(address);
        b.setActive(active);
        b.setCity(city);
        b.setEmail(email);
        b.setName(name);
        b.setPassword(password);

        getBranchById(id);
        validateBrandh(b);

        return this.entityManager.merge(b);
    }
}