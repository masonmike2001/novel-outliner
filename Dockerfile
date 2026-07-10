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

# Set the working directory to where the pom.xml lives
WORKDIR /app

# Copy the entire backend directory (this ensures the directory structure is preserved)
COPY backend/ ./backend/

# Copy the frontend build output into the static resources folder
COPY --from=frontend-build /app/novel-outliner-frontend/dist/ ./backend/src/main/resources/static/

# Run Maven from the root but point it to the backend folder pom.xml
RUN mvn -f backend/pom.xml clean package -DskipTests
