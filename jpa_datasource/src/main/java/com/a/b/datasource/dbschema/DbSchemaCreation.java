package com.a.b.datasource.dbschema;

import liquibase.integration.cdi.CDILiquibaseConfig;
import liquibase.integration.cdi.annotations.LiquibaseType;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;

import javax.annotation.Resource;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;

public class DbSchemaCreation {

  static final String MASTER_CHANGE_LOG = "db/changelog/db.changelog-master.yml";

  //see jta-data-source in persistence.xml
  @Resource(lookup = "java:/media")
  DataSource myDataSource;

  @Produces
  @LiquibaseType
  public CDILiquibaseConfig createConfig() {
    CDILiquibaseConfig config = new CDILiquibaseConfig();
    //config.setChangeLog(MASTER_CHANGE_LOG);
    config.setChangeLog("/home/alex/projects/tmp/jpa-project/jpa_datasource/src/main/resources/db/changelog/db.changelog-master.yml");
    return config;
  }

  @Produces
  @LiquibaseType
  public DataSource createDataSource() {
    return myDataSource;
  }

  @Produces
  @LiquibaseType
  public ResourceAccessor create() {
    return new ClassLoaderResourceAccessor(getClass().getClassLoader());
  }
}
