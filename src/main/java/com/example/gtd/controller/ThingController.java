package com.example.gtd.controller;


import com.example.gtd.dao.entity.Thing;
import com.example.gtd.customException.NotFoundInDbException;
import com.example.gtd.dto.ThingDTO;
import com.example.gtd.service.mapper.ThingMapper;
import com.example.gtd.service.dao.ThingDAO;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Data
@RequestMapping("/thing")
public class ThingController {

    private final ThingDAO thingService;
    private final ThingMapper thingMapper;

    @GetMapping()
    public ResponseEntity<List<ThingDTO>> get() {
        return new ResponseEntity<>(thingService.findAll().stream().
            map(thing -> {
                try {
                    return thingMapper.toDto(thing);
                } catch (NotFoundInDbException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList()),
            HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThingDTO> getById(@PathVariable Long id) throws NotFoundInDbException {
        if(thingService.findById(id).isPresent()) {
            return new ResponseEntity<>(
                    thingMapper.toDto(thingService.findById(id).orElseThrow()),
                    HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping()
    public ResponseEntity<ThingDTO> post(@Valid @RequestBody ThingDTO thingDto) throws NotFoundInDbException {
        Thing persistedThing = thingService.save(thingMapper.toEntity(thingDto));
        return new ResponseEntity<>(thingMapper.toDto(persistedThing),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ThingDTO> put(@RequestBody ThingDTO thingDTO, @PathVariable Long id) throws NotFoundInDbException {
        thingDTO.setId(id);
        if(thingService.findById(thingDTO.getId()).isPresent()) {
            return new ResponseEntity<>(thingMapper.
                    toDto(thingService.update(thingMapper.toEntity(thingDTO))),
                    HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PatchMapping("/{id}")
    public ResponseEntity<ThingDTO> patch(@PathVariable Long id, @RequestBody Map<Object, Object> fields) throws NotFoundInDbException {
        System.out.println(fields);
        if(thingService.findById(id).isPresent()) {
            return new ResponseEntity<>(thingMapper.toDto(thingService.patch(id, fields)), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if(thingService.findById(id).isPresent()) {
            thingService.delete(id);
            return new ResponseEntity<>("successfuly deleted", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("not found in db", HttpStatus.NOT_FOUND);
        }
    }
}


