FROM eclipse-temurin:17

LABEL authors="sarath"

WORKDIR /app

COPY target/AdminService-0.0.1-SNAPSHOT.jar /app/AdminService.jar

ENTRYPOINT ["java", "-jar", "AdminService.jar"]