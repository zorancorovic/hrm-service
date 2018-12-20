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
    public void shouldListBranches() throws ServiceException{
        System.out.println("1");
        List<Branch> branches = this.service.getAllBranches();
        assertThat(branches, Is.is(Matchers.not(Matchers.empty())));
    }
    @Test
    public void shouldDeleteBranches() throws ServiceException {
        System.out.println("2");
        Branch b = service.deleteBranch(1L);
    }

    @Test(expected = ServiceException.class)
    public void shouldNotDeleteBranches() throws ServiceException{
        System.out.println("3");
        Branch b = service.deleteBranch(10000001L);
    }

    @Test
    public void ShouldReturnBranchById() throws ServiceException{
        System.out.println("4");
        Branch b = service.getBranchById(1L);
    }

    @Test(expected = NoResultException.class)
    public void ShouldNotReturnBranchById() throws ServiceException{
        System.out.println("5");
        Branch b = service.getBranchById(1000000001L);
    }

    @Test
    public void ShouldValidateBranch() throws ServiceException{
        System.out.println("6");
        service.validateBrandh(service.getBranchById(1L));
    }




}
