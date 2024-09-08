FROM amazoncorretto:21-alpine-jdk

# Copia el JAR generado en la imagen
COPY ./build/libs/cienpaginas-0.0.1-SNAPSHOT.jar app.jar

# Configura el comando de entrada para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app.jar"]
