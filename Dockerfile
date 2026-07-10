# --- Stage 1: Frontend ---
FROM node:18-alpine AS frontend-build
WORKDIR /app
COPY frontend/package*.json ./
RUN npm ci
COPY frontend/ ./
# Explicitly build
RUN npm run build 

# --- Stage 2: Backend ---
FROM maven:3.9-eclipse-temurin-17 AS backend-build
WORKDIR /app
# Copy only necessary files to speed up
COPY backend/pom.xml .
COPY backend/src ./src
# Pull frontend build into the right place
COPY --from=frontend-build /app/dist ./src/main/resources/static
# Build JAR
RUN mvn clean package -DskipTests

# --- Stage 3: Runtime ---
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=backend-build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
