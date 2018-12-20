package com.synergysuite.hrmservice;

import com.synergysuite.hrmservice.model.Employee;
import com.synergysuite.hrmservice.service.EmployeeService;
import com.synergysuite.hrmservice.service.exceptions.ServiceException;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;

import static org.hamcrest.Matchers.*;

import org.junit.Test;
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

    @Autowired
    private EmployeeService service;

    @Test
    public void shouldListEmployees() {
        System.out.println("1");
        List<Employee> allEmployees = this.service.getAllEmployees();
        assertThat(allEmployees, is(not(empty())));

    }

    @Test
    public void shouldAddEmployee() throws ServiceException {
        System.out.println("2");
        Employee e = new Employee(null, 1, "test2", "test2", "test@test.com", Boolean.TRUE);
        Employee e1 = new Employee(null, 1, "test2", "test2", "test@test.com", Boolean.FALSE);
        Employee savedEmployee = this.service.saveEmployee(e);
        Employee savedEmployeee = this.service.saveEmployee(e1);
    }

    @Test(expected = ServiceException.class)
    public void shouldNotAddEmployeeIfNullFirstLastName() throws ServiceException {
        System.out.println("3");
        this.service.saveEmployee(new Employee(null, 1, "test", null, "email", Boolean.TRUE));
    }

    @Test
    public void shouldDeleteUser() throws ServiceException {
        System.out.println("4");
        Employee e = this.service.deleteEmployee(10L);
        System.out.println("Nesto");
    }

    @Test(expected = ServiceException.class)
    public void shouldNotDeleteUser() throws ServiceException {
        System.out.println("5");
        Employee e = this.service.deleteEmployee(null);
    }


    @Test
    public void shouldDeleteUserRest() throws ServiceException {
        System.out.println("6");
        Employee e = this.service.deleteEmployeeRest(10L);
    }

    @Test(expected = ServiceException.class)
    public void shouldNotDeleteUSerRest() throws ServiceException {
        System.out.println("7");
        Employee e = this.service.deleteEmployeeRest(null);
    }
}
