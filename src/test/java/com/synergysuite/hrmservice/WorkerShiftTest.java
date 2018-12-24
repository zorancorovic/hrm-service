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


//    @Test
//    public void shouldAddWorkerShiftRest() throws ServiceException {
//
//        Worker_shift ws = new Worker_shift(null, 1, "test2", "test2", "test@test.com", Boolean.TRUE);
//        Worker_shift worker_shift = this.service.saveWorkerShiftRest(ws);
//    }


}
