package com.example.gtd.service.dao;

import com.example.gtd.customException.AlreadyInDbException;
import com.example.gtd.customException.NotFoundInDbException;
import com.example.gtd.dao.entity.Role;
import com.example.gtd.dao.entity.User;
import com.example.gtd.dao.repo.RoleRepo;
import lombok.Data;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Data
public class RoleDAO {

    private final RoleRepo roleRepo;

    private final UserDAO userDAO;


    public List<Role> findAll() {
        return roleRepo.findAll();
    }


    public Role findById(Long id) throws NotFoundInDbException {
        Optional<Role> role = roleRepo.findById(id);
        if(role.isPresent()) {
            return role.get();
        }
        else {
            throw new NotFoundInDbException("Not found a role by this id");
        }
    }

    public Role save(Role role) throws AlreadyInDbException {
        if(roleRepo.findByRolename(role.getRolename()).isEmpty()) {
            return roleRepo.save(role);
        }
        else {
            throw new AlreadyInDbException("Role with this rolename already in database");
        }
    }

    public Role findByRolename(String rolename) throws NotFoundInDbException {
        Optional<Role> role = roleRepo.findByRolename(rolename);
        if(role.isPresent()) {
            return role.get();
        }
        else {
            throw new NotFoundInDbException("Not found a role by this id");
        }
    }

    public Role patch(Long id, Map<Object, Object> fields) throws NotFoundInDbException{
        Optional<Role> role = roleRepo.findById(id);
        if(role.isPresent()) {
            fields.forEach((key, value) -> {
                try {
                    BeanUtils.setProperty(role.get(), key.toString(), value);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            });
            return roleRepo.save(role.get());
        }
        else {
            throw new NotFoundInDbException("Not found a role by this id");
        }
    }


    public Role update(Role role) throws NotFoundInDbException, AlreadyInDbException {
        if(roleRepo.findById(role.getId()).isPresent()) {
            if(roleRepo.findByRolename(role.getRolename()).isEmpty()) {
                return roleRepo.save(role);
            }
            else {
                throw new AlreadyInDbException("Role with this rolename already in database");
            }
        }
        else {
            throw new NotFoundInDbException("Not found a role by this id");
        }
    }

    public Set<User> delete(Long id) throws NotFoundInDbException {
        Set<User> updated_users = new HashSet<>();
        Optional<Role> role = roleRepo.findById(id);
        if(role.isPresent()) {
            userDAO.findByRoles(role.get()).forEach(
                    user -> {
                        Set<Role> filtered_roles = user.getRoles()
                                .stream().filter(r -> !r.equals(role.get())).collect(Collectors.toSet());
                        user.setRoles(filtered_roles);
                        updated_users.add(user);
                        try {
                            userDAO.update(user);
                        } catch (NotFoundInDbException | AlreadyInDbException e) {
                            throw new RuntimeException(e);
                        }
                    });
            roleRepo.deleteById(id);
            return updated_users;
        }
        else {
            throw new NotFoundInDbException("Not found a role by this id");
        }

    }
}
