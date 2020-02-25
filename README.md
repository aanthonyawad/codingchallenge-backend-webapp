# IPlytics coding challenge
This coding challenge is for candidates for the position "Backend Developer - Web Applications"

## Overview
Our software lets users search for Patents and Standards, amongst other things. Our web application is the gateway to our data sets, and we would like to see how well you can build an application that models a little part of our data universe.

We want to know how well you can work with the following:
* Spring Boot framework
* Modern Java API's
* RESTful API design
* Unit and integration testing
* Written and generated documentation

## What we provide you
Fork this repo and clone it to your local environment. You will have a fully working Spring Boot webapp, which you can run and access at http://localhost:8080 to use a very simple API. Make a `GET` request to http://localhost:8080/patents/US1234A1 to load a dummy patent we created for you to get started. The repo also contains some SQL scripts managed with Flyway, to manage our data model and contents. Data is stored in an in-memory H2 database so you don't need to configure any other database.

## What we want from you

You need to solve a few small challenges in order to pass. Here they are:

### Fix a bug
First things first, there is a bug in the `/patents` GET endpoint. When we make a request for a patent that we don't have in our database, the endpoint should return a `404 NOT FOUND` but that's not working. We already have a test to cover this in the class `PatentControllerIntegrationTest`. Make the test pass, and do it in a way which makes the best use of the Spring Framework's features.

### CRUD for patents 
Our users need to add their own patents to the system. We have the `R` of CRUD but not the rest! Add new endpoints to the `PatentController` so we can create new patent entries, or update/delete existing ones. Make sure to use the best practices of RESTful API design, to return sensible responses, and the correct error codes as required. You should add integration and unit tests as needed.

### Declarations
One interesting aspect of Standards is how they are composed. Companies typically "declare" their patent to be a part of a standard. So there is a relational connection between patents and standards, which we can model. Create a SQL model for a declaration, and update the existing model to include the appropriate foreign key relationships. (It's important not to change the existing SQL model, but to build on it.) Create a new endpoint which lets a user declare their patents to be part of a standard. As in the previous task, ensure that appropriate responses and codes are returned.