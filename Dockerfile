# Use an appropriate base image
FROM openjdk:11

# Set the working directory
WORKDIR /app

# Copy the project files into the container
COPY testjenkins /app

# Build and run your application
CMD ["./gradlew", "run"]
