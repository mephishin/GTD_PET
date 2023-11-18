package com.example.gtd.service.mapper;


import com.example.gtd.customException.NotFoundInDbException;
import com.example.gtd.dao.entity.Role;
import com.example.gtd.dao.entity.User;
import com.example.gtd.dto.UserDTO;
import com.example.gtd.service.dao.RoleDAO;
import lombok.Builder;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Mapper(componentModel = "spring")
@Service
public abstract class UserMapper {
    @Autowired
    public RoleDAO roleService;
    @Autowired
    public PasswordEncoder passwordEncoder;

    @Mapping(target = "roles", ignore = true)
    public abstract UserDTO toDto(User source);
    @Mapping(target = "roles", ignore = true)
    public abstract User toEntity(UserDTO source) throws NotFoundInDbException;


    @AfterMapping
    protected void RoleNameToRole(@MappingTarget User.UserBuilder user, UserDTO userDTO) throws NotFoundInDbException {
        Set<String> roles = userDTO.getRoles();
        Set<Role> new_roles = new HashSet<>();
        if(roles != null) {
            for (String role : roles) {
                Role new_role = roleService.findByRolename(role);
                new_roles.add(new_role);
            }
        }
        user.roles(new_roles);
        user.password(passwordEncoder.encode(userDTO.getPassword()));
    }

    @AfterMapping
    protected void RoleToRoleName(@MappingTarget UserDTO.UserDTOBuilder userDTO, User user) {
        Set<Role> roles = user.getRoles();
        Set<String> new_roles = new HashSet<>();
        if(roles != null) {
            for (Role role : roles) {
                new_roles.add(role.getRolename());
            }
        }
        userDTO.roles(new_roles);
    }

}
