package com.a.b.datasource.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import static com.a.b.datasource.entities.EntityExample.Persistence.COLUMN_NAME;
import static com.a.b.datasource.entities.EntityExample.Persistence.TABLE_NAME;

@Entity
@Table(name = "not_existing_table")
public class NotValidEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long id;

  @Column(name="not_existing_name", unique = false, nullable = true, length = 1024)
  private String notExitingName;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getNotExitingName() {
    return notExitingName;
  }

  public void setNotExitingName(String notExitingName) {
    this.notExitingName = notExitingName;
  }
}
