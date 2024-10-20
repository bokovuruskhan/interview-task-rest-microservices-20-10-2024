package bokovuruskhan.users_roles_rest.service;

import bokovuruskhan.users_roles_rest.data.model.UserRole;

import java.util.List;

public interface IUserRoleService {

    boolean existsAnyWithUserId(Long userId);

    boolean existsAnyWithRoleId(Long roleId);

    List<UserRole> getUserRoles(Long userId);

    List<UserRole> addRoleToUser(Long roleId, Long userId);

    List<UserRole> deleteRoleFromUser(Long roleId, Long userId);

}
