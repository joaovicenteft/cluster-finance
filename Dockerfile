FROM openjdk:17

COPY ./out/production/classes/com/example/finance /tmp
WORKDIR /tmp
# Expose the port that your application runs on
EXPOSE 8080
ENTRYPOINT ["java","FinanceApplication"]