package bokovuruskhan.users_rest.data.repository;

import bokovuruskhan.users_rest.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
