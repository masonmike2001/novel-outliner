# Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-17 AS build

# 1. Set the working directory first
WORKDIR /home/app

# 2. Copy the files relative to the working directory
COPY src ./src
COPY pom.xml ./pom.xml

# 3. Now run maven safely from inside /home/app
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Copy the compiled jar from the build stage's target directory
COPY --from=build /home/app/target/*.jar app.jar

EXPOSE 10000
ENTRYPOINT ["java", "-Dserver.port=${PORT:10000}", "-jar", "/app/app.jar"]