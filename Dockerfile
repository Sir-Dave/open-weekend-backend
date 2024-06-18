# Use a slim JDK image for building
FROM maven:3.8-openjdk-18-slim AS build

# Copy project files, excluding unnecessary directories
COPY . .

# Build the application using Maven Wrapper
RUN ./mvnw clean package

# Use a slim JRE image for runtime
FROM openjdk:11-jre-slim

# Copy the generated artifact from the build stage
COPY --from=build target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the application using the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
