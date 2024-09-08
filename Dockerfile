FROM openjdk-22

COPY build/libs/cienpaginas-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]