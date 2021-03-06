package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.jdbc.*;
import org.springframework.boot.autoconfigure.mongo.*;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableEurekaClient
@EnableAutoConfiguration
public class ClaimSystemInfoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClaimSystemInfoServiceApplication.class, args);
	}
	 @Bean
	    public RestTemplate getRestTemplate() {
	        return new RestTemplate();
	    }

}
