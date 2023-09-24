package com.example.gtd.controller;

import com.example.gtd.dao.entity.Role;
import com.example.gtd.dto.RoleDTO;
import com.example.gtd.service.mapper.RoleMapper;
import com.example.gtd.service.dao.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMapper roleMapper;

    @PostMapping("/save")
    public String save(@RequestBody RoleDTO roleDTO) {
        if(roleService.findByRolename(roleDTO.getRolename()).isEmpty()) {
            roleService.save(roleMapper.toEntity(roleDTO));
            return "saved";
        }
        else {
            return "already in db";
        }
    }

    @PostMapping("/update")
    public String update(@RequestBody RoleDTO roleDTO) {
        if(roleService.findById(roleDTO.getId()).isPresent()) {
            roleService.update(roleMapper.toEntity(roleDTO));
            return "updated";
        }
        else {
            return "role not found";
        }

    }
}
