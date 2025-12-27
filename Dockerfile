# Используйте multi-arch совместимые образы
FROM --platform=linux/amd64 maven:3.9.6-eclipse-temurin-17 AS builder
LABEL authors="fynjy"
WORKDIR /app

# Копируем pom.xml и скачиваем зависимости
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Копируем исходный код и собираем
COPY src ./src
RUN mvn clean package -DskipTests

# Этап выполнения
FROM --platform=linux/amd64 eclipse-temurin:17-jre-alpine
LABEL authors="fynjy"

WORKDIR /app

# Копируем JAR
COPY --from=builder /app/target/*.jar app.jar

# Переменные окружения для базы данных (можно переопределить при запуске)
ENV DB_URL=jdbc:postgresql://localhost:5432/bitbitforum
ENV DB_USER=postgres
ENV DB_USER_PASSWORD=postgres

EXPOSE 8080

# Запуск приложения
ENTRYPOINT ["java", "-jar", "app.jar"]