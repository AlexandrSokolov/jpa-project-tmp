- test with automatic configuration (test if only liquibase generates table)
  https://stackoverflow.com/questions/23435937/how-to-test-spring-data-repositories
  
- go through an article with explicite configuration:
  https://www.baeldung.com/spring-jpa-test-in-memory-database
  https://github.com/eugenp/tutorials/tree/master/persistence-modules/spring-jpa
  https://stackoverflow.com/questions/51931726/how-to-use-jpa-repositories-without-spring-boot
- validate entity with all fields
  https://vladmihalcea.com/how-to-fix-wrong-column-type-encountered-schema-validation-errors-with-jpa-and-hibernate/
  https://www.tutorialspoint.com/hibernate/hibernate_mapping_types.htm
  https://www.baeldung.com/hibernate-custom-types
  https://www.baeldung.com/hibernate-types-library
  https://github.com/vladmihalcea/hibernate-types
  https://stackoverflow.com/questions/29118210/understanding-hibernate-type-annotation
  https://www.baeldung.com/hibernate-lob
  https://stackoverflow.com/questions/1944660/hibernate-database-specific-columndefinition-values
- test all queries
- load initial data with dbunit
- add tests into code generation
- testing only queries, without liquibase generation
- create jpa-spring-cdi-liquibase
- create jpa-spring-liquibase
- create project for entity mapping, with liquibase
- create project for entity mapping, with all id generators
    
    1. table generation
       hibernate.id.new_generator_mappings
       https://stackoverflow.com/questions/9861416/hibernate-generates-negative-id-values-when-using-a-sequence/16349845
    2. id field:
  ```@GeneratedValue(strategy = GenerationType.TABLE, generator = "id_generator")
       @TableGenerator(
       name="id_generator",
       table="hibernate_sequence",
       pkColumnName = "sequence_name", // default //sequence_name
       pkColumnValue = "example",
       valueColumnName = "next_val") //default //sequence_next_hi_value
  ```
    3. table creation:
  ```
          - createTable:
            tableName: hibernate_sequence
            columns:
              - column:
                  name: sequence_name
                  type: varchar(20)
              - column:
                  name: next_val
                  type: bigint
        - insert:
            tableName: hibernate_sequence
            columns:
              - column:
                  name: sequence_name
                  value: "example"
              - column:
                  name: next_val
                  value: 1
  ```
  See also:
  https://www.baeldung.com/hibernate-identifiers
  https://www.concretepage.com/hibernate/generatedvalue-strategy-generationtype-table-hibernate
  https://vladmihalcea.com/why-you-should-never-use-the-table-identifier-generator-with-jpa-and-hibernate/
  https://thorben-janssen.com/jpa-generate-primary-keys/
  https://docs.jboss.org/hibernate/core/4.0/manual/en-US/html_single/#mapping-declaration-id-generator
  https://stackoverflow.com/questions/14561385/missing-sequence-or-table-hibernate-sequence
- db schema issue
  https://stackoverflow.com/questions/36419328/liquibase-create-schema-for-postgres
- hibernate configurations
  https://docs.jboss.org/hibernate/orm/3.3/reference/en/html/session-configuration.html
-
