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
 
 
 

## Given tasks

Within 3 days we had to:
1
. Provide a readme.md file in the main root folder of your project (i.e., same place as
the root pom.xml file). In such file, you must briefly discuss your solution, providing
any info you deem important. If you do not attempt to do some of the requirements,
you must state so in the readme.md file, e.g., “I did not do requirement X, Y and Z”.
Failure to state so will further reduce your grade.

2. Write one REST API using Spring Boot and Kotlin, targeting JDK 8 (NO later version,
e.g., not 9, nor 10 nor 11)
a. Have at least one endpoint per main HTTP method, ie, GET, POST, PUT,
PATCH and DELETE. The PATCH endpoint MUST use JSON Merge Patch.
b. The API must use a SQL database.
c. Provide Swagger/OpenAPI documentation for all your endpoints. You should
also provide a starting class that can be used to start the application and see
the UI for the Swagger documentation. Note: in this case, you can use an
embedded SQL database (e.g., H2). In the readme.md file, specify the starting
class and the URL to open the UI. In other words, an examiner MUST be able
to start the API directly from an IDE, and point his/her browser to such
Swagger documentation. Note: you might want to use a special profile to
disable connections to other services, like Redis caches, which could prevent
the REST API from starting when started in isolation.
d. Each endpoint must use Wrapped Responses.
e. Endpoints returning collections of data must use Pagination, unless you can
convincingly argue (in code comments) that they do not deal with large
quantity of data, and the size is always small and bounded. Example: an
endpoint that returns the top 10 players in a leader-board for a game does not
need to use Pagination.
f. Write at least one test with RestAssured per each endpoint.
g. Add enough tests (unit or integration, it is up to you) such that, when they are
run from IntelliJ, they should achieve at least a 70% code coverage.
h. If the service communicates with another REST API, you need to use
WireMock in the integration tests to mock it.
i. Configure Maven to build a self-executable uber/fat jar for the service.
j. Write a Docker file for the service.

3.Your microservices should be accessible only from a single entry point, i.e., an API
Gateway.
a. You need at least one REST API service that is started more than once (i.e.,
more than one instance in the Docker-Compose file), and load balanced with
Eureka.
b. In Docker-Compose, use real databases (e.g. PostgreSQL) instead of
embedded ones (e.g., H2) directly in the services.
c. Write at least one end-to-end test in which you start (using Docker-Compose)
the Gateway, Eureka, 2 instances of the REST API, and a PostgreSQL
database. All end-to-end tests need to be in their own folder/module (as in the
examples shown in class).

4.You need to have security mechanisms (using Spring Security) in place to protect
your REST APIs.
a. You need to create the concept of “user” in your microservices. You will need
to create an API with database to handle registration and login of users. Note:
most of this would just be an adaptation from the examples shown in class.
b. You must use distributed session-based authentication with Redis. Note: this
latter can be disabled/changed (i.e., no Redis) when the REST API is tested in
isolation.
c. Write at least one end-to-end test (using Docker-Compose) to show that the
distributed session-based authentication is working.

5.You need at least one communication relying on AMQP.
a. Write a new service (can be as simple as a REST API with a single endpoint).
b. Make at least one communication from the main REST API to such new
service, using RabbitMQ.
c. Write at least one end-to-end test (using Docker-Compose) to show that the
AMQP communications are working.
Extras: In the eventuality of you finishing all of the above requirements, and only then,
if you have extra time left you should add functionalities/features to your project.
Those extra functionalities need to be briefly discussed/listed in the readme.md file
(e.g., as bullet points). Each major new visible feature must have at least one test to
show/verify it. Note: in the marking, the examiner might ignore extra functionalities
that are not listed/discussed in the readme. What type of functionalities to add is
completely up to you.

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
  
 
