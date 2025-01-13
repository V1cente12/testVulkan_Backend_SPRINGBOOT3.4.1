package com.example.testVulkan_Backend_SPRINGBOOT341.controller;

import com.example.testVulkan_Backend_SPRINGBOOT341.service.StadisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/stadistics")
public class StadisticsController {

    @Autowired
    private StadisticsService statisticsService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getStatistics() {
        Map<String, Object> stats = statisticsService.getStatistics();
        return ResponseEntity.ok(stats);
    }
}