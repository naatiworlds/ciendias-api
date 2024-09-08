# Usa la imagen base con JDK 22
FROM openjdk:22-jdk-alpine AS build

# Copia los archivos de tu proyecto y ejecuta la compilación de Gradle
WORKDIR /app
COPY . .

# Compila la aplicación sin ejecutar los tests
RUN ./gradlew clean build -x test

# Fase final: ejecutar la aplicación con OpenJDK 22
FROM openjdk:22-jdk-alpine

WORKDIR /app

# Copia el archivo JAR generado
COPY --from=build /app/build/libs/cienpaginas-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto de la aplicación
EXPOSE 8080

# Ejecuta la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
