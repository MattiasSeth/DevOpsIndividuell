# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the gradle wrapper and configuration files to the container
COPY gradlew /app/
COPY gradle /app/gradle
COPY build.gradle.kts settings.gradle.kts /app/

# Download dependencies
RUN chmod +x gradlew
RUN ./gradlew build --no-daemon || return 0

# Copy the rest of the project files
COPY . /app/

# Build the application
RUN ./gradlew build --no-daemon

# Expose port 8080 to the outside world
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "build/libs/DevOpsIndividuell-0.0.1-SNAPSHOT.jar"]


