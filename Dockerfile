FROM openjdk:21-jdk-slim

COPY target/*.jar app.jar

CMD ["java", "-jar", "/app.jar"]