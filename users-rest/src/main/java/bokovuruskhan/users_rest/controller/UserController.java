package bokovuruskhan.users_rest.controller;

import bokovuruskhan.users_rest.config.RestConfig;
import bokovuruskhan.users_rest.data.model.User;
import bokovuruskhan.users_rest.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = RestConfig.API_PREFIX, produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping
    private List<User> getAllUsers() {
        return userService.getAllUsers();
    }



}
