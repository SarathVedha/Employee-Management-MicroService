FROM eclipse-temurin:17

LABEL authors="sarath"

WORKDIR /app

COPY target/ServiceRegistry-0.0.1-SNAPSHOT.jar /app/ServiceRegistry.jar

ENTRYPOINT ["java", "-jar", "ServiceRegistry.jar"]