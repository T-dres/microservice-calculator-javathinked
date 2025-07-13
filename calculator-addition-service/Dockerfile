# use the minimal adoptjdk version on linux
FROM eclipse-temurin:24-jdk
# create an installation directory
WORKDIR /opt/app
# copy the jar file in the container
COPY target/calculator-addition-service-0.0.1-SNAPSHOT.jar calculator-addition-service.jar
# set the startup command to execute the jar
ENTRYPOINT ["java", "-jar", "calculator-addition-service.jar"]