package com.synergysuite.hrmservice.service;


import com.synergysuite.hrmservice.model.Worker_shift;
import com.synergysuite.hrmservice.service.exceptions.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Service
@RestController
public class WorkerShiftService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Worker_shift> getAllWorkerShifts() {
        return this.entityManager.createNamedQuery(Worker_shift.GET_ALL, Worker_shift.class).getResultList();
    }


    public Worker_shift getWorkerShiftById(Long id) throws ServiceException {
        if (id == null) {
            throw new ServiceException("Id supplied is null.");
        }

        Worker_shift ws = entityManager.find(Worker_shift.class, id);
        if (ws == null) {
            throw new ServiceException("Worker_shift with id: " + id + " doesn't exist");
        }
        return ws;
    }


    //Date needs to be validated;
    public List<Worker_shift> getWorkerShiftsByDate(LocalDate date) throws ServiceException {
        if (date == null)
            throw new ServiceException("Date is not valid");
        return this.entityManager.createNamedQuery(Worker_shift.GET_BY_DATE, Worker_shift.class).setParameter(1, date.toString()).getResultList();
    }

    @Transactional
    public Worker_shift saveWorkerShiftRest(@RequestBody Worker_shift ws) throws ServiceException {
        validateWorkerShift(ws);
        if (ws.getId() == null) {
            this.entityManager.persist(ws);
            return ws;
        }
        throw new ServiceException("Workers id exists already");
    }


    @Transactional
    public Worker_shift updateWorkerShiftRest(Long id, LocalDate clock_in, LocalDate clock_out, Long worker_id, Long shift_id, Long branch_id, LocalDate date) throws ServiceException {
        Worker_shift ws = getWorkerShiftById(id);
        ws.setClock_in(clock_in);
        ws.setClock_out(clock_out);
        ws.setWorker_id(worker_id);
        ws.setShift_id(shift_id);
        ws.setBranch_id(branch_id);
        ws.setDate(date);
        validateWorkerShift(ws);
        return this.entityManager.merge(ws);
    }


    public void validateWorkerShift(Worker_shift ws) throws ServiceException {
        if (ws == null || ws.getClock_in() == null || ws.getShift_id() == null || ws.getWorker_id() == null || ws.getBranch_id() == null || ws.getDate() == null)
            throw new ServiceException("Invalid data sent");
    }


}
