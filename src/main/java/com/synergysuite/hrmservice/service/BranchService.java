package com.synergysuite.hrmservice.service;

import com.synergysuite.hrmservice.model.Branch;
import com.synergysuite.hrmservice.model.Employee;
import com.synergysuite.hrmservice.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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


    public List<Branch> getAllBranches() {
        return this.entityManager.createNamedQuery(Branch.GET_ALL, Branch.class).getResultList();
    }

    @Transactional
    @GetMapping("/branch/{id}")
    public Branch deleteBranch(@PathVariable Long id) throws ServiceException {
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
}