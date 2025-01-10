package com.example.testVulkan_Backend_SPRINGBOOT341;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatabaseTestController {


    @Autowired
    private JdbcTemplate jdbcTemplate;


    @GetMapping("/test-db")
    public String testDatabaseConnection() {
        try {
            String query = "SELECT 1";
            Integer result = jdbcTemplate.queryForObject(query, Integer.class);
            return "conection success: " + result;
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }
}