package bokovuruskhan.users_roles_rest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class UsersRolesConfig {

    @Value("${users-rest-api-url}")
    public String USERS_REST_API_URL;

    @Value("${roles-rest-api-url}")
    public String ROLES_REST_API_URL;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
