package bokovuruskhan.users_rest.data.repository;

import bokovuruskhan.users_rest.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
