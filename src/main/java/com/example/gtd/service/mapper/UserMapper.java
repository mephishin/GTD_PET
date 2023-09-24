package com.example.gtd.service.mapper;


import com.example.gtd.dao.entity.Role;
import com.example.gtd.dao.entity.User;
import com.example.gtd.dto.UserDTO;
import com.example.gtd.service.dao.RoleService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Mapper(componentModel = "spring")
@Service
public abstract class UserMapper {
    @Autowired
    public RoleService roleService;

    public abstract UserDTO toDto(User source);
    public abstract User toEntity(UserDTO source);


    @AfterMapping
    protected void RoleIdToRole(@MappingTarget User user, UserDTO userDTO) {
        Set<String> roles = userDTO.getStr_roles();
        Set<Role> new_roles = new HashSet<>();
        if(roles != null) {
            for (String role : roles) {
                Optional<Role> new_role = roleService.findByRolename(role);
                new_role.ifPresent(new_roles::add);
            }
        }
        user.setRoles(new_roles);
    }
}
