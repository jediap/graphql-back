# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/)
and this project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).

## Unreleased

### Added

### Changed

- Updated the pom file to generate the docker tagged with the version number and latest.
- Updated the pom file to move version numbers to parameters.
- Renamed packages from `com.globalomnium.goaigua` to `com.idrica.goaigua`.
- Updated Spring Boot version to `2.2.6.RELEASE`.

### Deprecated

### Removed

### Fixed

### Security


## 1.9.0 - 2020-02-25

### Added

- Added Zipkin and Sleuth for Distributed Tracing

### Changed

- Updated logging configuration.
- Code formatted.
- Change log from manual logger to Lombok logger.
- Updated README.md file.

### Removed

- Clean unused files.


## 1.8.0 - 2020-02-11

### Added

- Added parameters to limit database connections.
- Updated the Spring Cloud platform.
- Removed CORS config.
- Enabled discovery.
- Use the Spring Clod config on default profile.
- Use local settings on profile: local.
- Clean unused files.


## 1.7.0 - 2019-07-26

### Added

- Added profiles to Maven.
- It activates the access to the apis of a microservice using Swagger-ui.
- Added GoAigua security packages.

### Changed

- Cleaning and reorganization of the pom file.
- Cleaning and reorganization of the properties files.
- Move the GenericException class to a more appropriate location.
- Removed warnings in code.

### Removed

- Deleted Swagger's old method of generating information.


## 1.6.0 - 2019-06-17

### Changed

- Log files are generated so that Filebeat and / or Logstash send them to Elasticsearch and can be seen in Kibana.
- Change logback.xml
- Prepare file to qa


## 1.5.0 - 2019-06-17

### Added

- ControllerHandlerAdvice

### Changed

- Format test directory and aplication.properties Standar


## 1.4.0 - 2019-06-10

### Added

- Solution problem with CORS.
- AbstractTest. 

### Changed

- Refactoring code.


## 1.3.0 - 2019-06-05

### Changed

* Realizadas las modificaciones necesarias para que el proyecto funcione dentro de un Apache Tomcat con varias intancias diferentes conviviendo a la vez.


## 1.2.0 - 2019-05-31

### Added

* Añadido que se ignoren los ficheros .attach_pid*
* Añadido que se ignoren los ficheros .factorypath
* Añadido parámetro para que se active el Bean Overriding, a partir de esta versión de Spring Boot se ha de activar.
* Activada la generación del enpoint de Prometheus usando Sping Boot 2.
* Añadidos parámetros para la gestión del pool de conexiones a base de datos.

### Changed

* Actualizado el fichero de banner.
* Actualizado el fichero pom del proyecto para solucionar un bug del plugin de Swagger.
* Actualizado el fichero pom del proyecto para actualizar unas referencias internas.
* Actualizado el fichero pom para actualizar la versión del plugin de docker.
* Actualizado el fichero de jenkins para que en el Stage 'Cleaning' se limpie la imagen de docker.


## 1.1.0 - 2019-04-10

### Added

- Versionado de apis.

### Changed

- Actualizadas las versiones de las librerías del fichero `pom.xml`.
  - Spring Boot.
  - Spring.
  - Plugins de Maven.
  - Cassandra.
  - PostgreSQL.
  - Jackson XML.
  - Gson.
  - JaCoCo.
  - Prometheus.
  - Apache Commons Codec.
  - Swagger
  - Mockito
- Actualizado el fichero application.properties para cambiar algunas variables que cambian al hacer upgrade de Spring Boot.
- Actualizado el constructor de Application para que funcione bien con Spring Boot 2.


## 1.0.0 - 2019-04-09

### Added

- Version inicial de la plantilla, contiene las versiones sobre las que se han credo todos los proyectos hasta ahora.

