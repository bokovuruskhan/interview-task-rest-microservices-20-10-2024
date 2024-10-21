package bokovuruskhan.users_roles_rest.service.impl;

import bokovuruskhan.users_roles_rest.config.UsersRolesConfig;
import bokovuruskhan.users_roles_rest.data.model.UserRole;
import bokovuruskhan.users_roles_rest.data.repository.IUserRoleRepository;
import bokovuruskhan.users_roles_rest.service.IUserRoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class UserRoleService implements IUserRoleService {

    @Autowired
    private UsersRolesConfig usersRolesConfig;

    @Autowired
    private final RestTemplate restTemplate;

    @Autowired
    private final IUserRoleRepository userRoleRepository;

    @Override
    public boolean existsAnyWithRoleId(Long roleId) {
        return userRoleRepository.existsByRoleId(roleId);
    }

    @Override
    public boolean existsAnyWithUserId(Long userId) {
        return userRoleRepository.existsByUserId(userId);
    }

    @Override
    public List<UserRole> getUserRoles(Long userId) {
        return userRoleRepository.findByUserId(userId);
    }

    @Override
    public List<UserRole> addRoleToUser(Long roleId, Long userId) {
        checkUserExists(userId);
        checkRoleExists(roleId);

        userRoleRepository.addRoleToUser(roleId, userId);
        return getUserRoles(userId);
    }

    @Override
    public List<UserRole> deleteRoleFromUser(Long roleId, Long userId) {
        userRoleRepository.delete(userRoleRepository.findByRoleIdAndUserId(roleId, userId));
        return getUserRoles(userId);
    }

    private void checkUserExists(Long userId) {
        String userUrl = usersRolesConfig.USERS_REST_API_URL + userId;
        try {
            restTemplate.getForEntity(userUrl, String.class);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    private void checkRoleExists(Long roleId) {
        String roleUrl = usersRolesConfig.ROLES_REST_API_URL + roleId;
        try {
            restTemplate.getForEntity(roleUrl, String.class);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found");
        }
    }

}
