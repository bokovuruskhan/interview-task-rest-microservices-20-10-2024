package bokovuruskhan.users_rest.service.impl;

import bokovuruskhan.users_rest.controller.ex.UserNotFoundException;
import bokovuruskhan.users_rest.data.model.User;
import bokovuruskhan.users_rest.data.repository.IUserRepository;
import bokovuruskhan.users_rest.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private final IUserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User updateUser(Long userId, User user) {
        return null;
    }

    @Override
    public User deleteUser(Long userId) {
        return null;
    }
}
