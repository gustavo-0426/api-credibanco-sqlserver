FROM eclipse-temurin:11.0.23_9-jre-alpine

RUN mkdir /home/api-credibanco

WORKDIR /home/api-credibanco

COPY ./target/api-credibanco-sqlserver-2.0.0.jar /home/api-credibanco/api-credibanco-sqlserver-2.0.0.jar

EXPOSE 9091

ENTRYPOINT ["java","-jar","/home/api-credibanco/api-credibanco-sqlserver-2.0.0.jar"]
