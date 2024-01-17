package com.example.gtd.controller;

import com.example.gtd.customException.AlreadyInDbException;
import com.example.gtd.customException.NotFoundInDbException;
import com.example.gtd.dao.entity.Role;
import com.example.gtd.dto.RoleDTO;
import com.example.gtd.dto.UserDTO;
import com.example.gtd.service.mapper.RoleMapper;
import com.example.gtd.service.dao.RoleDAO;
import com.example.gtd.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleDAO roleService;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserMapper userMapper;

    @PostMapping()
    public ResponseEntity<RoleDTO> create(@RequestBody RoleDTO roleDTO) throws AlreadyInDbException {
        return new ResponseEntity<>(
                roleMapper.toDto(roleService.save(roleMapper.toEntity(roleDTO))), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getById(@PathVariable Long id) throws NotFoundInDbException {
        return new ResponseEntity<>(roleMapper.toDto(roleService.findById(id)), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<RoleDTO>> get() {
        return new ResponseEntity<>(roleService
                .findAll().stream().map(roleMapper::toDto).collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> put(@RequestBody RoleDTO roleDTO, @PathVariable Long id) throws NotFoundInDbException, AlreadyInDbException {
        Role role = roleMapper.toEntity(roleDTO);
        role.setId(id);
        return new ResponseEntity<>(roleMapper.toDto(roleService.update(role)), HttpStatus.OK);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<RoleDTO> patch(@RequestBody Map<Object, Object> fields,
                                         @PathVariable Long id) throws NotFoundInDbException {
        return new ResponseEntity<>(
                roleMapper.toDto(roleService.patch(id, fields)),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Set<UserDTO>> delete(@PathVariable Long id) throws NotFoundInDbException {
        return new ResponseEntity<>(
                roleService.delete(id).stream().map(user -> userMapper.toDto(user))
                        .collect(Collectors.toSet()), HttpStatus.OK);
    }
}
