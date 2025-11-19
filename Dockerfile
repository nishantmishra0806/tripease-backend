#use an official jdk image
FROM eclipse-temurin:17-jre

WORKDIR /app

#Copy the jar file to container
COPY out/artifacts/tripease_jar/tripease.jar app.jar

EXPOSE 8080

#Run the application
ENTRYPOINT ["java" , "-jar" , "/appapp.jar"]

ENV SPRING_KAFKA_BOOTSTRAP_SERVERS=kafka:9092
ENV SPRING_KAFKA_CONSUMER_GROUP_ID=tripease-group
ENV SPRING_KAFKA_CONSUMER_AUTO_OFFSET_RESET=earliest