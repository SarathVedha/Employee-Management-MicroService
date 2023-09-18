FROM eclipse-temurin:17

LABEL authors="sarath"

WORKDIR /app

COPY target/OrganizationService-0.0.1-SNAPSHOT.jar /app/OrganizationService.jar

ENTRYPOINT ["java", "-jar", "OrganizationService.jar"]