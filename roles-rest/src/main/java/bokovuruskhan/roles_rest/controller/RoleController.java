package bokovuruskhan.roles_rest.controller;

import bokovuruskhan.roles_rest.config.RestConfig;
import bokovuruskhan.roles_rest.util.RequestBaseLogic;
import bokovuruskhan.roles_rest.data.model.Role;
import bokovuruskhan.roles_rest.service.IRoleService;
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
public class RoleController {

    @Autowired
    private final IRoleService userService;

    @GetMapping
    public List<Role> getAllRoles() {
        return userService.getAllRoles();
    }

    @GetMapping(value = "/{roleId}")
    @ApiResponse(content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Role.class)))
    public ResponseEntity<?> getRoleById(@PathVariable Long roleId) {
        return RequestBaseLogic.handleRequest(() -> ResponseEntity.ok(userService.getRoleById(roleId)));
    }

    @PostMapping
    @ApiResponse(content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Role.class)))
    public ResponseEntity<?> addRole(@RequestBody Role role) {
        return RequestBaseLogic.handleRequest(() -> ResponseEntity.ok(userService.createRole(role)));
    }

    @PutMapping(value = "/{roleId}")
    @ApiResponse(content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Role.class)))
    public ResponseEntity<?> updateRole(@PathVariable Long roleId, @RequestBody Role role) {
        return RequestBaseLogic.handleRequest(() -> ResponseEntity.ok(userService.updateRole(roleId, role)));
    }

    @DeleteMapping(value = "/{roleId}")
    @ApiResponse(content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Role.class)))
    public ResponseEntity<?> deleteRole(@PathVariable Long roleId) {
        return RequestBaseLogic.handleRequest(() -> ResponseEntity.ok(userService.deleteRole(roleId)));
    }
}
