package com.example.gtd.dao.repo;

import com.example.gtd.dao.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByRolename(String rolename);
    Boolean existsByRolename(String rolename);
}
