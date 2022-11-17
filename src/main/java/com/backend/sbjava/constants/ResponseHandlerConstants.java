package com.backend.sbjava.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;



@Getter
@ToString
@RequiredArgsConstructor

public enum ResponseHandlerConstants {

    DELETED_MESSAGE("El estudiante %s %s ha sido eliminado"),
    FOUND("Ya existe el usuario"),
    NOT_FOUND("El estudiante no existe"),
    SUCCESS("success");

    private final String message;
    
}
