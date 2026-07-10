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

# Set the WORKDIR to the backend folder
WORKDIR /app/backend

# Copy pom.xml and source code from the backend directory
COPY backend/pom.xml .
COPY backend/src ./src

# Copy the build output from Stage 1 into the target location for Spring Boot
# Note the path: we are copying INTO /app/backend/src/main/resources/static
COPY --from=frontend-build /app/novel-outliner-frontend/dist ./src/main/resources/static

# Now this will work because the pom.xml is in the current WORKDIR
RUN mvn clean package -DskipTests


