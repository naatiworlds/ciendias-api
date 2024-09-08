FROM eclipse-temurin:22-jdk-jammy

COPY build/libs/cienpaginas-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]