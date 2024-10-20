package bokovuruskhan.roles_rest.service;

import bokovuruskhan.roles_rest.data.model.Role;

import java.util.List;

public interface IRoleService {
    List<Role> getAllRoles();

    Role getRoleById(Long id);

    Role createRole(Role role);

    Role updateRole(Long roleId, Role updateBody);

    Role deleteRole(Long roleId);

}
