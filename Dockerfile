FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /app
COPY . .
RUN ./gradlew clean build -x test

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring

COPY --from=build /app/build/libs/*.jar app.jar
COPY --from=build /app/entrypoint.sh entrypoint.sh

ENTRYPOINT ["./entrypoint.sh"]
