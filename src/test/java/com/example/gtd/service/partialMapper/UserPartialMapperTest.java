//package com.example.gtd.service.partial_mapper;
//
//import com.example.gtd.dao.entity.Role;
//import com.example.gtd.dao.entity.User;
//import com.example.gtd.service.dao.RoleDAO;
//import com.example.gtd.service.dao.UserDAO;
//import lombok.Data;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//
//@Data
//@ExtendWith(MockitoExtension.class)
//public class UserPartialMapperTest {
//
//    @MockBean
//    private UserDAO userDAO;
//
//    @MockBean
//    private RoleDAO roleDAO;
//
//    @InjectMocks
//    private UserPartialMapper userPartialMapper;
//
//    @Test
//    public void toEntity() {
//        User user = User.builder().id(1L).username("User").password("Password").roles(Set.of(new Role("ROLE_ADMIN"))).build();
//        Mockito.when(userDAO.findById(1L)).thenReturn(Optional.of(user));
//        Mockito.when(roleDAO.findByRolename("ROLE_ADMIN")).thenReturn(Optional.of(
//                new Role("ROLE_ADMIN")));
//
//        HashMap<Object, Object> fields1 = new HashMap<>();
//        fields1.put("username", "User");
//        fields1.put("password", "Password");
//
//        HashMap<Object, Object> fields2 = new HashMap<>();
//        fields2.put("roles", List.of((Object) "ROLE_ADMIN"));
//
//        HashMap<Object, Object> fields3 = new HashMap<>();
//        fields3.put("username", "User");
//        fields3.put("password", "Password");
//        fields3.put("roles", List.of((Object) "ROLE_ADMIN"));
//
//
//
//        Assertions.assertEquals(user, userPartialMapper.toEntity(fields1, 1L));
//        Assertions.assertEquals(user, userPartialMapper.toEntity(fields2, 1L));
//        Assertions.assertEquals(user, userPartialMapper.toEntity(fields3, 1L));
//    }
//}
