package com.example.testVulkan_Backend_SPRINGBOOT341.service;

import com.example.testVulkan_Backend_SPRINGBOOT341.model.Course;
import com.example.testVulkan_Backend_SPRINGBOOT341.model.Student;
import com.example.testVulkan_Backend_SPRINGBOOT341.repository.StudentRepository;
import com.example.testVulkan_Backend_SPRINGBOOT341.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {


    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student saveStudent(Student student) {
        Course course = courseRepository.findById(student.getCourse().getId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        long enrolledCount = studentRepository.countByCourseId(course.getId());
        if (enrolledCount >= course.getMaxCapacity()) {
            throw new RuntimeException("The course has reached its maximum capacity");
        }

        student.setCourse(course);

        return studentRepository.save(student);
    }


    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Student updateStudent(Long id, Student student) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Student not found")
        );

        Course course = courseRepository.findById(student.getCourse().getId())
                .orElseThrow(() -> new RuntimeException("Course not found"));


        long enrolledCount = studentRepository.countByCourseId(course.getId());
        if (enrolledCount >= course.getMaxCapacity()) {
            throw new RuntimeException("The course has reached its maximum capacity");
        }

        existingStudent.setName(student.getName());
        existingStudent.setAge(student.getAge());
        existingStudent.setGender(student.getGender());
        existingStudent.setCourse(course);

        return studentRepository.save(existingStudent);
    }
}
