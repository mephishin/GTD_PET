package com.example.gtd.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ThingDTO {

    private Long id;

    @NotBlank(message = "thingName can't be null or empty")
    private String thingName;

    @NotBlank(message = "thingKind can't be null or empty")
    private String thingKind;

    @Size(message = "thing can have only one author", min = 1, max = 1)
    private List<String> user;

}
