package bokovuruskhan.users_rest.service.impl;

import bokovuruskhan.users_rest.config.UsersConfig;
import bokovuruskhan.users_rest.controller.ex.UserNotFoundException;
import bokovuruskhan.users_rest.data.model.User;
import bokovuruskhan.users_rest.data.repository.IUserRepository;
import bokovuruskhan.users_rest.service.IUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserService implements IUserService {

    @Autowired
    private UsersConfig usersConfig;

    @Autowired
    private final RestTemplate restTemplate;

    @Autowired
    private final IUserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public User createUser(User user) {
        user.setId(null);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, User updateBody) {
        User findUser = getUserById(userId);
        findUser.setEmail(updateBody.getEmail());
        findUser.setUsername(updateBody.getUsername());
        findUser.setPassword(updateBody.getPassword());
        return userRepository.save(findUser);
    }

    @Override
    public User deleteUser(Long userId) {
        checkUserLinkExists(userId);
        User findUser = getUserById(userId);
        userRepository.delete(findUser);
        return findUser;
    }

    private void checkUserLinkExists(Long userId) {
        String userUrl = usersConfig.USERS_ROLES_REST_API_URL + "/exists-by-user/" + userId;
        try {
            restTemplate.getForEntity(userUrl, Void.class);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This user is assigned to a group");
        } catch (HttpStatusCodeException e) {
            log.error(e.getMessage());
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return;
            }
            throw new ResponseStatusException(e.getStatusCode(), "Error occurred while checking user link existence", e);
        }
    }


}
