package com.a.b.datasource.repository;

import liquibase.integration.spring.SpringLiquibase;

import java.sql.SQLException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.a.b.datasource.repository")
@PropertySource("test.persistence.properties")
@EnableTransactionManagement
public class SpringJpaTestConfig {

  @Autowired
  private Environment env;

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
    dataSource.setUrl(env.getProperty("jdbc.url"));
    //dataSource.setSchema("PUBLIC");
//    dataSource.setUsername(env.getProperty("jdbc.user"));
//    dataSource.setPassword(env.getProperty("jdbc.pass"));
    return dataSource;
  }

  @Bean
  @DependsOn("springLiquibase") //to create schema with liquibase before schema validation by hibernate
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(dataSource());
    em.setPackagesToScan(new String[] { "com.a.b.datasource.entities", "com.a.b.datasource.repository" });
    em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    em.setJpaProperties(hibernateProperties());
    em.afterPropertiesSet();
    return em;
  }

  @Bean
  JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory);
    return transactionManager;
  }

  @Bean
  public SpringLiquibase springLiquibase(DataSource dataSource) throws SQLException {
    tryToCreateSchema(dataSource);
    SpringLiquibase liquibase = new SpringLiquibase();
    liquibase.setDropFirst(true);
    liquibase.setDataSource(dataSource);
    //liquibase.setDefaultSchema(env.getProperty("jdbc.db.name"));
    //liquibase.setIgnoreClasspathPrefix(false);
    liquibase.setChangeLog("classpath:/db/changelog/db.changelog-master.yml");
    return liquibase;
  }

  private Properties hibernateProperties() {

    final Properties hibernateProperties = new Properties();

    hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
    hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
    hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
    hibernateProperties.setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
    hibernateProperties.setProperty("hibernate.use_sql_comments", env.getProperty("hibernate.use_sql_comments"));
    hibernateProperties.setProperty("hibernate.id.new_generator_mappings", env.getProperty("hibernate.id.new_generator_mappings"));

    return hibernateProperties;
  }

  private void tryToCreateSchema(DataSource dataSource) throws SQLException {
    String CREATE_SCHEMA_QUERY = String.format(
      "CREATE SCHEMA IF NOT EXISTS %s",
      env.getProperty("jdbc.db.name"));
    dataSource
      .getConnection()
      .createStatement()
      .execute(CREATE_SCHEMA_QUERY);
  }
}
