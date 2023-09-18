FROM eclipse-temurin:17

LABEL authors="sarath"

WORKDIR /app

COPY target/ConfigServer-0.0.1-SNAPSHOT.jar /app/ConfigServer.jar

ENTRYPOINT ["java", "-jar", "ConfigServer.jar"]