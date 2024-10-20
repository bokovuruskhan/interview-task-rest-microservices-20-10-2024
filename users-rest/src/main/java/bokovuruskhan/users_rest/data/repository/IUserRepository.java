package bokovuruskhan.users_rest.data.repository;

import bokovuruskhan.users_rest.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
}
