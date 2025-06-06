# Imagen base de Java 17
FROM openjdk:17-jdk-slim

# Crea un directorio de trabajo
WORKDIR /app

# Etapa 1: construir el JAR con Maven (opcional si ya lo tienes empaquetado)
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: imagen final con el JAR
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]

# Expone el puerto (ajusta si tu app usa otro)
EXPOSE 9093