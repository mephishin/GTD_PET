package com.example.gtd.mapper;


import com.example.gtd.controller.ThingController;
import com.example.gtd.dao.entity.Role;
import com.example.gtd.dao.entity.User;
import com.example.gtd.dao.repo.RoleRepo;
import com.example.gtd.dto.UserDTO;
import com.example.gtd.services.RoleService;
import jakarta.persistence.PersistenceContext;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
@Service
public abstract class UserMapper {
    @Autowired
    public RoleService roleService;

    public abstract UserDTO toDto(User source);
    public abstract User toEntity(UserDTO source);


    @AfterMapping
    protected void RoleIdToRole(@MappingTarget User user, UserDTO userDTO) {
        Set<Long> role_id = userDTO.getRole_id();
        Set<Role> roles = new HashSet<>();
        for(Long id: role_id) {
            roles.add(roleService.findById(id).get());
        }
        user.setRoles(roles);
    }
}
