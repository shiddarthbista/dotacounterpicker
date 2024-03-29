FROM ubuntu:latest AS build
RUN apt-get update && apt-get install -y openjdk-17-jdk
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew bootJar --no-daemon

FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build /build/libs/dotacounterpicker-1.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]