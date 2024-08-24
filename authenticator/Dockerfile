FROM openjdk:17-alpine as build

WORKDIR /app

COPY ./gradle gradle
COPY ./build.gradle ./
COPY ./settings.gradle ./
COPY ./gradlew ./
COPY ./src src

RUN chmod +x /app/gradlew
RUN /app/gradlew build -x test --no-daemon


FROM bellsoft/liberica-openjre-alpine-musl:17

ARG DEPENDENCY=/app/build
COPY --from=build ${DEPENDENCY}/libs /app/lib
RUN mv /app/lib/*SNAPSHOT.jar /app/lib/app.jar
ENTRYPOINT ["java","-jar","app/lib/app.jar"]
