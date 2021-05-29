
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

# POSTMAN

To test the endpoints use the collection located in the project, in the resources directory: /main/resources/postman_colection

1. Make sure the project is running
2. Open POSTMAN an import the file **LIBRARY.postman_collection.json**

# Generated Swagger Documentation
## When you start the application, you are all set to view the documentation that is generated.

Go to URL: http://localhost:8080/v2/api-docs

# Basic Auth Authorization
User: sa@email.com
Password: 123456
