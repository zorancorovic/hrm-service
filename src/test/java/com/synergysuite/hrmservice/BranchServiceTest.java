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
        System.out.println("1");
        List<Branch> branches = this.service.getAllBranches();
        assertThat(branches, Is.is(Matchers.not(Matchers.empty())));
    }

    @Test
    public void shouldDeleteBranches() throws ServiceException {
        System.out.println("2");
        Branch b = service.deleteBranch(10000000001L);
    }

    @Test(expected = ServiceException.class)
    public void shouldNotDeleteBranches() throws ServiceException {
        System.out.println("3");
        Branch b = service.deleteBranch(1000220001L);
    }

    @Test
    public void ShouldReturnBranchById() throws ServiceException {
        System.out.println("4");
        Branch b = service.getBranchById(10000000001L);
    }

    @Test(expected = NoResultException.class)
    public void ShouldNotReturnBranchById() throws ServiceException {
        System.out.println("5");
        Branch b = service.getBranchById(1000000001L);
    }

    @Test
    public void ShouldValidateBranch() throws ServiceException {
        System.out.println("6");
        Branch b = service.getBranchById(10000000001L);
        b.setEmail("test@.mail.unique");
        service.validateBrandh(b);
    }

    @Test(expected = ServiceException.class)
    public void ShouldNotValidateBranch() throws ServiceException {
        System.out.println("7");
        service.validateBrandh(service.getBranchById(10000000001L));
    }

    @Test(expected = ServiceException.class)
    public void ShouldNotValidate() throws ServiceException {
        System.out.println("8");
        Branch b = service.getBranchById(10000000001L);
        b.setEmail(null);
        service.validateBrandh(b);
    }

    @Test(expected = ServiceException.class)
    public void ShouldNotValidate2() throws ServiceException {
        System.out.println("9");
        Branch b = service.getBranchById(10000000001L);
        b.setName(null);
        service.validateBrandh(b);
    }

    @Test(expected = ServiceException.class)
    public void ShouldNotValidate3() throws ServiceException {
        System.out.println("10");
        Branch b = service.getBranchById(10000000001L);
        b.setAddress(null);
        service.validateBrandh(b);
    }

    @Test(expected = ServiceException.class)
    public void ShouldNotValidate4() throws ServiceException {
        System.out.println("11");
        Branch b = service.getBranchById(10000000001L);
        b.setCity(null);
        service.validateBrandh(b);
    }

    @Test(expected = ServiceException.class)
    public void ShouldNotValidate5() throws ServiceException {
        System.out.println("12");
        Branch b = service.getBranchById(10000000001L);
        b.setPassword(null);
        service.validateBrandh(b);
    }

    @Test(expected = ServiceException.class)
    public void ShouldNotSaveBranch() throws ServiceException {
        System.out.println("13");
        Branch b = service.getBranchById(10000000001L);
        b.setId(null);
        service.saveBranch(b);
    }

    @Test
    public void ShouldSaveBranch() throws ServiceException {
        System.out.println("14");
        Branch b = new Branch();
        b.setAddress("1");
        b.setActive(true);
        b.setCity("1");
        b.setEmail("1000000");
        b.setName("1");
        b.setPassword("1");
        service.saveBranch(b);
    }
}