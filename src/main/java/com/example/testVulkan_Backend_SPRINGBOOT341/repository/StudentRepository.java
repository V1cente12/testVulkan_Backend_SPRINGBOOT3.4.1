package com.example.testVulkan_Backend_SPRINGBOOT341.repository;

import com.example.testVulkan_Backend_SPRINGBOOT341.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    long countByCourseId(Long courseId);
    List<Student> findByCourseId(Long courseId);
}