FROM maven:3.8.6-openjdk-11 AS build

WORKDIR /home/api-credibanco
COPY ../pom.xml .
COPY ../src ./src
RUN mvn clean package -DskipTests


FROM eclipse-temurin:11.0.23_9-jre-alpine

RUN mkdir /home/api-credibanco
WORKDIR /home/api-credibanco
COPY --from=build /home/api-credibanco/target/*.jar /home/api-credibanco/api-credibanco-sqlserver.jar
EXPOSE 9091

ENTRYPOINT ["java","-jar","/home/api-credibanco/api-credibanco-sqlserver.jar"]