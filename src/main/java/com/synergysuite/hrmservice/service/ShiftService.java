package com.synergysuite.hrmservice.service;

import com.synergysuite.hrmservice.model.Shift;
import com.synergysuite.hrmservice.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Date;
import java.util.List;

@Service
@RestController
public class ShiftService {

    @Autowired
    EntityManager entityManager;

    public List<Shift> getAllShifts() throws ServiceException {
        return this.entityManager.createNamedQuery(Shift.GET_ALL_SHIFTS, Shift.class).getResultList();
    }

    public void validateShift(Shift s) throws ServiceException {

        if (s.getStart() == null || s.getEnd() == null) {
            throw new ServiceException("Data suplied are not valid.");
        }
    }

    @Transactional
    public Shift createShift(Shift s) throws ServiceException {
        validateShift(s);
        this.entityManager.persist(s);
        return s;
    }

    @Transactional
    public Shift updateShift(Shift s) throws ServiceException{

        Shift pr = this.entityManager.createNamedQuery(Shift.GET_SHIFT_BY_ID, Shift.class).setParameter(1, s.getId()).setMaxResults(1).getSingleResult();
        if(pr == null){
            throw new ServiceException("Shift with this id don't exist.");
        }
        s.setEnd(s.getEnd());
        s.setStart(s.getStart());
        validateShift(s);
        entityManager.merge(s);
        return s;
    }

    public Shift getShiftById(Long id) throws NoResultException {
        return this.entityManager.createNamedQuery(Shift.GET_SHIFT_BY_ID,Shift.class).setParameter(1,id).setMaxResults(1).getSingleResult();
    }
}
