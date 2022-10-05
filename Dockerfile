FROM gradle:7.4.1 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build

FROM adoptopenjdk:11-jre-hotspot
EXPOSE 8080
ADD ./build/libs/challenge-0.0.1-SNAPSHOT.jar challenge-demo.jar
ENTRYPOINT ["java","-jar","challenge-demo.jar"]
