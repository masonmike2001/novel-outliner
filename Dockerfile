# --- Stage 1: Build the React Frontend ---
FROM node:18-alpine AS frontend-build
WORKDIR /app
COPY frontend/package*.json ./
RUN npm install
COPY frontend/ ./
# Adjust this if your build output is /build instead of /dist
RUN npm run build 

# --- Stage 2: Build the Spring Boot Backend ---
FROM maven:3.9-eclipse-temurin-17 AS backend-build
WORKDIR /app

# Copy Maven files first to leverage Docker layer caching
COPY backend/pom.xml .
COPY backend/src ./src

# Copy the built React files into the Spring Boot resources directory
# Note: Adjust 'dist' to 'build' if using create-react-app
COPY --from=frontend-build /app/dist ./src/main/resources/static

# Build the final JAR
RUN mvn clean package -DskipTests

# --- Stage 3: Runtime Environment ---
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copy only the compiled JAR from the previous stage
COPY --from=backend-build /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
