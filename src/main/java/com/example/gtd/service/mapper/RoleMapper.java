package com.example.gtd.service.mapper;

import com.example.gtd.dao.entity.Role;
import com.example.gtd.dto.RoleDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
@Service
public interface RoleMapper {

    RoleDTO toDto(Role source);
    Role toEntity(RoleDTO source);
}
