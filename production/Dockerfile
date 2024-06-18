# Use a slim JRE image for runtime
FROM openjdk:11-jre-slim

# Copy the generated artifact from the build stage
COPY *.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the application using the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]