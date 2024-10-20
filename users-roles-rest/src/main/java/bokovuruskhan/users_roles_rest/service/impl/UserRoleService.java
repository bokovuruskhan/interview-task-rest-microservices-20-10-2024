package bokovuruskhan.users_roles_rest.service.impl;

import bokovuruskhan.users_roles_rest.data.model.UserRole;
import bokovuruskhan.users_roles_rest.data.repository.IUserRoleRepository;
import bokovuruskhan.users_roles_rest.service.IUserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class UserRoleService implements IUserRoleService {

    @Autowired
    private final IUserRoleRepository userRoleRepository;

    @Override
    public List<UserRole> getUserRoles(Long userId) {
        return userRoleRepository.findByUserId(userId);
    }

    @Override
    public List<UserRole> addRoleToUser(Long roleId, Long userId) {
        userRoleRepository.addRoleToUser(roleId, userId);
        return getUserRoles(userId);
    }

    @Override
    public List<UserRole> deleteRoleFromUser(Long roleId, Long userId) {
        userRoleRepository.delete(userRoleRepository.findByRoleIdAndUserId(roleId, userId));
        return getUserRoles(userId);
    }
}
