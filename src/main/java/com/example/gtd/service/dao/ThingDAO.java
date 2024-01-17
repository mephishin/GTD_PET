package com.example.gtd.service.dao;

import com.example.gtd.dao.entity.Thing;
import com.example.gtd.dao.entity.User;
import com.example.gtd.dao.repo.ThingRepo;
import lombok.Data;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@Data
public class ThingDAO {

    private final ThingRepo thingRepo;

    @Transactional(readOnly = true)
    public List<Thing> findAll() {
        return thingRepo.findAll();
    }

    public Optional<Thing> findById(Long id) {
        return thingRepo.findById(id);
    }

    public Thing save(Thing thing) {
        return thingRepo.save(thing);
    }

    public Thing update(Thing thing) {
        return thingRepo.save(thing);
    }

    public Thing patch(Long id, Map<Object, Object> fields) {
        Optional<Thing> thing = thingRepo.findById(id);
        thing.ifPresent(thing1 -> fields.forEach((key, value) -> {
            try {
                BeanUtils.setProperty(thing1, key.toString(), value);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }));
        return thingRepo.save(thing.get());
    }

    public Set<Thing> findByUser(User user){
        return thingRepo.findByUser(user);
    }

    public void delete(Long id) {
        thingRepo.delete(thingRepo.findById(id).get());
    }
}
