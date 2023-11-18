package com.example.gtd.service.mapper;

import com.example.gtd.customException.NotFoundInDbException;
import com.example.gtd.dao.entity.Thing;
import com.example.gtd.dao.entity.User;
import com.example.gtd.dto.ThingDTO;
import com.example.gtd.service.dao.ThingDAO;
import com.example.gtd.service.dao.UserDAO;
import lombok.Builder;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
@Service
public abstract class ThingMapper {

    @Autowired
    private UserDAO userService;

    @Mapping(target = "user", ignore = true)
    public abstract ThingDTO toDto(Thing source) throws NotFoundInDbException;
    @Mapping(target = "user", ignore = true)
    public abstract Thing toEntity(ThingDTO source) throws NotFoundInDbException;

    @AfterMapping
    protected void usernameToUser(@MappingTarget Thing.ThingBuilder thing, ThingDTO thingDTO) throws NotFoundInDbException {
        thing.user(Set.of(userService.findUserByUsername(thingDTO.getUser().get(0))));

    }

    @AfterMapping
    protected void userToUsername(@MappingTarget ThingDTO.ThingDTOBuilder thingDTO, Thing thing) {
        thingDTO.user(thing.getUser().stream().map(User::getUsername).collect(Collectors.toList()));
    }
}
