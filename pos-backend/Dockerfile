# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /workspace

# Copy everything to include resources like application.yml
COPY . .

# Build the project
RUN mvn -B -DskipTests package

# Stage 2: Runtime
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy built JAR from build stage
COPY --from=build /workspace/target/*.jar app.jar

# Create non-root user and use it
RUN addgroup --system spring && adduser --system --ingroup spring spring
USER spring:spring

# Expose the port
EXPOSE 8080

# Start the application
ENTRYPOINT ["java", "-jar", "app.jar"]
