package com.backend.sbjava.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.backend.sbjava.dto.UserDto;
import com.backend.sbjava.model.UserEntity;

@Component
public class UserConverter {

    public UserDto convertEntityToDto(UserEntity userEntity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userEntity, UserDto.class);
    }

    public UserEntity convertDtotoEntity(UserDto userDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userDto, UserEntity.class);
    }
    
}
