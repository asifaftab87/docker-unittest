package com.interview.template.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @Min(5)
    private Long id;

    @NotNull
    private String username;
    private String email;
    private String password;
}
