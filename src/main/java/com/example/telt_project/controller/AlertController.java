// AlertController.java
package com.example.telt_project.controller;

import com.example.telt_project.model.Alert;
import com.example.telt_project.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")

public class AlertController {

    @Autowired
    private AlertRepository alertRepository;

    @GetMapping
    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    // Handle preflight OPTIONS requests
    @RequestMapping(method = RequestMethod.OPTIONS, value = "/delete-all")
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/delete-all")
    public ResponseEntity<String> deleteAllAlerts() {
        alertRepository.deleteAll();
        return ResponseEntity.ok("All alerts have been deleted.");
    }
}
