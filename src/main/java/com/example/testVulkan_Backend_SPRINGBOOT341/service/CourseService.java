package com.example.testVulkan_Backend_SPRINGBOOT341.service;

import com.example.testVulkan_Backend_SPRINGBOOT341.model.Course;
import com.example.testVulkan_Backend_SPRINGBOOT341.model.Student;
import com.example.testVulkan_Backend_SPRINGBOOT341.repository.CourseRepository;
import com.example.testVulkan_Backend_SPRINGBOOT341.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;


    @Autowired
    private StudentRepository studentRepository;

    public List<Course> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        for (Course course : courses) {
            long totalStudents = studentRepository.countByCourseId(course.getId());
            course.setTotalStudents(totalStudents);
        }
        return courses;
    }


    public Course getCourse(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course != null) {
            long totalStudents = studentRepository.countByCourseId(course.getId());
            course.setTotalStudents(totalStudents);
        }
        return course;
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public Course updateCourse(Long id, Course updatedCourse) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("course not found id: " + id));

        existingCourse.setName(updatedCourse.getName());
        existingCourse.setMaxCapacity(updatedCourse.getMaxCapacity());

        return courseRepository.save(existingCourse);
    }

    public List<Student> getStudentsByCourse(Long courseId) {
        return studentRepository.findByCourseId(courseId);  // No es necesario mapear manualmente
    }
}