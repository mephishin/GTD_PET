package com.example.gtd.dao.repo;

import com.example.gtd.dao.entity.Thing;
import com.example.gtd.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ThingRepo extends JpaRepository<Thing, Long> {
    Set<Thing> findByUser(User user);
}
