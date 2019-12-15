FROM openjdk

EXPOSE 8080

WORKDIR /app

COPY ./build/libs/api-mutante.jar /app

CMD java -jar api-mutante.jar
