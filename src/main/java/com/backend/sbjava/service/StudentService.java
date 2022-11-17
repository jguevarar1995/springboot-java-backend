package com.backend.sbjava.service;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.sbjava.constants.ResponseHandlerConstants;
import com.backend.sbjava.converter.StudentConverter;
import com.backend.sbjava.dto.StudentDto;
import com.backend.sbjava.model.StudentEntity;
import com.backend.sbjava.repository.StudentRepository;
import com.backend.sbjava.util.ResponseHandler;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentConverter studentConverter;

    public ResponseEntity<Object> getAllStudents() {
        return ResponseHandler.generateResponse(ResponseHandlerConstants.SUCCESS.toString(), HttpStatus.OK,
                studentRepository.findAll()
                        .stream()
                        .map(student -> studentConverter.convertEntityToDto(student))
                        .collect(Collectors.toList()));
    }

    public ResponseEntity<Object> findById(Long id) {
        StudentEntity studentEntity = studentRepository.findById(id)
                .orElse(null);
        if (studentEntity == null) {
            return ResponseHandler.generateApiError(ResponseHandlerConstants.NOT_FOUND.toString(),
                    HttpStatus.NOT_FOUND);
        } else {
            return ResponseHandler.generateResponse(ResponseHandlerConstants.SUCCESS.toString(), HttpStatus.OK,
                    this.studentConverter.convertEntityToDto(studentEntity));
        }
    }

    public ResponseEntity<Object> createNewStudent(StudentDto student) {
        StudentEntity persistedStudent = studentRepository.findByDocNumber(student.getDocNumber());
        if (persistedStudent != null) {
            return ResponseHandler.generateApiError(ResponseHandlerConstants.FOUND.toString(), HttpStatus.CONFLICT);
        } else {
            StudentEntity studentEntity = studentConverter.convertDtotoEntity(student);
            this.studentRepository.save(studentEntity);
            return ResponseHandler.generateResponse(ResponseHandlerConstants.SUCCESS.toString(), HttpStatus.OK,
                    this.studentConverter.convertEntityToDto(studentEntity));
        }

    }

    public ResponseEntity<Object> updateStudent(StudentDto studentDto, Long id) {
        StudentEntity persistedStudent = studentRepository.findById(id)
                .orElse(null);
        if (persistedStudent == null) {
            return ResponseHandler.generateApiError(ResponseHandlerConstants.NOT_FOUND.toString(), HttpStatus.NOT_FOUND);
        } else {
            studentDto.setId(id);
            StudentEntity studentEntity = studentConverter.convertDtotoEntity(studentDto);
            this.studentRepository.save(studentEntity);
            return ResponseHandler.generateResponse(ResponseHandlerConstants.SUCCESS.toString(), HttpStatus.OK,
                    this.studentConverter.convertEntityToDto(studentEntity));
        }
    }

    public ResponseEntity<Object> deleteStudent(Long id) {
        StudentEntity studentEntity = studentRepository.findById(id)
                .orElse(null);
        if (studentEntity == null) {
            return ResponseHandler.generateApiError(ResponseHandlerConstants.NOT_FOUND.toString(), HttpStatus.NOT_FOUND);
        } else {
            this.studentRepository.delete(studentEntity);
            StudentDto studentDto = studentConverter.convertEntityToDto(studentEntity);
            return ResponseHandler.generateResponse(ResponseHandlerConstants.SUCCESS.toString(), HttpStatus.OK,
                    String.format(ResponseHandlerConstants.DELETED_MESSAGE.toString(), studentDto.getFirstName(),
                            studentDto.getLastName()));
        }

    }

}
