# --- Stage 1: Build the React Frontend ---
FROM node:22.12.0-alpine AS frontend-build

# Set the WORKDIR to the folder where your source code actually lives
WORKDIR /app/novel-outliner-frontend

# Copy package files from the subfolder
COPY frontend/novel-outliner-frontend/package*.json ./
RUN npm install

# Copy the rest of the source code
COPY frontend/novel-outliner-frontend/ .

# Now, because we are inside the novel-outliner-frontend folder,
# index.html will be at the root of the WORKDIR
# Change line 16 to:
RUN npx vite build --config vite.config.js --base ./
# --- Stage 2: Build the Spring Boot Backend ---
FROM maven:3.9-eclipse-temurin-17 AS backend-build
WORKDIR /app

COPY backend/pom.xml .
COPY backend/src ./src

# Pull the 'dist' folder from the previous stage
# Since we built in /app/dist in Stage 1, we pull it from there
COPY --from=frontend-build /app/dist ./src/main/resources/static

RUN mvn clean package -DskipTests
