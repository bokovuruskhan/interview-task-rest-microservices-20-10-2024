package bokovuruskhan.users_rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "bokovuruskhan.database")
public class UsersRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersRestApplication.class, args);
	}

}
