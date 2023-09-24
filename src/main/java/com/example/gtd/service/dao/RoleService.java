package com.example.gtd.service.dao;

import com.example.gtd.dao.entity.Role;
import com.example.gtd.dao.repo.RoleRepo;
import lombok.Data;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class RoleService {

    private final RoleRepo roleRepo;

    @Transactional(readOnly = true)
    public List<Role> findAll() {
        return roleRepo.findAll();
    }

    public Optional<Role> findById(Long id) {
        return roleRepo.findById(id);
    }

    public Role save(Role role) {
        return roleRepo.save(role);
    }

    public Optional<Role> findByRolename(String rolename) {
        return roleRepo.findByRolename(rolename);
    }

    public Role update(Role role) {
        return roleRepo.save(role);
    }
}
