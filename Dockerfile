FROM openjdk
ADD target/*.jar services.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/services.jar"]