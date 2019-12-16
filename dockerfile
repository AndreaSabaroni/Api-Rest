FROM openjdk:14-jdk-alpine3.10

EXPOSE 8080

WORKDIR /app

COPY ./build/libs/api-mutante.jar /app

CMD java -jar api-mutante.jar
