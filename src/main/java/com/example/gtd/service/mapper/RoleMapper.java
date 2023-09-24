package com.example.gtd.service.mapper;

import com.example.gtd.dao.entity.Role;
import com.example.gtd.dto.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
@Service
public interface RoleMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "rolename", target = "rolename")
    RoleDTO toDto(Role source);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "rolename", target = "rolename")
    Role toEntity(RoleDTO source);
}
