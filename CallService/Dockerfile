FROM openjdk:11-jre-slim
COPY ./target/CallService-0.0.1-SNAPSHOT.jar /opt/app.jar
WORKDIR /opt/app
ENTRYPOINT ["java","-jar","/opt/app.jar"]