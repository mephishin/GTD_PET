package com.example.gtd.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long id;

    @NotBlank(message = "username can't be null or empty")
    private String username;

    @NotBlank(message = "password can't be null or empty")
    private String password;

    @Size(message = "need at least one role", min = 1)
    private Set<String> roles;
}
