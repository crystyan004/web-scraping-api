FROM openjdk:17
EXPOSE 8080
ADD target/web-scraping-api.jar web-scraping-api.jar
ENTRYPOINT ["java", "-jar", "web-scraping-api.jar"]