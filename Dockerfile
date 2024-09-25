FROM openjdk:21-jdk-slim

COPY target/*.jar student-management.jar

CMD ["java", "-jar", "/student-management.jar"]