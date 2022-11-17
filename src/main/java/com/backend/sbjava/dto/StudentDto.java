package com.backend.sbjava.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class StudentDto {

    @JsonProperty(access = Access.READ_ONLY)
    private Long id;

    private Long docNumber;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private Integer grade;

    private String course;

    private Double score;

    @JsonProperty(access = Access.READ_ONLY)
    private Integer active = 1;
    
}
