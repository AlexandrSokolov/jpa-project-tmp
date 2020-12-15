### Test project

test desc

#### Build the project:

To build and apply all code quality checks, run:  `mvn clean install`

To speed build up without code quality checks, run: `mvn clean package`

To skip tests, run Maven with: `-DskipTests=true` parameter. For instance:

`mvn clean package -DskipTests=true`

#### Increment version:

`mvn versions:set -DnewVersion=1.0.1 -DoldVersion=* -DgroupId=* -DartifactId=* -DgenerateBackupPoms=false`

Note: it is not enough to run it as: `mvn versions:set -DnewVersion=1.0.1 -DgenerateBackupPoms=false`

In that case dependency in `deps/pom.xml` will not be changed.

#### Usage:

1. Application GUI url

    `${deployed.application.host}/project`

    For instance: `http://localhost:18080/project`

2. Enunciate and Swagger documentation on supported REST services url:

    `${deployed.application.host}/project/docs`

    For instance: `http://localhost:18080/project/docs`