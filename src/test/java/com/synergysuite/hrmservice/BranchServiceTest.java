package com.synergysuite.hrmservice;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.synergysuite.hrmservice.model.Branch;
import com.synergysuite.hrmservice.service.BranchService;
import com.synergysuite.hrmservice.service.exceptions.ServiceException;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void shouldListEmployees(){
        System.out.println("1");
        List<Branch> branches = this.service.getAllBranches();
        assertThat(branches, Is.is(Matchers.not(Matchers.empty())));
    }
    @Test
    public void shouldDeleteEmployee() throws ServiceException {
        System.out.println("2");
        Branch b = service.deleteBranch(1L);
    }

}
