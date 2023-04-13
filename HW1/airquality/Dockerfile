FROM openjdk:17
CMD ["mvn", "package", "spring-boot:repackage"]
ARG JAR_FILE=target/airquality-1.0-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]