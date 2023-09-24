package com.example.gtd.controller;


import com.example.gtd.dao.entity.User;
import com.example.gtd.dto.RoleDTO;
import com.example.gtd.dto.UserDTO;
import com.example.gtd.service.mapper.UserMapper;
import com.example.gtd.service.dao.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Data
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping()
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping("/update")
    public String update(@RequestBody UserDTO userDTO) {
        if(userService.findById(userDTO.getId()).isPresent()) {
            userService.update(userMapper.toEntity(userDTO));
            return "updated";
        }
        else {
            return "role not found";
        }

    }

    @PostMapping("/registration")
    public String registrate(@RequestBody UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        if(userService.findUserByUsername(user.getUsername()).isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.save(user);
            return "registrated";
        }
        else {
            return "Already in db";
        }
    }
}
