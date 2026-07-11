# --- Stage 1: Build the React Frontend ---
FROM node:22.12.0-alpine AS frontend-build

# Set WORKDIR to the actual folder containing package.json
WORKDIR /app/novel-outliner-frontend

# Copy package files from your nested structure
COPY frontend/novel-outliner-frontend/package*.json ./
RUN npm install

# Copy the rest of the source code
COPY frontend/novel-outliner-frontend/ .

# Run build (Vite is now looking at /app/novel-outliner-frontend/)
RUN npx vite build

# --- Stage 2: Build the Spring Boot Backend ---
FROM maven:3.9-eclipse-temurin-17 AS backend-build
WORKDIR /app

# Copy the entire backend directory
COPY backend/ ./backend/

# DEBUG: List all files to verify pom.xml exists at /app/backend/pom.xml
RUN ls -R /app/backend

# Run the command with a dummy 'echo' to see if it even reaches this point
RUN echo "DEBUG: Starting Maven build..."
RUN mvn -f backend/pom.xml clean package -DskipTests
