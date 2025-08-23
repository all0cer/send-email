FROM  maven:3.8.6-openjdk-17 as build

COPY src /app/src
WORKDIR /app

RUN mvn clean install

FROM openjdk:17-jdk-alpine

COPY --from=build /app/target/email-service-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080

WORKDIR /app

ENTRYPOINT ["java","-jar","app.jar"]