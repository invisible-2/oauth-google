FROM eclipse-temurin:17-jdk-alpine
COPY pack/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8082