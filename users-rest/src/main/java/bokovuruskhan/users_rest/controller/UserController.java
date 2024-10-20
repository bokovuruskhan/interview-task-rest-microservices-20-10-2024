package bokovuruskhan.users_rest.controller;

import bokovuruskhan.users_rest.config.RestConfig;
import bokovuruskhan.users_rest.controller.ex.UserNotFoundException;
import bokovuruskhan.users_rest.data.model.User;
import bokovuruskhan.users_rest.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = RestConfig.API_PREFIX, produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserController {

    @Autowired
    private final IUserService userService;

    @GetMapping
    private List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/{userId}")
    private User getUserById(@PathVariable Long userId)  {
        return userService.getUserById(userId);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


}
