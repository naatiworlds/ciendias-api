FROM amazoncorretto:21-alpine-jdk

COPY build/libs/cienpaginas-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jav", "/app.jar"]