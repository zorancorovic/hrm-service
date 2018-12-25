package com.synergysuite.hrmservice;

import com.synergysuite.hrmservice.model.Employee;
import com.synergysuite.hrmservice.model.Worker_shift;
import com.synergysuite.hrmservice.service.EmployeeService;
import com.synergysuite.hrmservice.service.WorkerShiftService;
import com.synergysuite.hrmservice.service.exceptions.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkerShiftTest {

    @Autowired
    private WorkerShiftService service;

    @Test
    public void shouldListWorkerShifts() {
        List<Worker_shift> allWorkerShifts = this.service.getAllWorkerShifts();
        assertThat(allWorkerShifts, is(not(empty())));
    }

    @Test
    public void shouldListWorkerShiftById() throws ServiceException {
        Worker_shift ws = this.service.getWorkerShiftById(10002L);
    }

    @Test(expected = ServiceException.class)
    public void shouldNotListWorkerShiftById() throws ServiceException {
        Worker_shift ws = this.service.getWorkerShiftById(null);
    }

    @Test
    public void shouldAddWorkerShiftRest() throws ServiceException {
        LocalDate date = LocalDate.now();
        //needs to be edited for the main base- main base is using TIMESTAMP() h2 base is not supporting TIMESTAMP() so instead we are using LacalDate / Date
        Worker_shift worker_shift = this.service.saveWorkerShiftRest(new Worker_shift(null, date, date.plusDays(2), 10000L, 111L, date, 10000000001L));
    }

    ////////
    @Test(expected = ServiceException.class)
    public void shouldNotAddWorkerShiftIdNotNullRest() throws ServiceException {
        LocalDate date = LocalDate.now();
        //needs to be edited for the main base- main base is using TIMESTAMP() h2 base is not supporting TIMESTAMP() so instead we are using LacalDate / Date
        Worker_shift worker_shift = this.service.saveWorkerShiftRest(new Worker_shift(1L, date, date.plusDays(2), 10000L, 111L, date, 10000000001L));
    }

    @Test(expected = ServiceException.class)
    public void shouldNotAddWorkerShiftObjectNullRest() throws ServiceException {
        LocalDate date = LocalDate.now();
        //needs to be edited for the main base- main base is using TIMESTAMP() h2 base is not supporting TIMESTAMP() so instead we are using LacalDate / Date
        Worker_shift worker_shift = this.service.saveWorkerShiftRest(null);
    }

    @Test(expected = ServiceException.class)
    public void shouldNotAddWorkerShiftClockInNullRest() throws ServiceException {
        LocalDate date = LocalDate.now();
        //needs to be edited for the main base- main base is using TIMESTAMP() h2 base is not supporting TIMESTAMP() so instead we are using LacalDate / Date
        Worker_shift worker_shift = this.service.saveWorkerShiftRest(new Worker_shift(null, null, date.plusDays(2), 10000L, 111L, date, 10000000001L));
    }

    @Test(expected = ServiceException.class)
    public void shouldNotAddWorkerShiftWorkerIdNullRest() throws ServiceException {
        LocalDate date = LocalDate.now();
        //needs to be edited for the main base- main base is using TIMESTAMP() h2 base is not supporting TIMESTAMP() so instead we are using LacalDate / Date
        Worker_shift worker_shift = this.service.saveWorkerShiftRest(new Worker_shift(null, date, date.plusDays(2), null, 111L, date, 10000000001L));
    }

    @Test(expected = ServiceException.class)
    public void shouldNotAddWorkerShiftShiftIdNullRest() throws ServiceException {
        LocalDate date = LocalDate.now();
        //needs to be edited for the main base- main base is using TIMESTAMP() h2 base is not supporting TIMESTAMP() so instead we are using LacalDate / Date
        Worker_shift worker_shift = this.service.saveWorkerShiftRest(new Worker_shift(null, date, date.plusDays(2), 10000L, null, date, 10000000001L));
    }

    @Test(expected = ServiceException.class)
    public void shouldNotAddWorkerShiftDateNullRest() throws ServiceException {
        LocalDate date = LocalDate.now();
        //needs to be edited for the main base- main base is using TIMESTAMP() h2 base is not supporting TIMESTAMP() so instead we are using LacalDate / Date
        Worker_shift worker_shift = this.service.saveWorkerShiftRest(new Worker_shift(null, date, date.plusDays(2), 10000L, 111L, null, 10000000001L));
    }

    @Test(expected = ServiceException.class)
    public void shouldNotAddWorkerShiftBranchIdNullRest() throws ServiceException {
        LocalDate date = LocalDate.now();
        //needs to be edited for the main base- main base is using TIMESTAMP() h2 base is not supporting TIMESTAMP() so instead we are using LacalDate / Date
        Worker_shift worker_shift = this.service.saveWorkerShiftRest(new Worker_shift(null, date, date.plusDays(2), 10000L, 111L, date, null));
    }


    @Test
    public void shouldUpdateWorkerShiftRest() throws ServiceException {
        LocalDate date = LocalDate.now();
        Worker_shift worker_shift = this.service.updateWorkerShiftRest(10002L, date.plusDays(1), date.plusDays(1), 10000L, 111L, 10000000002L, date);
    }


    //////
    @Test(expected = ServiceException.class)
    public void shouldNotUpdateWorkerShiftIdNullRest() throws ServiceException {
        LocalDate date = LocalDate.now();
        Worker_shift worker_shift = this.service.updateWorkerShiftRest(null, date.plusDays(1), date.plusDays(1), 10000L, 111L, 10000000002L, date);
    }
    @Test(expected = ServiceException.class)
    public void shouldNotUpdateWorkerShiftClockInNullRest() throws ServiceException {
        LocalDate date = LocalDate.now();
        Worker_shift worker_shift = this.service.updateWorkerShiftRest(10002L, null, date.plusDays(1), 10000L, 111L, 10000000002L, date);
    }
    @Test(expected = ServiceException.class)
    public void shouldNotUpdateWorkerShiftWorkerIdNullRest() throws ServiceException {
        LocalDate date = LocalDate.now();
        Worker_shift worker_shift = this.service.updateWorkerShiftRest(10002L, date.plusDays(1), date.plusDays(1), null, 111L, 10000000002L, date);
    }
    @Test(expected = ServiceException.class)
    public void shouldNotUpdateWorkerShiftShiftIdNullRest() throws ServiceException {
        LocalDate date = LocalDate.now();
        Worker_shift worker_shift = this.service.updateWorkerShiftRest(10002L, date.plusDays(1), date.plusDays(1), 10000L, null, 10000000002L, date);
    }
    @Test(expected = ServiceException.class)
    public void shouldNotUpdateWorkerShifBranchIdNullRest() throws ServiceException {
        LocalDate date = LocalDate.now();
        Worker_shift worker_shift = this.service.updateWorkerShiftRest(10002L, date.plusDays(1), date.plusDays(1), 10000L, 111L, null, date);
    }
    @Test(expected = ServiceException.class)
    public void shouldNotUpdateWorkerShiftDateNullRest() throws ServiceException {
        LocalDate date = LocalDate.now();
        Worker_shift worker_shift = this.service.updateWorkerShiftRest(10002L, date.plusDays(1), date.plusDays(1), 10000L, 111L, 10000000002L, null);
    }









}
