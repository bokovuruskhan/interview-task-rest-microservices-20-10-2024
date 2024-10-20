package bokovuruskhan.users_roles_rest.controller;

import bokovuruskhan.users_roles_rest.config.RestConfig;
import bokovuruskhan.users_roles_rest.data.model.UserRole;
import bokovuruskhan.users_roles_rest.service.IUserRoleService;
import bokovuruskhan.users_roles_rest.util.RequestBaseLogic;
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
@RequestMapping(value = RestConfig.API_PREFIX, produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserRoleController {

    @Autowired
    private final IUserRoleService userRoleService;

    @GetMapping(value = "/users/{userId}/roles")
    public List<UserRole> getUserRoles(@PathVariable Long userId) {
        return userRoleService.getUserRoles(userId);
    }

    @PostMapping(value = "/users/{userId}/roles/{roleId}")
    @ApiResponse(content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserRole.class)))
    public ResponseEntity<?> addRoleToUser(@PathVariable Long roleId, @PathVariable Long userId) {
        return RequestBaseLogic.handleRequest(() -> ResponseEntity.ok(userRoleService.addRoleToUser(roleId, userId)));
    }

    @DeleteMapping(value = "/users/{userId}/roles/{roleId}")
    @ApiResponse(content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserRole.class)))
    public ResponseEntity<?> deleteRoleFromUser(@PathVariable Long roleId, @PathVariable Long userId) {
        return RequestBaseLogic.handleRequest(() -> ResponseEntity.ok(userRoleService.deleteRoleFromUser(roleId, userId)));
    }
}
