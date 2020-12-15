package com.a.b.datasource.repository;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CdiConfig {

  @Produces
  @Dependent //do not set RequestScoped
  @PersistenceContext(unitName = "jpa-project-persistent-unit") //see persistence-unit name="jpa-project-persistent-unit" in persistence.xml
  public EntityManager entityManager;
}
