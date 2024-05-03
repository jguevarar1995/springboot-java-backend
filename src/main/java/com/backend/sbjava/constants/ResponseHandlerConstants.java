package com.backend.sbjava.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


@Getter
@ToString
@RequiredArgsConstructor

public enum ResponseHandlerConstants {
    DELETED_MESSAGE("El estudiante %s %s ha sido eliminado"),
    EMPTY_STUDENT_LIST("No hay estudiantes registrados"),
    FOUND("Ya existe el usuario"),
    NO_VALID("Email o contraseña no válidos"),
    NOT_FOUND("El estudiante no existe"),
    SUCCESS("success");

    private final String message;

}
