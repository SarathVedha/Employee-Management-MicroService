FROM eclipse-temurin:17

LABEL authors="sarath"

WORKDIR /app

COPY target/ApiGateway-0.0.1-SNAPSHOT.jar /app/ApiGateway.jar

ENTRYPOINT ["java", "-jar", "ApiGateway.jar"]