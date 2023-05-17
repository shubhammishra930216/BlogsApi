FROM maven:3.8.3-adoptopenjdk-8-slim as builder

ADD ./pom.xml pom.xml
ADD ./src src/

RUN mvn clean -DskipTests=true package

FROM eclipse-temurin:1.8-jre-alpine

COPY --from=builder target/blogsapi-0.0.1-SNAPSHOT.jar blogsapi.jar

RUN addgroup -S appgroup && adduser -S appuser -G appgroup

USER appuser

EXPOSE 8080

ENTRYPOINT ["java","-jar","blogsapi.jar"]

