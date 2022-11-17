package com.backend.sbjava.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "students")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, insertable = false, updatable= false)
    private Long id;

    @Column(name = "doc_number", unique = true, nullable = false)
    private Long docNumber;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "phone", unique = true, nullable = false)
    private String phone;

    @Column(name = "grade", nullable = false)
    private Integer grade;

    @Column(name = "course", nullable = false)
    private String course;

    @Column(name = "score", nullable = false)
    private Double score;

    @Column(name = "active", columnDefinition="TINYINT(1) DEFAULT 1")
    private Integer active;

}