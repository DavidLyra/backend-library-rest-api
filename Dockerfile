FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/library-management-rest-api.jar"]