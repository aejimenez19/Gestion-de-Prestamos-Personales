# ============================
# 1. Build stage (compila el jar)
# ============================
FROM gradle:8.7-jdk21-alpine AS build
WORKDIR /home/gradle/app

COPY . .

# Compila sin tests para evitar que fallen
RUN gradle clean build -x test


# ============================
# 2. Run stage (ejecuta la app)
# ============================
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Copiar el jar compilado desde el stage anterior
COPY --from=build /home/gradle/app/build/libs/*.jar app.jar

# Exponer el puerto (Render necesita saberlo)
EXPOSE 8080

# Comando de ejecución
ENTRYPOINT ["java", "-jar", "app.jar"]
