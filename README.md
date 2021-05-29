
Please read the following steps to setup the project in your local machine

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

- Pull the project from GIT: [https://github.com/DavidLyra/library-management-rest-api.git](https://github.com/DavidLyra/library-management-rest-api.git)
- In Eclipse, import the project as Maven project from the Check the **Build Path** of the project is compiled with Java 11
- Open the Terminal and go inside the **project backend-library-rest-api/**. Then execute the following command to package the project:

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
2. Go to URL: http://localhost:8080/v2/api-docs

# Postman API Client

To send requests and view responses, test the endpoints using the collection located in the project: / main / resources / postman_colection

1. Make sure the project is running
2. Open POSTMAN an import the file **LIBRARY.postman_collection.json**

# Basic Auth Authorization
User: sa@email.com
Password: 123456
