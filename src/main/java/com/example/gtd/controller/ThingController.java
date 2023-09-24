package com.example.gtd.controller;


import com.example.gtd.dao.entity.Thing;
import com.example.gtd.dto.ThingDTO;
import com.example.gtd.dto.UserDTO;
import com.example.gtd.service.mapper.ThingMapper;
import com.example.gtd.service.dao.ThingService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Data
public class ThingController {

    private final ThingService thingService;
    private final ThingMapper thingMapper;

    @GetMapping("/thing")
    public List<ThingDTO> findAll() {
        return thingService.findAll().stream().map(thingMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/thing/{id}")
    public ResponseEntity<ThingDTO> findById(@PathVariable Long id) {
        return ResponseEntity.of(thingService.findById(id).map(thingMapper::toDto));
    }

    @PostMapping("/thing")
    public ThingDTO create(@RequestBody ThingDTO thingDto) {
        System.out.println(thingDto);
        Thing persistedThing = thingService.save(thingMapper.toEntity(thingDto));
        System.out.println(persistedThing);
        return thingMapper.toDto(persistedThing);
    }

    @PostMapping("/update")
    public String update(@RequestBody ThingDTO thingDTO) {
        if(thingService.findById(thingDTO.getId()).isPresent()) {
            thingService.update(thingMapper.toEntity(thingDTO));
            return "updated";
        }
        else {
            return "role not found";
        }

    }
}
