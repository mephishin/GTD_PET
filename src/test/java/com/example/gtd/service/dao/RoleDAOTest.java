package com.example.gtd.service.dao;

import com.example.gtd.dao.entity.Role;
import com.example.gtd.dao.entity.User;
import com.example.gtd.dao.repo.RoleRepo;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.*;

@Data
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RoleDAOTest {

    @Mock
    private RoleRepo roleRepo;

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private RoleDAO roleDAO;

    private Role roleAdmin;

    private Role roleUser;

    private User userWithRolesAdminUser;

    private User userWithRolesAdmin;

    private User userWithRolesUser;

    private Set<User> userSet;

    private Set<Role> roleSet;

    @BeforeEach
    public void init() {
        roleAdmin = Role.builder().id(1L).rolename("ROLE_ADMIN").build();
        roleUser = Role.builder().id(2L).rolename("ROLE_USER").build();

        userWithRolesAdminUser = User.builder().id(1L).username("User1").password("Password1").roles(Set.of(
                roleAdmin, roleUser)).build();
        userWithRolesAdmin = User.builder().id(2L).username("User2").password("Password2").roles(Set.of(
                roleAdmin)).build();
        userWithRolesUser = User.builder().id(3L).username("User3").password("Password3").roles(Set.of(
                roleUser)).build();

         userSet = new HashSet<>(Set.of(userWithRolesAdminUser, userWithRolesAdmin, userWithRolesUser));
         roleSet = new HashSet<>(Set.of(roleAdmin, roleUser));
    }

    @Tag("delete")
    @Test
    public void RoleDAO_delete_role_with_related_users() {
        Mockito.when(roleRepo.findById(Mockito.any(Long.class))).thenReturn(Optional.of(roleAdmin));
        Mockito.when(userDAO.findByRoles(roleAdmin)).thenReturn(userSet);

        Set<User> userSetUPD = new HashSet<>(Set.of(
                User.builder().id(1L).username("User1").password("Password1").roles(Set.of(
                        roleUser)).build(),
                User.builder().id(2L).username("User2").password("Password2").roles(Set.of()).build(),
                User.builder().id(3L).username("User3").password("Password3").roles(Set.of(
                        roleUser)).build()
        ));

        Assertions.assertEquals(userSetUPD, roleDAO.delete(roleAdmin.getId()));

    }

    @Tag("delete")
    @Test
    public void RoleDAO_delete_role_with_non_related_users() {
        Mockito.when(roleRepo.findById(Mockito.any(Long.class))).thenReturn(Optional.of(roleAdmin));
        Mockito.when(userDAO.findByRoles(roleAdmin)).thenReturn(new HashSet<>());

        Set<User> userSetUPD = new HashSet<>();

        Assertions.assertEquals(userSetUPD, roleDAO.delete(roleAdmin.getId()));
    }


    @Tag("patch")
    @Test
    public void RoleDAO_patch_with_() {
        Mockito.when(roleRepo.findById(Mockito.any(Long.class))).thenReturn(Optional.of(roleAdmin));
        Mockito.when(roleRepo.save(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());

        Role roleUPD = Role.builder().id(1L).rolename("ROLE_ADMIN_UPD").build();

        HashMap<Object, Object> fields = new HashMap<>();
        fields.put("rolename", "ROLE_ADMIN_UPD");

        Assertions.assertEquals(roleUPD, roleDAO.patch(1L, fields));
    }
}
