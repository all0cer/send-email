FROM  maven:3.8.6-eclipse-temurin-17 AS build

COPY src /app/src
COPY pom.xml /app
WORKDIR /app

RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-alpine

COPY --from=build /app/target/email-service-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080

WORKDIR /app

ENTRYPOINT ["java","-jar","app.jar"]