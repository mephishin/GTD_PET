package com.example.gtd.dao.repo;

import com.example.gtd.dao.entity.Role;
import com.example.gtd.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Set<User> findByRoles(Role role);
    Optional<User> findByUsername(String name);
}
