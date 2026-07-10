# --- Stage 1: Build the React Frontend ---
# --- Stage 1: Build the React Frontend ---
FROM node:22.12.0-alpine AS frontend-build
WORKDIR /app

# Copy package files
COPY frontend/novel-outliner-frontend/package*.json ./

# FORCE a clean install - this deletes any cached/incompatible binaries
RUN npm cache clean --force && npm install

# Copy source code
COPY frontend/novel-outliner-frontend/ .

# Run only the vite build, skipping the TypeScript type-checker (tsc)
RUN npx vite build

# --- Stage 2: Build the Spring Boot Backend ---
FROM maven:3.9-eclipse-temurin-17 AS backend-build
WORKDIR /app

COPY backend/pom.xml .
COPY backend/src ./src

# Pull the 'dist' folder from the previous stage
# Since we built in /app/dist in Stage 1, we pull it from there
COPY --from=frontend-build /app/dist ./src/main/resources/static

RUN mvn clean package -DskipTests
