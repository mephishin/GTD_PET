package com.example.gtd.controller;


import com.example.gtd.customException.AlreadyInDbException;
import com.example.gtd.customException.NotFoundInDbException;
import com.example.gtd.dao.entity.User;
import com.example.gtd.dto.UserDTO;
import com.example.gtd.service.dao.RoleDAO;
import com.example.gtd.service.mapper.UserMapper;
import com.example.gtd.service.dao.UserDAO;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@Data
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleDAO roleDAO;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) throws NotFoundInDbException {
        return new ResponseEntity<>(userMapper.toDto(userDAO.findById(id)), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> findAll() {
        return new ResponseEntity<>(userDAO.findAll().stream().map(
                user -> userMapper.toDto(user)).collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO, @PathVariable Long id)
            throws NotFoundInDbException, AlreadyInDbException {
        userDTO.setId(id);
        User user = userMapper.toEntity(userDTO);
        return new ResponseEntity<>(userMapper.toDto(userDAO.update(user)), HttpStatus.OK);

    }

    @PostMapping()
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO userDTO)
            throws NotFoundInDbException, AlreadyInDbException {
        User user = userMapper.toEntity(userDTO);
        return new ResponseEntity<>(userMapper.toDto(userDAO.post(user)), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> patch(
            @PathVariable Long id, @RequestBody Map<Object, Object> fields)
            throws NotFoundInDbException, InvocationTargetException, IllegalAccessException {
        return new ResponseEntity<>(userMapper.toDto(userDAO.patch(id, fields)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws NotFoundInDbException {
//        return new ResponseEntity<>(userMapper.toDto(userDAO.delete(id)), HttpStatus.OK);
        userDAO.delete(id);
        return new ResponseEntity<>("Succesfully deleted", HttpStatus.OK);
    }
}
