FROM openjdk:17
#FROM openjdk:11

COPY build/libs/finance-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]