package com.example.gtd.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThingDTO {

    private Long id;
    private String thingName;
    private String thingKind;
    private String[] authorName;

}
