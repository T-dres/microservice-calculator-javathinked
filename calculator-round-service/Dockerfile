# use the minimal adoptjdk version on linux
FROM openjdk:17-jdk-alpine
# create an installation directory
WORKDIR /opt/app
# copy the jar file in the container
COPY target/calculator-round-service-0.0.1-SNAPSHOT.jar calculator-round-service.jar
# set the startup command to execute the jar
ENTRYPOINT ["java", "-jar", "calculator-round-service.jar"]