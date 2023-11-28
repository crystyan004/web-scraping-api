FROM openjdk:17-oracle
COPY target/*.jar web-scraping-api.jar
EXPOSE 8080:8080
ENTRYPOINT ["java", "-jar", "web-scraping-api.jar"]