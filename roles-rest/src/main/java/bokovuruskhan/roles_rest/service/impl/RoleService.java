package bokovuruskhan.roles_rest.service.impl;

import bokovuruskhan.database.model.Role;
import bokovuruskhan.database.repository.IRoleRepository;
import bokovuruskhan.roles_rest.controller.ex.RoleNotFoundException;
import bokovuruskhan.roles_rest.service.IRoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService implements IRoleService {

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
        Role findRole = getRoleById(roleId);
        roleRepository.delete(findRole);
        return findRole;
    }
}
