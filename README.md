# IPlytics coding challenge
This coding challenge is for candidates for the position "Backend Developer - Web Applications"

## Overview
Our software lets users search for Patents and Standards, amongst other things.
## Tools Used

- open-jdk 8
- maven
- Spring
- H2 in memory database to persist the data

## Generates
A runnable jar will be generated that will be can be used hit on the enpdoint http://localhost:8080/

## How To generate the jar
to test the app run:
```bash
mvn test
```

to generate the jar run:
```bash 
mvn compile
```

to run the app run:
```bash
mvn spring-boot:run
```

change default port in application.properties file set the command to the port wanted server.port=8081

## SonarQube Linting
please note that you need to have sonarQube installed on your machine
more details : https://www.sonarqube.org/
```bash
mvn sonar:sonar -Dsonar.projectKey=de.iplytics.codingchallenge_backend_webapp -Dsonar.host.url=http://localhost:9000 -Dsonar.login=54db2a4cf71d61a01d01a61fa108c571946a70a6
```

## Swagger documentaion under
http://localhost:8080/swagger-ui.html

## Solr 8.8.2 DB config
- solr DB version 8.8.2
- download apache solr zip from : https://www.apache.org/dyn/closer.lua/lucene/solr/8.8.2/solr-8.8.2.zip
- run solr DB from the bin directory in the terminal run the following command : solr start
- solr endpoint : localhost:8983:solr/

### add solr cores
- solr create -c patent
- solr create -c standard
- solr create -c declaration


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.