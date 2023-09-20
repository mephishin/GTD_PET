package com.example.gtd.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThingDTO {

    private Long id;
    private String thingName;
    private String thingKind;
    private Long userId;

}
