
Please read the following steps to setup the project in your local machine.

# **Getting Started**

The following project is deployed with the following features:

- Java 11
- Spring Boot 2.4
- Spring Security with Bearer token
- Spring Data to persistence the data with H2 as database.
- Spring Fox with Swagger to generate the API documentation
- Docker as a container

# **Reference Documentation**

For reference, please follow the next steps:

1. Clone the project from GIT: [https://github.com/DavidLyra/library-management-rest-api.git](https://github.com/DavidLyra/library-management-rest-api.git)
2. In ItelliJ IDEA, import the project as Maven projecT.
3. Open the Terminal and go inside the **project backend-library-rest-api/**. 
4. Execute the following command to package and start the project:

`mvn package`

- Then, to run the project, execute the following command

`mvn docker:start`

This command will show you the starter log with the Starter CONTAINER ID
- To view the log trace, copy the CONTAINER ID and execute the following command:

`docker logs --follow {CONTAINER_ID}`

If everything is fine, open the Swagger API documentation of this project located in [http://localhost:8090/swagger-ui.html](http://localhost:8090/swagger-ui.html)

- To stop the project, use the next command:
`mvn docker:stop`

# H2 Database

This demo is currently using an H2 database called **test_db** so you can run it quickly and out-of-the-box without much configuration.

The script is located in the resources directory: /main/resources/data.sql

## H2 Console

1. Make sure the project is running
2. Go to URL: http://localhost:8080/h2-console
3. Login:

- Driver Class: org.h2.driver
- JDBC URL: jdbc:h2:mem:testdb
- User Name: sa
- Password: password

# Generated Swagger Documentation

The application was configured as a Spring Boot to integrate swagger2. In Spring Boot, a REST API was exposed. The documentation of REST services is very important and should help consumers of the service to know which services are available, such as authentication. In addition, it is a simple way to test whether the service is active.

1. Make sure the project is running
2. Go to URL: http://localhost:8090/swagger-ui.html

# Postman API Client

To send requests and view responses, test the endpoints using the collection located in the project: / main / resources / postman_colection

1. Make sure the project is running
2. Open POSTMAN an import the file **LIBRARY.postman_collection.json**

# Spring Security with Bearer token

1. Make sure the project is running
2. Make a POST request to `http://localhost:8080/auth` with the default admin user we programatically created to get a valid JWT token:

**Request Body**

{
    "email": "sa@email.com",
    "password": "123456"
}

**Response body**

{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYSIsInJvbGVzIjpbXSwiZXhwIjoxNzA4Njc4NzM2LCJpYXQiOjE2MjIyNzg3MzZ9.TFVjHPzH10Frn3k3QZmJs56EbRAega55oPI-sH7pMHA"
}

3. Add the JWT token as a Bearer token parameter and make the initial GET request to rest of endpoints.
