FROM openjdk:8-jre-slim

RUN mkdir /app

WORKDIR /app

ADD ./api/target/catalog-api-1.0-SNAPSHOT.jar /app

EXPOSE 8081

CMD java -jar catalog-api-1.0-SNAPSHOT.jar