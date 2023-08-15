# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app

# Copy the pom.xml file first to leverage Docker cache
COPY pom.xml .

# Download and cache the Maven dependencies
RUN mvn dependency:go-offline

# Copy the source code
COPY src/ src/

# Build the application
RUN mvn package -DskipTests

# Stage 2: Create the final image
FROM openjdk:17-alpine
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
