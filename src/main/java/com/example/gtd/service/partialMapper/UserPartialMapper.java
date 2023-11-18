//package com.example.gtd.service.partialMapper;
//
//import com.example.gtd.customException.NotFoundInDbException;
//import com.example.gtd.dao.entity.User;
//import com.example.gtd.service.dao.RoleDAO;
//import com.example.gtd.service.dao.UserDAO;
//import org.mapstruct.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.ReflectionUtils;
//
//import java.lang.reflect.Field;
//import java.util.*;
//import java.util.stream.Collectors;
//
//
//@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
//@Service
//public abstract class UserPartialMapper {
//
//    @Autowired
//    public UserDAO userDAO;
//    @Autowired
//    public RoleDAO roleDAO;
//
//    @Mapping(target = "id", ignore = true)
//    public abstract User toEntity(Map<Object, Object> source, Long id);
//
//    @AfterMapping
//    public void MapToUserDto(@MappingTarget User user, Map<Object, Object> fields, Long id) {
//        user.setId(id);
//        user.setUsername(userDAO.findById(id).get().getUsername());
//        user.setPassword(userDAO.findById(id).get().getPassword());
//        user.setRoles(userDAO.findById(id).get().getRoles());
//        System.out.println(fields);
//        fields.forEach((key, value) -> {
//            Field field = ReflectionUtils.findField(User.class, (String) key);
//            ReflectionUtils.makeAccessible(field);
//            if(!field.getName().equals("roles")) {
//                ReflectionUtils.setField(field, user, value);
//            }
//            else {
//                List<String> value1 = (ArrayList<String>) value;
//                for(String value11: value1) {
//                    try {
//                        roleDAO.findByRolename(value11);
//                    } catch (NotFoundInDbException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//                ReflectionUtils.setField(field, user, value1.stream().map(roleDAO::findByRolename)
//                        .filter(Objects::nonNull).collect(Collectors.toSet()));
//            }
//        });
//    }
//
//}
