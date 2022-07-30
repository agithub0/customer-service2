package customer.api.v1.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties("spring.mail")
public class FromEmail {

	private String host;
	private String username;
	private String password;
	private int port;
}
