package com.example.gtd.controller;

import com.example.gtd.GtdApplication;
import com.example.gtd.config.SecurityConfig;
import com.example.gtd.config.WebConfig;
import com.example.gtd.dao.entity.Role;
import com.example.gtd.dto.RoleDTO;
import com.example.gtd.service.dao.RoleDAO;
import com.example.gtd.service.mapper.RoleMapper;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;


@Data
@WebMvcTest(RoleController.class)
@ContextConfiguration(classes = {GtdApplication.class, WebConfig.class, SecurityConfig.class})
public class RoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoleDAO roleDAO;

    @MockBean
    private RoleMapper roleMapper;

    @Test
    void roleControllerShouldReturnRoleById() throws Exception {
        Mockito.when(roleMapper.toEntity(
                RoleDTO.builder().rolename("ROLE_ADMIN").build())).thenReturn(new Role("ROLE_ADMIN"));
        Mockito.when(roleMapper.toDto(
                new Role("ROLE_ADMIN"))).thenReturn(RoleDTO.builder().rolename("ROLE_ADMIN").build());
        Mockito.when(roleDAO.findById(1L)).thenReturn(Optional.of(
                new Role("ROLE_ADMIN")));
        this.mockMvc.perform(MockMvcRequestBuilders.get("/role/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rolename").value("ROLE_ADMIN"))
        ;
    }
}
