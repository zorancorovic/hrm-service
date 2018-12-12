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
    public void shouldListEmployees(){
        List<Employee> allEmployees = this.service.getAllEmployees();
        assertThat(allEmployees, is(not(empty())));
    }

    @Test
    public void shouldAddEmployee() throws ServiceException {
        Employee e = new Employee(null, "test2", "test2");
        Employee savedEmployee = this.service.saveEmployee(e);
    }

    @Test(expected = ServiceException.class)
    public void shouldNotAddEmployeeIfNullFirstLastName() throws ServiceException {
        this.service.saveEmployee(new Employee(null, null, "test"));
    }

}
