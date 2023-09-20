package com.example.gtd.mapper;

import com.example.gtd.dao.entity.Thing;
import com.example.gtd.dao.entity.User;
import com.example.gtd.dao.repo.UserRepo;
import com.example.gtd.dto.ThingDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
@Service
public interface ThingMapper {

    ThingDTO toDto(Thing source);
    Thing toEntity(ThingDTO source);

}
