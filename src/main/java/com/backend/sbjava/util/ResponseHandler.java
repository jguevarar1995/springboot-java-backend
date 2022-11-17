package com.backend.sbjava.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

    private ResponseHandler() {
        throw new IllegalStateException("ResponseHandler class");
    }

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj);

        return new ResponseEntity<>(map, status);
    }

    public static ResponseEntity<Object> generateApiError(String exception, HttpStatus status) {
        Map<String, Object> map = new HashMap<>();
        map.put("error", exception);
        map.put("status", status.value());

        return new ResponseEntity<>(map, status);
    }
}
