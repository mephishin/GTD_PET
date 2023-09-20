package com.example.gtd.dao.repo;

import com.example.gtd.dao.entity.Thing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThingRepo extends JpaRepository<Thing, Long> {
}
