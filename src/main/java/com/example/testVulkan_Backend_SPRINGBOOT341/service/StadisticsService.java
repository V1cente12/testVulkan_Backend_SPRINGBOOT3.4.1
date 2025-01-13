package com.example.testVulkan_Backend_SPRINGBOOT341.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.example.testVulkan_Backend_SPRINGBOOT341.repository.CourseRepository;
import com.example.testVulkan_Backend_SPRINGBOOT341.repository.StudentRepository;
import com.example.testVulkan_Backend_SPRINGBOOT341.model.Course;
import com.example.testVulkan_Backend_SPRINGBOOT341.model.Student;

@Service
public class StadisticsService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    public Map<String, Object> getStatistics() {
        Map<String, Object> stats = new HashMap<>();

        List<Course> courses = courseRepository.findAll();
        List<Student> students = studentRepository.findAll();

        long totalCourses = courses.size();
        long totalStudents = students.size();
        double averageStudentsPerCourse = (double) totalStudents / totalCourses;
        double averageCapacityPerCourse = courses.stream()
                .mapToInt(Course::getMaxCapacity)
                .average()
                .orElse(0.0);

        Map<String, Long> studentsByCourse = new HashMap<>();
        Map<String, Long> studentsByGender = new HashMap<>();
        List<Map<String, Object>> courseCapacityPercentage = new ArrayList<>();


        for (Student student : students) {

            String courseName = student.getCourse().getName();
            studentsByCourse.put(courseName, studentsByCourse.getOrDefault(courseName, 0L) + 1);


            String gender = student.getGender();
            studentsByGender.put(gender, studentsByGender.getOrDefault(gender, 0L) + 1);
        }


        for (Course course : courses) {
            long studentCountInCourse = studentsByCourse.getOrDefault(course.getName(), 0L);
            double capacityPercentage = (double) studentCountInCourse / course.getMaxCapacity() * 100;
            Map<String, Object> courseData = new HashMap<>();
            courseData.put("courseName", course.getName());
            courseData.put("capacityPercentage", capacityPercentage);
            courseCapacityPercentage.add(courseData);
        }

        stats.put("totalCourses", totalCourses);
        stats.put("totalStudents", totalStudents);
        stats.put("averageStudentsPerCourse", averageStudentsPerCourse);
        stats.put("averageCapacityPerCourse", averageCapacityPerCourse);
        stats.put("studentsByCourse", studentsByCourse);
        stats.put("studentsByGender", studentsByGender);
        stats.put("courseCapacityPercentage", courseCapacityPercentage);

        return stats;
    }
}

