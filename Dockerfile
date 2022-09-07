FROM alpine:3.16

RUN apk add openjdk11
COPY build/libs/car-service.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]