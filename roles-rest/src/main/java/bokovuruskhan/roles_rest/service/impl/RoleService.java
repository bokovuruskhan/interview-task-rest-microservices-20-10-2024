package bokovuruskhan.roles_rest.service.impl;

import bokovuruskhan.roles_rest.config.RolesConfig;
import bokovuruskhan.roles_rest.controller.ex.RoleNotFoundException;
import bokovuruskhan.roles_rest.data.model.Role;
import bokovuruskhan.roles_rest.data.repository.IRoleRepository;
import bokovuruskhan.roles_rest.service.IRoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class RoleService implements IRoleService {

    @Autowired
    private RolesConfig rolesConfig;

    @Autowired
    private final RestTemplate restTemplate;

    @Autowired
    private final IRoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException("Role not found"));
    }

    @Override
    public Role createRole(Role role) {
        role.setId(null);
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Long roleId, Role updateBody) {
        Role findRole = getRoleById(roleId);
        findRole.setName(updateBody.getName());
        findRole.setDescription(updateBody.getDescription());
        return roleRepository.save(findRole);
    }

    @Override
    public Role deleteRole(Long roleId) {
        checkRoleLinkExists(roleId);
        Role findRole = getRoleById(roleId);
        roleRepository.delete(findRole);
        return findRole;
    }

    private void checkRoleLinkExists(Long roleId) {
        String roleUrl = rolesConfig.USERS_ROLES_REST_API_URL + "/exists-by-role/" + roleId;
        try {
            restTemplate.getForEntity(roleUrl, Void.class);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This role is assigned to a user");
        } catch (HttpStatusCodeException e) {
            log.error(e.getMessage());
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return;
            }
            throw new ResponseStatusException(e.getStatusCode(), "Error occurred while checking role link existence", e);
        }
    }


}
