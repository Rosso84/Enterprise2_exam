## PG6100 Enterpriseprogramming 2

## Requirements

Tools needed to run this project:

JDK 8

Git

Maven

An Ide (we used Intellij)

Docker

A bash Cli (Gitbash for windows or built in terminal for linux/mac)


 ## About the project
 
 Through our lectures we had full details of :
 
 -RESTful API's and HTTP
 
 -Knowledge of other kinds of webservices like SOAP and GraphQL
 
 -Microservice Archtiecture
 
 -Security in distributed systems
 
 -Message oriented Middleware
 
 
 

## My Achievements

Module: Flight-service

The application has one microservice the following funtionaliti :

-at least one endpoint per main HTTP method, ie, GET, POST, PUT,
PATCH and DELETE. The PATCH endpoint MUST use JSON Merge Patch.

-The API uses a SQL database.

-Provides Swagger documentation for all endpoints

-Provides a starting class that can be used to start the application and see
 the UI for the Swagger documentation that uses H2 an embedded SQL database .
 
-Each endpoint uses Wrapped Responses.

-Endpoints returning collections of data (getAll) uses Pagination
 
-at least one test with RestAssured per each endpoint.
 
 
 
 ## Running the aplication
 First Run maven clean install.
 
 Then there are 2 ways to run the application and open swagger:
 
 1.You can manually run the application by navigating to Flight-service/Test/flightservice/
   and rightclick on LocalApplicationRunner and run.  This will use an embedded database H2 for testing .
   
   The applicaion will then be available at:
   
   http://localhost:8080/swagger-ui.html
 
 2.Or run a Dockerfile in a  glasfish container with docker-compose by navigating to root of the project and use a terminal      to run command 'docker-compose up --build'. this will build an image and use a real postgres database.
   The applicaion will  then be available at:    
   
   http://localhost:8080/swagger-ui.html
  
 
