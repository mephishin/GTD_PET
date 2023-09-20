package com.example.gtd.controller;


import com.example.gtd.dao.entity.Thing;
import com.example.gtd.dto.ThingDTO;
import com.example.gtd.mapper.ThingMapper;
import com.example.gtd.services.ThingService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Data
public class ThingController {

    private final ThingService service;
    private final ThingMapper mapper;

    @GetMapping("/thing")
    public List<ThingDTO> findAll() {
        return service.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/thing/{id}")
    public ResponseEntity<ThingDTO> findById(@PathVariable Long id) {
        return ResponseEntity.of(service.findById(id).map(mapper::toDto));
    }

    @PostMapping("/thing")
    public ThingDTO create(@RequestBody ThingDTO thingDto) {
        System.out.println(thingDto);
        Thing persistedThing = service.save(mapper.toEntity(thingDto));
        System.out.println(persistedThing);
        return mapper.toDto(persistedThing);
    }


}
