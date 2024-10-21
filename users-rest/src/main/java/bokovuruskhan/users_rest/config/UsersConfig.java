package bokovuruskhan.users_rest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class UsersConfig {

    @Value("${users-roles-rest-api-url}")
    public String USERS_ROLES_REST_API_URL;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
