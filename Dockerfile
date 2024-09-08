# Usar una imagen base que incluye OpenJDK 22 y Gradle 8.8
FROM gradle:8.8-jdk22 AS build

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar los archivos del proyecto al contenedor
COPY . .

# Compilar la aplicación sin ejecutar tests
RUN gradle clean build -x test

# Segunda fase: usar una imagen de OpenJDK 22 para ejecutar el JAR
FROM eclipse-temurin:22-jdk-jammy

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el JAR generado desde la fase de compilación
COPY --from=build /app/build/libs/*.jar app.jar

# Exponer el puerto de la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
