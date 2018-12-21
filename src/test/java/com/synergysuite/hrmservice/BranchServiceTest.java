package com.synergysuite.hrmservice;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.synergysuite.hrmservice.model.Branch;
import com.synergysuite.hrmservice.service.BranchService;
import com.synergysuite.hrmservice.service.exceptions.ServiceException;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.NoResultException;
import javax.xml.ws.Service;
import java.util.List;

import static com.fasterxml.jackson.databind.ObjectWriter.Prefetch.empty;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Java6Assertions.not;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BranchServiceTest {

    @Autowired
    private BranchService service;

    @Test
    public void shouldListBranches() throws ServiceException {
        List<Branch> branches = this.service.getAllBranches();
        assertThat(branches, Is.is(Matchers.not(Matchers.empty())));
    }

    @Test
    public void shouldDeleteBranches() throws ServiceException {
        Branch b = service.deleteBranch(10000000001L);
    }

    @Test(expected = ServiceException.class)
    public void shouldNotDeleteBranches() throws ServiceException {
        Branch b = service.deleteBranch(1000220001L);
    }

    @Test
    public void shouldReturnBranchById() throws ServiceException {
        Branch b = service.getBranchById(10000000001L);
    }

    @Test(expected = NoResultException.class)
    public void shouldNotReturnBranchById() throws ServiceException {
        Branch b = service.getBranchById(1000000001L);
    }

    @Test(expected = ServiceException.class)
    public void shouldNotReturnBranchById2() throws ServiceException {
        Branch b = service.getBranchById(null);
    }

    @Test
    public void shouldValidateBranch() throws ServiceException {
        Branch b = service.getBranchById(10000000001L);
        b.setEmail("test@.mail.unique");
        service.validateBrandh(b);
    }

    @Test(expected = ServiceException.class)
    public void shouldNotValidateBranch() throws ServiceException {
        service.validateBrandh(service.getBranchById(10000000001L));
    }

    @Test(expected = ServiceException.class)
    public void shouldNotValidate() throws ServiceException {
        Branch b = service.getBranchById(10000000001L);
        b.setEmail(null);
        service.validateBrandh(b);
    }

    @Test(expected = ServiceException.class)
    public void shouldNotValidate2() throws ServiceException {
        Branch b = service.getBranchById(10000000001L);
        b.setName(null);
        service.validateBrandh(b);
    }

    @Test(expected = ServiceException.class)
    public void shouldNotValidate3() throws ServiceException {
        Branch b = service.getBranchById(10000000001L);
        b.setAddress(null);
        service.validateBrandh(b);
    }

    @Test(expected = ServiceException.class)
    public void shouldNotValidate4() throws ServiceException {
        Branch b = service.getBranchById(10000000001L);
        b.setCity(null);
        service.validateBrandh(b);
    }

    @Test(expected = ServiceException.class)
    public void shouldNotValidate5() throws ServiceException {
        Branch b = service.getBranchById(10000000001L);
        b.setPassword(null);
        service.validateBrandh(b);
    }

    @Test(expected = ServiceException.class)
    public void shouldNotSaveBranch() throws ServiceException {
        Branch b = service.getBranchById(10000000001L);
        b.setId(null);
        service.saveBranch(b);
    }

    @Test(expected = ServiceException.class)
    public void shouldNotSaveBranch2() throws ServiceException {
        Branch b = new Branch();
        b.setAddress("1");
        b.setActive(true);
        b.setCity(null);
        b.setEmail("1000000");
        b.setName("1");
        b.setPassword("1");
        service.saveBranch(b);
    }

    @Test
    public void shouldSaveBranch() throws ServiceException {
        Branch b = new Branch();
        b.setAddress("1");
        b.setActive(true);
        b.setCity("1");
        b.setEmail("1000000");
        b.setName("1");
        b.setPassword("1");
        service.saveBranch(b);
    }

    @Test
    public void shouldUprdateBranch() throws ServiceException{
        service.updateBranch(10000000001L,"adr","cit","ema","name","pass",true);
    }
    @Test(expected = NoResultException.class)
    public void shouldNotUprdateBranch() throws ServiceException{
        service.updateBranch(10002320001L,"adr","cit","ema","name","pass",true);
    }
    @Test(expected = ServiceException.class)
    public void shouldNotUprdateBranch2() throws ServiceException{
        service.updateBranch(10000000001L,null,"cit","ema","name","pass",true);
    }
}