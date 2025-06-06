# Imagen base de Java 17
FROM openjdk:17-jdk-slim

# Crea un directorio de trabajo
WORKDIR /app

# Copia el .jar generado por Maven (ajusta el nombre del archivo si es necesario)
COPY target/*.jar app.jar

# Expone el puerto (ajusta si tu app usa otro)
EXPOSE 9093

# Comando que se ejecuta al iniciar el contenedor
ENTRYPOINT ["java", "-jar", "app.jar"]