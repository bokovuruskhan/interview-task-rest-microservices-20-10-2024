package bokovuruskhan.users_roles_rest.data.repository;


import bokovuruskhan.users_roles_rest.data.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IUserRoleRepository extends JpaRepository<UserRole, Long> {
    @Query(value = "SELECT * FROM user_role WHERE user_id = ?1", nativeQuery = true)
    List<UserRole> findByUserId(Long userId);

    @Query(value = "SELECT * FROM user_role WHERE role_id = ?1 AND user_id = ?2", nativeQuery = true)
    UserRole findByRoleIdAndUserId(Long roleId, Long userId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_role (role_id, user_id) VALUES (?1, ?2)", nativeQuery = true)
    void addRoleToUser(Long roleId, Long userId);
}
