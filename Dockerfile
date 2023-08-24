# Use an appropriate base image
FROM openjdk:11

# Set the working directory
WORKDIR /app

RUN git clone https://github.com/lahmaroussama/testjenkins.git /app

# Copy the project files into the container
# Build and run your application

