# --- Stage 1: Build the React Frontend ---
FROM node:18-alpine AS frontend-build
WORKDIR /app

# Copy only the package files first to leverage Docker caching
COPY frontend/novel-outliner-frontend/package*.json ./
RUN npm install

# Copy the actual source code
COPY frontend/novel-outliner-frontend/ .

# Now npm run build will find index.html at the root of /app
RUN npm run build

# --- Stage 2: Build the Spring Boot Backend ---
FROM maven:3.9-eclipse-temurin-17 AS backend-build
WORKDIR /app

COPY backend/pom.xml .
COPY backend/src ./src

# Pull the 'dist' folder from the previous stage
# Since we built in /app/dist in Stage 1, we pull it from there
COPY --from=frontend-build /app/dist ./src/main/resources/static

RUN mvn clean package -DskipTests
