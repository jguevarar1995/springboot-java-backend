package com.backend.sbjava.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.backend.sbjava.dto.StudentDto;
import com.backend.sbjava.model.StudentEntity;

@Component
public class StudentConverter {

    public StudentDto convertEntityToDto(StudentEntity studentEntity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(studentEntity, StudentDto.class);
    }

    public StudentEntity convertDtotoEntity(StudentDto studentDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(studentDto, StudentEntity.class);
    }

}
