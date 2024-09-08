# Imagen base de Eclipse Temurin con OpenJDK 22
FROM eclipse-temurin:22-jdk-jammy AS build

# Instala Gradle manualmente
ENV GRADLE_VERSION=8.2.1
RUN apt-get update && apt-get install -y wget unzip \
    && wget https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip \
    && unzip gradle-${GRADLE_VERSION}-bin.zip -d /opt \
    && rm gradle-${GRADLE_VERSION}-bin.zip \
    && ln -s /opt/gradle-${GRADLE_VERSION}/bin/gradle /usr/bin/gradle

# Establece el directorio de trabajo
WORKDIR /app

# Copia todos los archivos del proyecto
COPY . .

# Construye la aplicación sin ejecutar tests
RUN gradle clean build -x test

# Segunda fase: imagen ligera de OpenJDK para ejecutar el JAR
FROM eclipse-temurin:22-jdk-jammy

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR generado en la fase de construcción
COPY --from=build /app/build/libs/*.jar app.jar

# Exponer el puerto en el que la aplicación se ejecuta
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
