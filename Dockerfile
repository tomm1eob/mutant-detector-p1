FROM alpine:latest as build

RUN apk update
RUN apk add openjdk17

COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew bootJar --no-daemon
RUN ls -l ./build/libs

FROM openjdk:17-alpine
EXPOSE 8080
COPY --from=build ./build/libs/mutant-detector-0.0.1-SNAPSHOT.jar ./app.jar


ENTRYPOINT ["java", "-jar", "app.jar"]