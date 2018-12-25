package com.synergysuite.hrmservice;

import com.synergysuite.hrmservice.model.Shift;
import com.synergysuite.hrmservice.service.ShiftService;
import com.synergysuite.hrmservice.service.exceptions.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.NoResultException;
import java.sql.Time;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiftTest {

    @Autowired
    ShiftService shiftService;

    @Test
    public void shouldValidateShift() throws NoResultException, ServiceException {
        Shift s = shiftService.getShiftById(1000000000002L);
        shiftService.validateShift(s);

    }

    @Test(expected = ServiceException.class)
    public void shoudlNotValidateShift() throws ServiceException, NoResultException{
        Shift s = shiftService.getShiftById(1000000000002L);
        s.setEnd(null);
        shiftService.validateShift(s);
    }

    @Test(expected = ServiceException.class)
    public void shoudlNotValidateShift2() throws ServiceException, NoResultException{
        Shift s = shiftService.getShiftById(1000000000002L);
        s.setStart(null);
        shiftService.validateShift(s);
    }

    @Test(expected = NoResultException.class)
    public void shouldNotUpdate() throws ServiceException{
        Shift s = shiftService.getShiftById(1000000000002L);
        s.setId(12222222L);
        shiftService.updateShift(s);
    }
    public void shouldUpdate() throws ServiceException{
        Shift s = shiftService.getShiftById(1000000000002L);
        s.setStart(Time.valueOf("22:22:22"));
        shiftService.updateShift(s);
    }

    @Test(expected = ServiceException.class)
    public void ShouldNOtUpdate2() throws ServiceException{
        Shift s = shiftService.getShiftById(1000000000002L);
        s.setStart(null);
        shiftService.updateShift(s);
    }

    @Test
    public void shouldCreate() throws ServiceException{
        Shift s = shiftService.getShiftById(1000000000002L);
        s.setId(null);
        shiftService.createShift(s);
    }

    @Test(expected = ServiceException.class)
    public void shouldNotCreate() throws ServiceException{
        Shift s = shiftService.getShiftById(1000000000002L);
        s.setEnd(null);
        shiftService.createShift(s);
    }

    @Test(expected = ServiceException.class)
    public void shouldNotCreate2() throws ServiceException{
        Shift s = shiftService.getShiftById(1000000000002L);
        s.setStart(null);
        shiftService.createShift(s);
    }
}