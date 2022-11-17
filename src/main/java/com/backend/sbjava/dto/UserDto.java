package com.backend.sbjava.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDto {
    
    private Long id;
    
    private String email;

    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    private Integer roleId;
}
