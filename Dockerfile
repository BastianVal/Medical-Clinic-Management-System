FROM eclipse-temurin:21-jre-alpine
ARG JAR_FILE=target/appointments-0.0.1.jar
COPY ${JAR_FILE} app_appointments.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_appointments.jar"]