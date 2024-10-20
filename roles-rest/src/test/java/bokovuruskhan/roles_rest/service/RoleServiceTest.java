package bokovuruskhan.roles_rest.service;

import bokovuruskhan.database.model.Role;
import bokovuruskhan.database.repository.IRoleRepository;
import bokovuruskhan.roles_rest.controller.ex.RoleNotFoundException;

import bokovuruskhan.roles_rest.service.impl.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RoleServiceTest {

    private IRoleRepository roleRepository;
    private RoleService roleService;

    @BeforeEach
    void setUp() {
        roleRepository = mock(IRoleRepository.class);
        roleService = new RoleService(roleRepository);
    }

    @Test
    void testGetAllRoles() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(1L, "ROLE_USER", "User role", null, null));
        roles.add(new Role(2L, "ROLE_ADMIN", "Admin role", null, null));

        when(roleRepository.findAll()).thenReturn(roles);

        List<Role> result = roleService.getAllRoles();

        assertEquals(2, result.size());
        verify(roleRepository, times(1)).findAll();
    }

    @Test
    void testGetRoleById_Success() {
        Role role = new Role(1L, "ROLE_USER", "User role", null, null);
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));

        Role result = roleService.getRoleById(1L);

        assertEquals("ROLE_USER", result.getName());
        verify(roleRepository, times(1)).findById(1L);
    }

    @Test
    void testGetRoleById_NotFound() {
        when(roleRepository.findById(1L)).thenReturn(Optional.empty());

        RoleNotFoundException exception = assertThrows(RoleNotFoundException.class, () -> roleService.getRoleById(1L));
        assertEquals("Role not found", exception.getMessage());
        verify(roleRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateRole() {
        Role newRole = new Role(null, "ROLE_USER", "User role", null, null);
        Role savedRole = new Role(1L, "ROLE_USER", "User role", null, null);
        when(roleRepository.save(any(Role.class))).thenReturn(savedRole);

        Role result = roleService.createRole(newRole);

        assertNotNull(result.getId());
        assertEquals("ROLE_USER", result.getName());
        verify(roleRepository, times(1)).save(newRole);
    }

    @Test
    void testUpdateRole() {
        Role existingRole = new Role(1L, "ROLE_USER", "User role", null, null);
        Role updateBody = new Role(null, "ROLE_UPDATED_USER", "Updated User role", null, null);
        when(roleRepository.findById(1L)).thenReturn(Optional.of(existingRole));
        when(roleRepository.save(any(Role.class))).thenReturn(existingRole);

        Role result = roleService.updateRole(1L, updateBody);

        assertEquals("ROLE_UPDATED_USER", result.getName());
        verify(roleRepository, times(1)).findById(1L);
        verify(roleRepository, times(1)).save(existingRole);
    }

    @Test
    void testDeleteRole() {
        Role existingRole = new Role(1L, "ROLE_USER", "User role", null, null);
        when(roleRepository.findById(1L)).thenReturn(Optional.of(existingRole));

        Role result = roleService.deleteRole(1L);

        assertEquals(existingRole, result);
        verify(roleRepository, times(1)).findById(1L);
        verify(roleRepository, times(1)).delete(existingRole);
    }

    @Test
    void testDeleteRole_NotFound() {
        when(roleRepository.findById(1L)).thenReturn(Optional.empty());

        RoleNotFoundException exception = assertThrows(RoleNotFoundException.class, () -> roleService.deleteRole(1L));
        assertEquals("Role not found", exception.getMessage());
        verify(roleRepository, times(1)).findById(1L);
        verify(roleRepository, never()).delete(any(Role.class));
    }
}
