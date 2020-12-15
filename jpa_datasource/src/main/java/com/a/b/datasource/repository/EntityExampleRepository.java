package com.a.b.datasource.repository;

import com.a.b.datasource.entities.EntityExample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.a.b.datasource.entities.EntityExample.Persistence.COLUMN_NAME;
import static com.a.b.datasource.entities.EntityExample.Persistence.ENTITY_NAME;
import static com.a.b.datasource.entities.EntityExample.Persistence.TABLE_NAME;
import static com.a.b.datasource.entities.EntityExample.Queries.NAME_PARAM;

public interface EntityExampleRepository extends JpaRepository<EntityExample, Long> {

  //if query is defined via spring Query annotation, we can name method as we want
  //Indexed param is used
  @Query("SELECT e FROM " + ENTITY_NAME + " e WHERE " + COLUMN_NAME + " = ?1")
  List<EntityExample> findByNameIndexed(String name);

  //if query is defined via spring Query annotation, we can name method as we want
  //Named param is used, defined with @Param spring annotation
  @Query("SELECT e FROM " + ENTITY_NAME + " e WHERE " + COLUMN_NAME + " = :" + NAME_PARAM)
  List<EntityExample> findByNameNamed(@Param(NAME_PARAM) String name);

  //Query is defined on the entity class via jpa anotation, but not spring one
  //Spring will find query using entity name + "." + method name
  //EntityExample.findByNameIndexedParam query will be invoked:
  List<EntityExample> findByNameIndexedParam(String name);

  //Query is defined on the entity class via jpa anotation, but not spring one
  //spring will find query using entity name + "." + method name
  //Named param is used, defined with @Param spring annotation
  //EntityExample.findByNameNamedParam query will be invoked:
  List<EntityExample> findByNameNamedParam(@Param(NAME_PARAM) String name);

  ///////////////   Native queries

  //if query is defined via spring Query annotation, we can name method as we want
  //Indexed param is used
  @Query(value = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = ?1", nativeQuery = true)
  List<EntityExample> findByNameNativeIndexed(String name);

  //if query is defined via spring Query annotation, we can name method as we want
  //Named param is used, defined with @Param spring annotation
  @Query(value = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = :" + NAME_PARAM, nativeQuery = true)
  List<EntityExample> findByNameNativeNamed(@Param(NAME_PARAM) String name);

  //Query is defined on the entity class via jpa anotation, but not spring one
  //spring will find query using entity name + "." + method name
  //EntityExample.findByNameNativeIndexedParam query will be invoked:
  List<EntityExample> findByNameNativeIndexedParam(String name);

  //Query is defined on the entity class via jpa anotation, but not spring one
  //spring will find query using entity name + "." + method name
  //EntityExample.findByNameNativeIndexedParam query will be invoked:
  List<EntityExample> findByNameNativeNamedParam(@Param(NAME_PARAM) String name);

}
