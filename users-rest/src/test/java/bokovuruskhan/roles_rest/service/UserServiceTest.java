package bokovuruskhan.roles_rest.service;

import bokovuruskhan.roles_rest.controller.ex.UserNotFoundException;
import bokovuruskhan.roles_rest.data.model.User;
import bokovuruskhan.roles_rest.data.repository.IUserRepository;
import bokovuruskhan.roles_rest.service.impl.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private IUserRepository userRepository;

    private AutoCloseable closeable;

    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        user1 = new User(1L, "test@example.com", "testUser", "password", LocalDateTime.now(), LocalDateTime.now());
        user2 = new User(2L, "test2@example.com", "testUser2", "password2", LocalDateTime.now(), LocalDateTime.now());
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userService.getAllUsers();

        assertEquals(2, users.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetUserById_UserExists() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));

        User user = userService.getUserById(1L);

        assertNotNull(user);
        assertEquals("test@example.com", user.getEmail());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testGetUserById_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(UserNotFoundException.class, () -> userService.getUserById(1L));

        assertEquals("User not found", exception.getMessage());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateUser() {
        when(userRepository.save(any(User.class))).thenReturn(user1);

        User createdUser = userService.createUser(new User(null, "test@example.com", "testUser", "password", LocalDateTime.now(), LocalDateTime.now()));

        assertNotNull(createdUser);
        assertEquals("test@example.com", createdUser.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testUpdateUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(userRepository.save(any(User.class))).thenReturn(user1);

        User updatedUser = userService.updateUser(1L, new User(null, "updated@example.com", "updatedUser", "newPassword", LocalDateTime.now(), LocalDateTime.now()));

        assertNotNull(updatedUser);
        assertEquals("updated@example.com", updatedUser.getEmail());
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testDeleteUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));

        User deletedUser = userService.deleteUser(1L);

        assertNotNull(deletedUser);
        assertEquals("test@example.com", deletedUser.getEmail());
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).delete(user1);
    }
}
