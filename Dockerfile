# Imagen base de Gradle para construir la aplicación
FROM gradle:8.8.1-jdk17 AS build
WORKDIR /app

# Copia todos los archivos del proyecto a la imagen
COPY --chown=gradle:gradle . .

# Construye el proyecto y genera el archivo JAR
RUN gradle clean build -x test

# Imagen ligera de Java para ejecutar el JAR (Usamos JDK 17)
FROM eclipse-temurin:17-jdk-jammy

# Establece el directorio de trabajo en el contenedor
WORKDIR /app

# Copia el archivo JAR generado en la fase de construcción
COPY --from=build /app/build/libs/*.jar app.jar

# Expone el puerto en el que correrá la aplicación (ajusta según el puerto de tu app)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
