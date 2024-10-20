package bokovuruskhan.users_rest.controller;

import bokovuruskhan.users_rest.config.RestConfig;
import bokovuruskhan.users_rest.util.RequestBaseLogic;
import bokovuruskhan.users_rest.data.model.User;
import bokovuruskhan.users_rest.service.IUserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = RestConfig.API_PREFIX + "/users", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserController {

    @Autowired
    private final IUserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/{userId}")
    @ApiResponse(content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = User.class)))
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        return RequestBaseLogic.handleRequest(() -> ResponseEntity.ok(userService.getUserById(userId)));
    }

    @PostMapping
    @ApiResponse(content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = User.class)))
    public ResponseEntity<?> addUser(@RequestBody User user) {
        return RequestBaseLogic.handleRequest(() -> ResponseEntity.ok(userService.createUser(user)));
    }

    @PutMapping(value = "/{userId}")
    @ApiResponse(content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = User.class)))
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody User user) {
        return RequestBaseLogic.handleRequest(() -> ResponseEntity.ok(userService.updateUser(userId, user)));
    }

    @DeleteMapping(value = "/{userId}")
    @ApiResponse(content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = User.class)))
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return RequestBaseLogic.handleRequest(() -> ResponseEntity.ok(userService.deleteUser(userId)));
    }
}
