package bokovuruskhan.roles_rest.data.repository;

import bokovuruskhan.roles_rest.data.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
}
