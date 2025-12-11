# Stage 1: Build the application using Maven
FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml .
# (Optional) Pre-download dependencies to speed up future builds
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

# Stage 2: Create the final, secure Alpine image
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

RUN apk update && apk upgrade

# SECURITY BEST PRACTICE: Create a non-root user
RUN addgroup -S spring && adduser -S spring -G spring

# Copy the JAR from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Switch to the non-root user
USER spring:spring

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]