package com.example.testVulkan_Backend_SPRINGBOOT341.repository;

import com.example.testVulkan_Backend_SPRINGBOOT341.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
