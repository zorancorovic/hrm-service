package com.synergysuite.hrmservice;

import com.synergysuite.hrmservice.model.Employee;
import com.synergysuite.hrmservice.service.EmployeeService;
import com.synergysuite.hrmservice.service.exceptions.ServiceException;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;

import static org.hamcrest.Matchers.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

    @Rule
    public ExpectedException thrown =  ExpectedException.none();

    @Autowired
    private EmployeeService service;

    @Test
    public void shouldListEmployees() {
        List<Employee> allEmployees = this.service.getAllEmployees();
        assertThat(allEmployees, is(not(empty())));

    }

    @Test
    public void shouldAddEmployeRest() throws ServiceException {
        Employee e = new Employee(null, 1, "test2", "test2", "test@test.com", Boolean.TRUE);
        Employee savedEmployeee = this.service.saveEmployeeRest(e);
    }

    ////
    @Test
    public void shouldNotAddEmployeeIfNullBranchId() throws ServiceException {
        thrown.expect(ServiceException.class);
        thrown.expectMessage("");
        this.service.saveEmployeeRest(new Employee(null, null, "test", "test", "email", Boolean.TRUE));
    }
    @Test(expected = ServiceException.class)
    public void shouldNotAddEmployeeIfNullFirstName() throws ServiceException {
        this.service.saveEmployeeRest(new Employee(null, 1, null, "test", "email", Boolean.TRUE));
    }
    @Test(expected = ServiceException.class)
    public void shouldNotAddEmployeeIfNullLastName() throws ServiceException {
        this.service.saveEmployeeRest(new Employee(null, 1, "test", null, "email", Boolean.TRUE));
    }
    @Test(expected = ServiceException.class)
    public void shouldNotAddEmployeeIfNullEmail() throws ServiceException {
        this.service.saveEmployeeRest(new Employee(null, 1, "test", "test", null, Boolean.TRUE));
    }




    @Test
    public void shouldDeleteEmployeeRest() throws ServiceException {
        Employee e = this.service.deleteEmployeeRest(10L);
    }

    ////
    @Test(expected = ServiceException.class)
    public void shouldNotDeleteEmployeeRest() throws ServiceException {
        Employee e = this.service.deleteEmployeeRest(null);
    }


    //if employee is deleted (active setted to false)
    @Test
    public void shouldRetrieveEmployeeRest() throws ServiceException {
        Employee e = this.service.retrieveEmployeeRest(10L);
    }

    ////
    @Test(expected = ServiceException.class)
    public void shouldNotRetrieveEmployeeRest() throws ServiceException {
        Employee e = this.service.retrieveEmployeeRest(null);
    }

    @Test
    public void shouldUpdateRest() throws ServiceException {
        Employee e = this.service.updateEmployeeRest(10L, "anton","djokaj","anton@gmail.com");
    }

    ////
    @Test(expected = ServiceException.class)
    public void shouldNotUpdateNullIdRest() throws ServiceException {
        Employee e = this.service.updateEmployeeRest(null, "anton","djokaj","anton@gmail.com");
    }
    @Test(expected = ServiceException.class)
    public void shouldNotUpdateNullFirstNameRest() throws ServiceException {
        Employee e = this.service.updateEmployeeRest(11L, null, "djokaj","anton@gmail.com");
    }
    @Test(expected = ServiceException.class)
    public void shouldNotUpdateNullLastNameRest() throws ServiceException {
        Employee e = this.service.updateEmployeeRest(11L, "anton", null,"anton@gmail.com");
    }
    @Test(expected = ServiceException.class)
    public void shouldNotUpdateNullEmailRest() throws ServiceException {
        Employee e = this.service.updateEmployeeRest(11L, "anton", "djokaj",null);
    }



}
