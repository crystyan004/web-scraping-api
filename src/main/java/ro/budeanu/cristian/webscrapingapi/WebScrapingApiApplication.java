package ro.budeanu.cristian.webscrapingapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WebScrapingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebScrapingApiApplication.class, args);
	}

}
