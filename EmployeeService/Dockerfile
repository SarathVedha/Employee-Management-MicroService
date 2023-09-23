FROM eclipse-temurin:17

LABEL authors="Sarath"

WORKDIR /app

COPY target/EmployeeService-0.0.1-SNAPSHOT.jar /app/EmployeeService.jar

ENTRYPOINT ["java", "-jar", "EmployeeService.jar"]