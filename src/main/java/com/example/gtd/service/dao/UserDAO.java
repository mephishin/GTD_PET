package com.example.gtd.service.dao;

import com.example.gtd.customException.AlreadyInDbException;
import com.example.gtd.customException.NotFoundInDbException;
import com.example.gtd.dao.entity.Role;
import com.example.gtd.dao.entity.User;
import com.example.gtd.dao.repo.RoleRepo;
import com.example.gtd.dao.repo.UserRepo;
import lombok.Data;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Service
@Data
public class UserDAO implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ThingDAO thingDAO;

    @Autowired
    private RoleRepo roleRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUsername(username);
        if(user.isEmpty()) {
            throw new UsernameNotFoundException("User ‘" + username + "’ not found");
        }
        else{
            return user.get();
        }

    }

    public User findUserByUsername(String username) throws NotFoundInDbException {
        Optional<User> user = userRepo.findByUsername(username);
        if(user.isPresent()) {
            return user.get();
        }
        else {
            throw new NotFoundInDbException("Not found a user with this username");
        }
    }

    public User post(User user) throws AlreadyInDbException {
        if(userRepo.findByUsername(user.getUsername()).isEmpty()) {
            return userRepo.save(user);
        }
        else {
            throw new AlreadyInDbException("User with this username already in database");
        }
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User update(User user) throws NotFoundInDbException, AlreadyInDbException {
        if(userRepo.findById(user.getId()).isPresent()) {
            if(userRepo.findByUsername(user.getUsername()).isEmpty()) {
                return userRepo.save(user);
            }
            else {
                throw new AlreadyInDbException("User with this username already in db");
            }
        }
        else {
            throw new NotFoundInDbException("Not found a user by this id");
        }
    }

    public User findById(Long id) throws NotFoundInDbException {
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()) {
            return user.get();
        }
        else {
            throw new NotFoundInDbException("Not found a user by this id");
        }
    }

    public void delete(Long id) throws NotFoundInDbException {
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()) {
            thingDAO.findByUser(user.get()).forEach(
                    thing -> {
                        thing.setUser(null);
                        thingDAO.update(thing);
                    });
            userRepo.deleteById(id);
        }
        else {
            throw new NotFoundInDbException("Not found a user by this id");
        }
    }

    public Set<User> findByRoles(Role roles) {
        return userRepo.findByRoles(roles);
    }

    public User patch(Long id, Map<Object, Object> fields) throws NotFoundInDbException, InvocationTargetException, IllegalAccessException {
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()) {
            for(Map.Entry<Object, Object> entry: fields.entrySet()) {
                if(!entry.getKey().equals("roles")) {
                    BeanUtils.setProperty(user.get(), entry.getKey().toString(), entry.getValue());
                }
                else {
                    ArrayList<String> arr = new ArrayList<>();
                    for(String role: (ArrayList<String>) entry.getValue()) {
                        if(roleRepo.existsByRolename(role)) {
                            arr.add(role);
                        }
                        else {
                            throw new NotFoundInDbException("Not found a role with this rolename");
                        }
                    }
                    BeanUtils.setProperty(user.get(), entry.getKey().toString(), arr);
                }
            }
            return userRepo.save(user.get());
        }
        else {
            throw new NotFoundInDbException("Not found a user by this id");
        }
    }
}
