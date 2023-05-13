FROM lolhens/baseimage-openjre
ADD target/universityTransportation-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 80
ENTRYPOINT ["java", "-jar", "springbootApp.jar"]