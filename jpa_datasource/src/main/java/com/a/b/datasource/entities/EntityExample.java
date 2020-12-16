package com.a.b.datasource.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

import static com.a.b.datasource.entities.EntityExample.Persistence.COLUMN_NAME;
import static com.a.b.datasource.entities.EntityExample.Persistence.ENTITY_NAME;
import static com.a.b.datasource.entities.EntityExample.Persistence.TABLE_NAME;
import static com.a.b.datasource.entities.EntityExample.Queries.NAME_PARAM;

@Entity
@Table(name = TABLE_NAME)
@NamedQueries({
  @NamedQuery(
    name = EntityExample.Queries.BY_NAME_INDEXED_PARAM,
    query = "SELECT e FROM " + ENTITY_NAME + " e WHERE " + COLUMN_NAME + " = ?1"),
  @NamedQuery(
    name = EntityExample.Queries.BY_NAME_NAMED_PARAM,
    query = "SELECT e FROM " + ENTITY_NAME + " e WHERE " + COLUMN_NAME + " = :" + NAME_PARAM)
})
@NamedNativeQueries({
  @NamedNativeQuery(
    name = EntityExample.Queries.NATIVE_BY_NAME_INDEXED_PARAM,
    query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = ?1", resultClass = EntityExample.class),
  @NamedNativeQuery(
    name = EntityExample.Queries.NATIVE_BY_NAME_NAMED_PARAM,
    query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = :" + NAME_PARAM,
    resultClass = EntityExample.class)
})
public class EntityExample {

  public interface Persistence {
    String ENTITY_NAME = "EntityExample"; //same as entity
    String TABLE_NAME = "example";
    String COLUMN_NAME = "name";
  }

  /**
   * To use with Spring Data Jpa, query name must start with entity name, then dot, then method name,
   * for instance the following methods will be invoked for queries:
   *
   */
  public interface Queries {
    String BY_NAME_INDEXED_PARAM = ENTITY_NAME + ".findByNameIndexedParam";
    String NATIVE_BY_NAME_INDEXED_PARAM = ENTITY_NAME + ".findByNameNativeIndexedParam";
    String NAME_PARAM = "name";
    String BY_NAME_NAMED_PARAM = ENTITY_NAME + ".findByNameNamedParam";
    String NATIVE_BY_NAME_NAMED_PARAM = ENTITY_NAME + ".findByNameNativeNamedParam";
  }

  public EntityExample(){}

  @Id
  //@GeneratedValue(strategy = GenerationType.SEQUENCE)
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "id_generator")
  @TableGenerator(
    name="id_generator",
    table="hibernate_sequence",
    pkColumnName = "sequence_name",
    pkColumnValue = "example",
    valueColumnName = "next_val") //default //sequence_next_hi_value
  private long id;

  @Column(name=COLUMN_NAME, unique = false, nullable = true, length = 1024)
  private String name;

//  @Column(name="not_existing_name", unique = false, nullable = true, length = 1024)
//  private String notExitingName;
//
//  public String getNotExitingName() {
//    return notExitingName;
//  }
//
//  public void setNotExitingName(String notExitingName) {
//    this.notExitingName = notExitingName;
//  }

//  @Type
//  @Enumerated(EnumType.STRING)
//  private Status status;

//  //liquibase type: tinyint
//  @Column(name="smallest_int")
//  private short smallestInt;

//  //liquibase type: smallint
//  @Column(name="small_int")
//  private short smallInt;

//  //liquibase type: number
//  private int number1;

//  //liquibase type: int
//  private int number2;
//
//  //liquibase type: bigint
//  @Column(name="big_number")
//  private long bigNumber;
//
//  //liquibase type: currency
//  private BigDecimal money;

//  @Column(name="float_field")
//  private float floatField;

//  @Column(name="double_field")
//  private double doubleField;
//
//  @Column(name="decimal_field")
//  private BigDecimal decimalField;

  ///////   new java 8 date time api ///////////////////
  //liquibase: datetime
//  @Column(name="instant_field")
//  private Instant instantField;
//
//  //liquibase: datetime
//  @Column(name="offset_date_time")
//  private OffsetDateTime offsetDateTime;
//
//  //liquibase: datetime
//  @Column(name="zoned_date_time")
//  private ZonedDateTime zonedDateTime;

  //liquibase: datetime
  @Column(name="local_date_time")
  private LocalDateTime localDateTime;

//  //liquibase: date
//  @Column(name="local_date")
//  private LocalDate localDate;
//
//  //liquibase: time
//  @Column(name="local_time")
//  private LocalTime localTime;
//
//  ///////   old java 8 date time api ///////////////////
//  //liquibase: datetime
//  @Column(name="java_date")
//  private Date javaDate;

//  //liquibase: date
//  @Column(name="java_only_date")
//  private Date javaOnlyDate;

//  //liquibase: datetime
//  @Column(name="sql_date")
//  private java.sql.Date sqlDate;
//
//  //liquibase: timestamp
//  @Column(name="sql_timestamp")
//  private Timestamp sqlTimestamp;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

//  public Status getStatus() {
//    return status;
//  }
//
//  public void setStatus(Status status) {
//    this.status = status;
//  }

//  public short getSmallestInt() {
//    return smallestInt;
//  }
//
//  public void setSmallestInt(short smallestInt) {
//    this.smallestInt = smallestInt;
//  }
//
//  public short getSmallInt() {
//    return smallInt;
//  }
//
//  public void setSmallInt(short smallInt) {
//    this.smallInt = smallInt;
//  }

//  public int getNumber1() {
//    return number1;
//  }
//
//  public void setNumber1(int number1) {
//    this.number1 = number1;
//  }

//  public int getNumber2() {
//    return number2;
//  }
//
//  public void setNumber2(int number2) {
//    this.number2 = number2;
//  }
//
//  public long getBigNumber() {
//    return bigNumber;
//  }
//
//  public void setBigNumber(long bigNumber) {
//    this.bigNumber = bigNumber;
//  }
//
//  public BigDecimal getMoney() {
//    return money;
//  }
//
//  public void setMoney(BigDecimal money) {
//    this.money = money;
//  }

//  public float getFloatField() {
//    return floatField;
//  }

//  public void setFloatField(float floatField) {
//    this.floatField = floatField;
//  }

//  public double getDoubleField() {
//    return doubleField;
//  }
//
//  public void setDoubleField(double doubleField) {
//    this.doubleField = doubleField;
//  }
//
//  public BigDecimal getDecimalField() {
//    return decimalField;
//  }
//
//  public void setDecimalField(BigDecimal decimalField) {
//    this.decimalField = decimalField;
//  }
//
//  public Instant getInstantField() {
//    return instantField;
//  }
//
//  public void setInstantField(Instant instantField) {
//    this.instantField = instantField;
//  }
//
//  public OffsetDateTime getOffsetDateTime() {
//    return offsetDateTime;
//  }
//
//  public void setOffsetDateTime(OffsetDateTime offsetDateTime) {
//    this.offsetDateTime = offsetDateTime;
//  }
//
//  public ZonedDateTime getZonedDateTime() {
//    return zonedDateTime;
//  }
//
//  public void setZonedDateTime(ZonedDateTime zonedDateTime) {
//    this.zonedDateTime = zonedDateTime;
//  }

  public LocalDateTime getLocalDateTime() {
    return localDateTime;
  }

  public void setLocalDateTime(LocalDateTime localDateTime) {
    this.localDateTime = localDateTime;
  }

//  public LocalDate getLocalDate() {
//    return localDate;
//  }
//
//  public void setLocalDate(LocalDate localDate) {
//    this.localDate = localDate;
//  }
//
//  public LocalTime getLocalTime() {
//    return localTime;
//  }
//
//  public void setLocalTime(LocalTime localTime) {
//    this.localTime = localTime;
//  }
//
//  public Date getJavaDate() {
//    return javaDate != null ? (Date) javaDate.clone() : null;
//  }
//
//  public void setJavaDate(Date javaDate) {
//    this.javaDate = javaDate != null ? (Date) javaDate.clone() : null;
//  }

//  public Date getJavaOnlyDate() {
//    return javaOnlyDate != null ? (Date) javaOnlyDate.clone() : null;
//  }
//
//  public void setJavaOnlyDate(Date javaOnlyDate) {
//    this.javaOnlyDate = javaOnlyDate != null ? (Date) javaOnlyDate.clone() : null;
//  }

//  public java.sql.Date getSqlDate() {
//    return sqlDate != null ? (java.sql.Date) sqlDate.clone() : null;
//  }
//
//  public void setSqlDate(java.sql.Date sqlDate) {
//    this.sqlDate = sqlDate != null ? (java.sql.Date) sqlDate.clone() : null;
//  }
//
//  public Timestamp getSqlTimestamp() {
//    return sqlTimestamp != null ? (Timestamp) sqlTimestamp.clone() : null;
//  }
//
//  public void setSqlTimestamp(Timestamp sqlTimestamp) {
//    this.sqlTimestamp = sqlTimestamp != null ? (Timestamp) sqlTimestamp.clone() : null;
//  }
}
