# Stage 2: Build the Spring Boot backend
FROM maven:3.9-eclipse-temurin-17 AS backend-build
WORKDIR /app
COPY backend/pom.xml .
COPY backend/src ./src

# Ensure this path matches the actual output folder of your React build
# If it's 'build' instead of 'dist', change it here:
COPY --from=frontend-build /app/dist ./src/main/resources/static

RUN mvn clean package -DskipTests
