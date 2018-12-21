package com.synergysuite.hrmservice;


import com.synergysuite.hrmservice.model.Manager;
import com.synergysuite.hrmservice.service.ManagerService;
import com.synergysuite.hrmservice.service.exceptions.ServiceException;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerServiceTest {

    @Autowired
    private ManagerService menagerService;

    @Test
    public void shouldShowList() throws ServiceException {
        List<Manager> managers = this.menagerService.getAllManagers();
        assertThat(managers, Is.is(Matchers.not(Matchers.empty())));
    }

    @Test
    public void shouldShowManager() throws ServiceException {
        Manager m = menagerService.getManagerbyId(10000000001l);

        if (m == null) {
            throw new ServiceException("Manager with this id doesn't exist.");
        }
    }

    @Test(expected = NoResultException.class)
    public void shoudlNotShowMenager() throws ServiceException {

        Manager m = menagerService.getManagerbyId(1000012232L);
    }

    @Test(expected = ServiceException.class)
    public void shoudlNotShowMenager1() throws ServiceException {

        Manager m = menagerService.getManagerbyId(null);
    }

    @Test
    public void shouldValidateManager()throws ServiceException{
        Manager m = menagerService.getManagerbyId(10000000001L);
        m.setEmail("exampleuniquemail@gmail.com");
        menagerService.validateManager(m);
    }
    @Test(expected = ServiceException.class)
    public void shouldNotValidateManager() throws ServiceException {
        Manager m = menagerService.getManagerbyId(10000000001L);
        menagerService.validateManager(m);
    }

    @Test(expected = ServiceException.class)
    public void shouldNotValidateManager1() throws ServiceException {
        Manager m = menagerService.getManagerbyId(10000000001L);
        m.setEmail(null);
        menagerService.validateManager(m);
    }
    @Test(expected = ServiceException.class)
    public void shouldNotValidateManager3() throws ServiceException {
        Manager m = menagerService.getManagerbyId(10000000001L);
        m.setFk_branch_id(null);
        menagerService.validateManager(m);
    }
    @Test(expected = ServiceException.class)
    public void shouldNotValidateManager4() throws ServiceException {
        Manager m = menagerService.getManagerbyId(10000000001L);
        m.setLast_name(null);
        menagerService.validateManager(m);
    }
    @Test(expected = ServiceException.class)
    public void shouldNotValidateManager5() throws ServiceException {
        Manager m = menagerService.getManagerbyId(10000000001L);
        m.setName(null);
        menagerService.validateManager(m);
    }
    @Test(expected = ServiceException.class)
    public void shouldNotValidateManager6() throws ServiceException {
        Manager m = menagerService.getManagerbyId(10000000001L);
        m.setPassword(null);
        menagerService.validateManager(m);
    }

    @Test
    public void shouldUpdateManager() throws ServiceException{
        menagerService.updateManager(10000000001L,10000000001L,"pass","ema","name","last",true);
    }
    @Test(expected = NoResultException.class)
    public void shouldNotUpdateManager()throws ServiceException{
        menagerService.updateManager(1003399401L,10000000001L,"pass","emaa","name","last",true);
    }
    @Test(expected = ServiceException.class)
    public void shouldNotUpdateManager2() throws ServiceException{
        menagerService.updateManager(10000000001L,10000000001L,"pass",null,"name","last",true);
    }

    @Test
    public void shouldDeleteMnager()throws ServiceException{
        menagerService.deleteManager(10000000001L);
    }

    @Test(expected = ServiceException.class)
    public void shoulNotdDeleteMnager1()throws ServiceException{
        menagerService.deleteManager(null);
    }

    @Test(expected = ServiceException.class)
    public void shoulNotdDeleteMnager2()throws ServiceException{
        menagerService.deleteManager(2233232344L);
    }
    @Test(expected = ServiceException.class)
    public void shouldNotSaveManager() throws ServiceException {
        Manager m = menagerService.getManagerbyId(10000000001L);
        m.setId(null);
        menagerService.saveManager(m);
    }
    @Test(expected = ServiceException.class)
    public void shouldNotSaveManager2() throws ServiceException {
        Manager m = new Manager("name",null,"ema","pass",1001L,true);
        menagerService.saveManager(m);
    }

    @Test
    public void shouldSaveManager() throws ServiceException {

        Manager m = new Manager("name","last","emaill","pass",1001L,true);
        menagerService.saveManager(m);
    }
}