package com.youyu.cotenant.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonitorEndpoint {
	
    @GetMapping("/health")
    public ResponseEntity<?> health() {
        return ResponseEntity.ok("{\"msg\":\"I'm alive\"}");
    }
}
