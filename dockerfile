FROM openjdk

EXPOSE 8080

WORKDIR /app

COPY . /app

RUN ./gradlew

RUN ./gradlew bootJar

ENTRYPOINT ["./gradlew", "bootRun"]
