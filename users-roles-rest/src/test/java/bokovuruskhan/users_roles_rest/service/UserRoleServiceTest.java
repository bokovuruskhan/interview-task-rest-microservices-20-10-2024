package bokovuruskhan.users_roles_rest.service;

import bokovuruskhan.users_roles_rest.data.model.UserRole;
import bokovuruskhan.users_roles_rest.data.model.UserRoleId;
import bokovuruskhan.users_roles_rest.data.repository.IUserRoleRepository;
import bokovuruskhan.users_roles_rest.service.impl.UserRoleService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserRoleServiceTest {

    @Mock
    private IUserRoleRepository userRoleRepository;

    @InjectMocks
    private UserRoleService userRoleService;

    private UserRole userRole;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        userRole = new UserRole();
        userRole.setId(new UserRoleId(1L, 1L));
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void testGetUserRoles() {
        List<UserRole> userRoles = new ArrayList<>();
        userRoles.add(userRole);

        when(userRoleRepository.findByUserId(1L)).thenReturn(userRoles);

        List<UserRole> result = userRoleService.getUserRoles(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(userRole, result.get(0));

        verify(userRoleRepository, times(1)).findByUserId(1L);
    }

    @Test
    void testAddRoleToUser() {
        userRoleService.addRoleToUser(1L, 1L);
        when(userRoleRepository.findByUserId(1L)).thenReturn(List.of(userRole));

        List<UserRole> result = userRoleService.addRoleToUser(1L, 1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(userRole, result.get(0));
    }

    @Test
    void testDeleteRoleFromUser() {
        when(userRoleRepository.findByRoleIdAndUserId(1L, 1L)).thenReturn(userRole);

        UserRole result = userRoleService.deleteRoleFromUser(1L, 1L);

        assertNotNull(result);
        assertEquals(userRole, result);

        verify(userRoleRepository, times(1)).findByRoleIdAndUserId(1L, 1L);
        verify(userRoleRepository, times(1)).delete(userRole);
    }
}
