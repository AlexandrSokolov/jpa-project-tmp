package com.a.b.datasource.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.a.b.datasource.entities.EntityExample;
import com.a.b.datasource.entities.NotValidEntity;

//@ExtendWith(SpringExtension.class)
//@RunWith(SpringRunner.class)
//java.lang.IllegalStateException: Unable to find a @SpringBootConfiguration, you need to use @ContextConfiguration or @SpringBootTest(classes=...) with your test
//@SpringBootTest(classes=EntityExample.class, EntityExampleRepository.class)
//@AutoConfigureTestDatabase
//@SpringBootTest(classes=Application.class)
//@DataJpaTest

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringJpaAutoConfig.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Sql(
//  executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
//  statements = "CREATE SCHEMA IF NOT EXISTS MYTESTDB; SET SCHEMA MYTESTDB; USE MYTESTDB;"
//  /*, scripts = {"classpath:init.sql"} */)
@Transactional
public class EntityExampleRepositoryAutoconfigureTest {

  @Autowired private DataSource dataSource;
  @Autowired private EntityManager entityManager;
  @Autowired private EntityExampleRepository entityExampleRepository;

  @Test
  public void injectedComponentsAreNotNull(){
    Assert.assertNotNull(dataSource);
    Assert.assertNotNull(entityManager);
    Assert.assertNotNull(entityExampleRepository);
  }

  @Test
  public void testFindByNameIndexed() {
    EntityExample example = new EntityExample();
    //example.setId(10);
    example.setName("test");

    entityManager.persist(example);
    entityManager.flush();
    //entityExampleRepository.saveAndFlush(example);

    List<EntityExample> found = entityExampleRepository.findByNameIndexed("test");
    Assert.assertNotNull(found);
    Assert.assertFalse(found.isEmpty());
  }

  @Test
  public void test2() {
    NotValidEntity example = new NotValidEntity();
    //example.setId(10); do not set it, when you persiste entity
    example.setNotExitingName("test");

    entityManager.persist(example);
    entityManager.flush();

//    entityManager.persist(example);
//    entityManager.flush();

  }
}
