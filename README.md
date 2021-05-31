# Library Management REST API

This RESTAPI was developed with the objective of administering books in a library for the requested exam. An API for managing one or more libraries was not included in the scope, so the entity "Library" was not included in this first version, as well as its relationship with the entity "Book". Includes only the entities "Book" and "BookFamily" .

The relationship of these entities "Book" and "BookFamily" occurs only in the "many to one" form, so a book will belong to only one family of books, for example, "Technology".

About API security. We opted for authentication using JWT, being, therefore, more secure than Basic Authentication (simpler to implement).
In order to try to simplify the implementation of JWT, this application includes only authentication, over authorization, it is allowing access to all endpoints, including swagger and h2. Thus, once logged in and with the token, all endpoints can be accessed. Later, we can also include functions to control authorization of access to API services, which is not in the scope of this test.

Even outside the informed scope and thinking about availability and monitoring of the API, I decided to use the Spring Boot Actuator to collect metrics, understand traffic, or the state of our database, this way they become trivial with this dependency, and have a low effort for implementation. With the Spring Boot Actuator it is possible to create an environment for monitoring and alerts, integrating with tools such as Prometheus and Grafana.

Please read the following steps to setup the project in your local machine.

# **Getting Started**

The following project is deployed with the following features:

- Java 11
- Spring Boot 2.4
- Spring Security with Bearer token
- Spring Data to persistence the data with H2 as database.
- Spring Fox with Swagger to generate the API documentation
- Spring Actuator to monitoring the API.
- Docker as a container

# **Reference Documentation**

For reference, please follow the next steps:

1. Clone the project from GIT: [https://github.com/DavidLyra/library-management-rest-api.git](https://github.com/DavidLyra/library-management-rest-api.git)
2. In ItelliJ IDEA, import the project as Maven project.
3. Open the Terminal and go inside the **project backend-library-rest-api/**. 
4. Execute the following command to package and start the project:


## Two scripts were created to build the Docker image and create the container.

1. Access the project directory.

3. Run shell-script 'build.sh':

       sh build.sh

3. Run the shell-script 'run.sh':

       sh run.sh

# H2 Database

This REST API is currently using an H2 database called **testdb** so you can run it quickly and out-of-the-box without much configuration.

The script is located in the resources directory: /main/resources/data.sql

## H2 Console

1. Make sure the project is running
2. Go to URL: http://localhost:8080/h2-console
3. Login:

- Driver Class: org.h2.driver
- JDBC URL: jdbc:h2:mem:testdb
- User Name: sa
- Password: library

# Generated Swagger Documentation

The application was configured as a Spring Boot to integrate swagger2. In Spring Boot, a REST API was exposed. The documentation of REST services is very important and should help consumers of the service to know which services are available, such as authentication. In addition, it is a simple way to test whether the service is active.

1. Make sure the project is running
2. Go to URL: http://localhost:8090/swagger-ui.html

# Postman API Client

To send requests and view responses, test the endpoints using the collection located in the project: / main / resources / postman_colection

1. Make sure the project is running
2. Open Postman API Client an import the file **LIBRARY.postman_collection.json**

# Spring Security with Bearer token

1. Make sure the project is running
2. Make a POST request to `http://localhost:8080/auth` with the default admin user we programatically created to get a valid JWT token:

       Request Body:

       {
           "email": "sa@email.com",
           "password": "123456"
       }

       Response body:

       {
           "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYSIsInJvbGVzIjpbXSwiZXhwIjoxNzA4Njc4NzM2LCJpYXQiOjE2MjIyNzg3MzZ9.TFVjHPzH10Frn3k3QZmJs56EbRAega55oPI-sH7pMHA"
       }

3. Add the JWT token as a Bearer token parameter and make the initial request to rest of endpoints.

# Connect to FTP Server to send the books file.

In the "application.yml" configuration file, you must configure access to the FTP server. In the example below, we chose a simple external public server to test and upload the file.

The user and password can be verified on the website: https://dataleaktest.com/uploader/ftp.aspx

Example application.yml config:

       # FTP config
       ftp:
         username: dlpuser
         password: 123456
         server: sftp.dlptest.com
         port: 21
         keepAliveTimout: 10


