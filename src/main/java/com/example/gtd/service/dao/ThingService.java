package com.example.gtd.service.dao;

import com.example.gtd.dao.entity.Thing;
import com.example.gtd.dao.repo.ThingRepo;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class ThingService {

    private final ThingRepo repo;

    @Transactional(readOnly = true)
    public List<Thing> findAll() {
        return repo.findAll();
    }

    public Optional<Thing> findById(Long id) {
        return repo.findById(id);
    }

    public Thing save(Thing thing) {
        return repo.save(thing);
    }

    public Thing update(Thing thing) {
        return repo.save(thing);
    }
}
