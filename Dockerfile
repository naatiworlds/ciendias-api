# Imagen base con OpenJDK 22
FROM eclipse-temurin:22-jdk-jammy AS build

# Instalar Gradle 8.8 manualmente
ENV GRADLE_VERSION=8.8
RUN apt-get update && apt-get install -y wget unzip \
    && wget https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip \
    && unzip gradle-${GRADLE_VERSION}-bin.zip -d /opt \
    && rm gradle-${GRADLE_VERSION}-bin.zip \
    && ln -s /opt/gradle-${GRADLE_VERSION}/bin/gradle /usr/bin/gradle

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar los archivos del proyecto
COPY . .


# Segunda fase: usar una imagen ligera de OpenJDK 22 para ejecutar el JAR
FROM eclipse-temurin:22-jdk-jammy

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR generado desde la fase de construcción
COPY --from=build build/libs/cienpaginas-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto de la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
