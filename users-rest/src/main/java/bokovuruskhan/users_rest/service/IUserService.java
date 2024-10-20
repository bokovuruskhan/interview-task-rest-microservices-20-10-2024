package bokovuruskhan.users_rest.service;

import bokovuruskhan.users_rest.data.model.User;

import java.util.List;

public interface IUserService {
    List<User> getAllUsers();

    User getUserById(Long id);

    User createUser(User user);

    User updateUser(Long userId, User updateBody);

    User deleteUser(Long userId);

}
