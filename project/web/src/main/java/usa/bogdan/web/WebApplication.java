package usa.bogdan.web;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
	@Bean
	public NewTopic newTopic1() {
		return new NewTopic("updateTopic", 1, (short) 1);
	}
	@Bean
	public NewTopic newTopic2() {
		return new NewTopic("deleteTopic", 1, (short) 1);
	}
}
