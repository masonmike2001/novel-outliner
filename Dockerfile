# Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app

# FIX: Changed 'mvc' to 'mvn'
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jre-alpine
COPY --from=build /home/app/target/*.jar app.jar

EXPOSE 10000
ENTRYPOINT ["java", "-Dserver.port=${PORT:10000}", "-jar", "/app.jar"]