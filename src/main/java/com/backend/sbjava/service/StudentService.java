package com.backend.sbjava.service;

import java.util.List;
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
        List<StudentEntity> studentEntityList = studentRepository.findAll();
        if (studentEntityList.isEmpty()) {
            return ResponseHandler.generateApiError(ResponseHandlerConstants.EMPTY_STUDENT_LIST.getMessage(),
                    HttpStatus.NOT_FOUND);
        } else {
            return ResponseHandler.generateResponse(ResponseHandlerConstants.SUCCESS.getMessage(), HttpStatus.OK,
                    studentEntityList
                            .stream()
                            .map(student -> studentConverter.convertEntityToDto(student))
                            .collect(Collectors.toList()));
        }
    }

    public ResponseEntity<Object> findById(Long id) {
        StudentEntity studentEntity = studentRepository.findById(id)
                .orElse(null);
        if (studentEntity == null) {
            return ResponseHandler.generateApiError(ResponseHandlerConstants.NOT_FOUND.getMessage(),
                    HttpStatus.NOT_FOUND);
        } else {
            return ResponseHandler.generateResponse(ResponseHandlerConstants.SUCCESS.getMessage(), HttpStatus.OK,
                    this.studentConverter.convertEntityToDto(studentEntity));
        }
    }

    public ResponseEntity<Object> createNewStudent(StudentDto student) {
        try {
            StudentEntity persistedStudent = studentRepository.findByDocNumber(student.getDocNumber());
            if (persistedStudent != null) {
                return ResponseHandler.generateApiError(ResponseHandlerConstants.FOUND.getMessage(), HttpStatus.CONFLICT);
            } else {
                StudentEntity studentEntity = studentConverter.convertDtotoEntity(student);
                this.studentRepository.save(studentEntity);
                return ResponseHandler.generateResponse(ResponseHandlerConstants.SUCCESS.getMessage(), HttpStatus.OK,
                        this.studentConverter.convertEntityToDto(studentEntity));
            }
        } catch (Exception e) {
            return ResponseHandler.generateApiError(e.getMessage(), HttpStatus.CONFLICT);
        }

    }

    public ResponseEntity<Object> updateStudent(StudentDto studentDto, Long id) {
        StudentEntity persistedStudent = studentRepository.findById(id)
                .orElse(null);
        if (persistedStudent == null) {
            return ResponseHandler.generateApiError(ResponseHandlerConstants.NOT_FOUND.getMessage(),
                    HttpStatus.NOT_FOUND);
        } else {
            studentDto.setId(id);
            StudentEntity studentEntity = studentConverter.convertDtotoEntity(studentDto);
            this.studentRepository.save(studentEntity);
            return ResponseHandler.generateResponse(ResponseHandlerConstants.SUCCESS.getMessage(), HttpStatus.OK,
                    this.studentConverter.convertEntityToDto(studentEntity));
        }
    }

    public ResponseEntity<Object> deleteStudent(Long id) {
        StudentEntity studentEntity = studentRepository.findById(id)
                .orElse(null);
        if (studentEntity == null) {
            return ResponseHandler.generateApiError(ResponseHandlerConstants.NOT_FOUND.getMessage(),
                    HttpStatus.NOT_FOUND);
        } else {
            this.studentRepository.delete(studentEntity);
            StudentDto studentDto = studentConverter.convertEntityToDto(studentEntity);
            return ResponseHandler.generateResponse(ResponseHandlerConstants.SUCCESS.getMessage(), HttpStatus.OK,
                    String.format(ResponseHandlerConstants.DELETED_MESSAGE.getMessage(), studentDto.getFirstName(),
                            studentDto.getLastName()));
        }

    }

}
