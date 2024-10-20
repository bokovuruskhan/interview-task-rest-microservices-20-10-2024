package bokovuruskhan.roles_rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "bokovuruskhan.database")
public class RolesRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RolesRestApplication.class, args);
	}

}
