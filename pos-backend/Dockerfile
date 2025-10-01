# Stage 1: build
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests package

# Stage 2: runtime
FROM eclipse-temurin:17-jre
ARG JAR_FILE=/workspace/target/*.jar
COPY --from=build ${JAR_FILE} /app/app.jar

# run as non-root user
RUN addgroup --system spring && adduser --system --ingroup spring spring || true
USER spring:spring

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
