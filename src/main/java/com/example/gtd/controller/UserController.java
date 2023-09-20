package com.example.gtd.controller;


import com.example.gtd.dao.entity.Role;
import com.example.gtd.dao.entity.User;
import com.example.gtd.dao.repo.RoleRepo;
import com.example.gtd.dao.repo.UserRepo;
import com.example.gtd.dto.UserDTO;
import com.example.gtd.mapper.UserMapper;
import com.example.gtd.services.RoleService;
import com.example.gtd.services.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@Data
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleService roleService;

    @PostMapping("/register")
    public String register(@RequestBody UserDTO userDTO) {

        System.out.println(userDTO);
        User user = userMapper.toEntity(userDTO);
        System.out.println(user);
        Optional<User> new_user = userService.findUserByUsername(user.getUsername());
        if(new_user.isEmpty()) {
            userService.save(user);
            return "Succesfully registred";
        }
        else {
            return "Already in db";
        }
    }

    @GetMapping()
    public List<User> findAll() {
        return userService.findAll();
    }

}
