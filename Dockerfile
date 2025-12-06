# Usar una imagen base oficial de OpenJDK 21
FROM eclipse-temurin:21-jdk-alpine 

# Variable para el jar generado
env JAR_FILE=build/libs/*.jar

# Crear directorio para la app
WORKDIR /app

# Copiar el jar compilado de build/libs
COPY build/libs/*.jar app.jar

# Exponer el puerto de la aplicación Spring Boot
EXPOSE 8080

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
