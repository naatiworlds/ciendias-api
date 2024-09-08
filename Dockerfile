# Usar la imagen base de Amazon Corretto 21
FROM amazoncorretto:21-alpine-jdk

# Copiar el JAR generado en la imagen
COPY cienpaginas-0.0.1-SNAPSHOT.jar app.jar

# Configurar el comando de entrada para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app.jar"]
