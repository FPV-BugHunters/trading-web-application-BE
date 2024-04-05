FROM eclipse-temurin:17-jdk-alpine

VOLUME /tmp

ARG JAR_FILE

COPY trading-application/target/trading-application-0.0.1-SNAPSHOT.jar app.jar


ENTRYPOINT ["java", "-jar","/app.jar", "--spring.config.location=file:///config/application.properties"]