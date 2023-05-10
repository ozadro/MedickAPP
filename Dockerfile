FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY target/spring-medick-docker.jar spring-medick-docker.jar
EXPOSE 8080
CMD ["java", "-jar", "my-app.jar", "--server.address=myapp.example.com"]