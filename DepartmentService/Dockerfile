FROM eclipse-temurin:17

LABEL authors="sarath"

WORKDIR /app

COPY target/DepartmentService-0.0.1-SNAPSHOT.jar /app/DepartmentService.jar

ENTRYPOINT ["java", "-jar", "DepartmentService.jar"]