package com.backend.sbjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.sbjava.constants.ResponseHandlerConstants;
import com.backend.sbjava.converter.UserConverter;
import com.backend.sbjava.dto.UserDto;
import com.backend.sbjava.model.UserEntity;
import com.backend.sbjava.repository.UserRepository;
import com.backend.sbjava.util.ResponseHandler;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    public ResponseEntity<Object> doLogin(UserDto userDto) {
        UserEntity userEntity = userRepository.findByEmail(userDto.getEmail());
        if (userEntity != null) {
            if (userDto.getPassword().equals(userEntity.getPassword())) {
                return ResponseHandler.generateResponse(ResponseHandlerConstants.SUCCESS.getMessage(), HttpStatus.OK,
                        this.userConverter.convertEntityToDto(userEntity));
            } else {
                return ResponseHandler.generateApiError(ResponseHandlerConstants.NO_VALID.getMessage(), HttpStatus.BAD_REQUEST);
            }
        } else {
            return ResponseHandler.generateApiError(ResponseHandlerConstants.NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
