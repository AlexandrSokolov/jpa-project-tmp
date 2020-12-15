package com.a.b.datasource.repository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.a.b.datasource.repository")
@EntityScan( { "com.a.b.datasource.entities" })
public class SpringJpaAutoConfig {

  public static void main(String... args) {
    SpringApplication.run(SpringJpaAutoConfig.class, args);
  }
}
