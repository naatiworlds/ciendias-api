# Fase 1: Construcción de la aplicación usando Gradle y OpenJDK 22
FROM gradle:8.8-jdk22 AS build

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Cachear las dependencias de Gradle
COPY build.gradle.kts settings.gradle.kts ./
COPY gradle ./gradle

# Descargar las dependencias sin construir el proyecto (para usar el caché)
RUN gradle build -x test --no-daemon --stacktrace || return 0

# Copiar el resto del código del proyecto
COPY . .

# Compilar la aplicación (sin ejecutar los tests)
RUN gradle clean build -x test --no-daemon --stacktrace

# Fase 2: Crear una imagen ligera para ejecutar el JAR
FROM eclipse-temurin:22-jdk-jammy

# Establecer el directorio de trabajo en el contenedor final
WORKDIR /app

# Copiar el archivo JAR compilado desde la fase de construcción
COPY --from=build /app/build/libs/*.jar app.jar

# Exponer el puerto 8080
EXPOSE 8080

# Comando de inicio para ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
