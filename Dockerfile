FROM openjdk:8

COPY build/libs/va-api-bbw-0.0.1-SNAPSHOT.jar /app/vabbw.jar

CMD ["java", "-jar", "/app/vabbw.jar"]