package com.synergysuite.hrmservice;

import com.synergysuite.hrmservice.model.Admin;
import com.synergysuite.hrmservice.model.Employee;
import com.synergysuite.hrmservice.service.AdminService;
import com.synergysuite.hrmservice.service.exceptions.ServiceException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private AdminService service;

    @Test
    public void shouldListAdmins() {
        List<Admin> allAdmins = this.service.getAllAdmins();
        assertThat(allAdmins, is(not(empty())));
    }

    @Test
    public void shouldUpdateAdminRest() throws ServiceException {
        Admin a = this.service.updateAdminRest(1000L, "anton@anton.net", "anton123");
    }

    @Test(expected = ServiceException.class)
    public void shouldNotUpdateAdminNullIdRest() throws ServiceException {
        Admin a = this.service.updateAdminRest(null, "anton@anton.net", "anton123");
    }


    @Test(expected = ServiceException.class)
    public void shouldNotUpdateAdminNullEmailRest() throws ServiceException {
        Admin a = this.service.updateAdminRest(100L, null, "anton123");
    }


    @Test(expected = ServiceException.class)
    public void shouldNotUpdateAdminNullPasswordRest() throws ServiceException {
        Admin a = this.service.updateAdminRest(200L, "anton@anton.net", null);
    }

}


