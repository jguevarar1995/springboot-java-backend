package com.backend.sbjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.sbjava.model.StudentEntity;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long>{
    StudentEntity findByDocNumber(Long docNumber);
}
