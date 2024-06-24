FROM openjdk:22-jdk
ADD target/smartstayjar.jar smartstayjar.jar

LABEL authors="lakhman"

ENTRYPOINT ["java", "-jar", "hello-wrold-docler.jar"]

