package com.example.gtd.service.mapper;

import com.example.gtd.dao.entity.Thing;
import com.example.gtd.dto.ThingDTO;
import com.example.gtd.service.dao.ThingService;
import com.example.gtd.service.dao.UserService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Mapper(componentModel = "spring")
@Service
public abstract class ThingMapper {

    @Autowired
    private ThingService thingService;
    @Autowired
    private UserService userService;

    public abstract ThingDTO toDto(Thing source);
    public abstract Thing toEntity(ThingDTO source);

    @AfterMapping
    protected void usernameToUser(@MappingTarget Thing thing, ThingDTO thingDTO) {
        if(userService.findUserByUsername(thingDTO.getAuthorName()[0]).isPresent()){
            thing.setUser(Set.of(userService.findUserByUsername(thingDTO.getAuthorName()[0]).get()));
        }
        else {
            System.out.println("User not found");
        }
    }


}
