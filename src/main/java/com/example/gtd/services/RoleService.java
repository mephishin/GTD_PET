package com.example.gtd.services;

import com.example.gtd.dao.entity.Role;
import com.example.gtd.dao.entity.Thing;
import com.example.gtd.dao.repo.RoleRepo;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class RoleService {

    private final RoleRepo repo;

    @Transactional(readOnly = true)
    public List<Role> findAll() {
        return repo.findAll();
    }

    public Optional<Role> findById(Long id) {
        return repo.findById(id);
    }

    public Role save(Role role) {
        return repo.save(role);
    }
}
