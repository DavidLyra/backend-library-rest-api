FROM adoptopenjdk/openjdk11:alpine-jre
RUN addgroup -S app && adduser -S app -G app
USER app:app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]