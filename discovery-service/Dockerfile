# use the minimal adoptjdk version on linux
FROM eclipse-temurin:24-jdk
# create an installation directory
WORKDIR /opt/app
# copy the jar file in the container
COPY target/discovery-service-0.0.1-SNAPSHOT.jar discovery-service.jar
# set the startup command to execute the jar
ENTRYPOINT ["java", "-jar", "discovery-service.jar"]