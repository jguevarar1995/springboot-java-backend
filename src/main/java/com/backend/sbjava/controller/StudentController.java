package com.backend.sbjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.sbjava.dto.StudentDto;
import com.backend.sbjava.service.StudentService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/v1")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/student")
    public ResponseEntity<Object> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(value = "/student/{id}")
    public ResponseEntity<Object> findStudentById(@PathVariable Long id) {
        return studentService.findById(id);
    }

    @PostMapping(value = "/student")
    public ResponseEntity<Object> createNewStudent(@RequestBody StudentDto studentDto) {
        return studentService.createNewStudent(studentDto);
    }

    @PutMapping(value = "/student/{id}")
    public ResponseEntity<Object> updateStudent(@RequestBody StudentDto studentDto, @PathVariable Long id) {
        return studentService.updateStudent(studentDto, id);
    }

    @DeleteMapping(value = "/student/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

}
