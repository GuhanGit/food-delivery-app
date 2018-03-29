FROM java:8
VOLUME /tmp
ADD target/health-system-service-0.0.1-SNAPSHOT.jar health-system-service-0.0.1-SNAPSHOT.jar
EXPOSE 8081
RUN bash -c 'touch /health-system-service-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongodb/foodDeliveryApp","-Djava.security.egd=file:/dev/./urandom","-jar","/health-system-service-0.0.1-SNAPSHOT.jar"]






