package com.a.b.datasource.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.a.b.datasource.entities.EntityExample;
import com.a.b.datasource.entities.NotValidEntity;
import com.a.b.datasource.entities.Status;

//without DataJpaTest
@RunWith(SpringRunner.class) //alternative is SpringJUnit4ClassRunner.class
@ContextConfiguration(
  classes = { SpringJpaTestConfig.class }
  /*, loader = AnnotationConfigContextLoader.class */ )
@Transactional
public class EntityExampleRepositoryTest {

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
    example.setLocalDateTime(LocalDateTime.now());
//    example.setStatus(Status.CREATED);

    //entityExampleRepository.saveAndFlush(example);
    entityManager.persist(example);
    entityManager.flush();

    List<EntityExample> found = entityExampleRepository.findByNameIndexed("test");
    Assert.assertNotNull(found);
    Assert.assertFalse(found.isEmpty());
  }

  @Test
  public void testNotValidEntity() {
    NotValidEntity example = new NotValidEntity();
    //example.setId(10); do not set id, when you persist entity, or use save() instead of persist()
    example.setNotExitingName("test");

    entityManager.persist(example);
    entityManager.flush();
  }
}
