

FROM openjdk:22-jdk

ADD smartstayjar.jar smartstayjar.jar

LABEL authors="lakhman"

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "smartstayjar.jar"]

