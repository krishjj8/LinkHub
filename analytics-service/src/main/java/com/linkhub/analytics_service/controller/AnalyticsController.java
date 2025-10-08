package com.linkhub.analytics_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/analytics")
public class AnalyticsController {
    @PostMapping("/click")
    public ResponseEntity<Void> recordClickEvent(){
        System.out.println("Analytics service received a click");
        return ResponseEntity.ok().build();
    }
}
