package com.stephull.projects.koreanbuildingapp.controller;

import java.util.AbstractMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="http://127.0.0.1:3000")
@RestController
@RequestMapping("/api/ping")
public class PingController {
    
    public PingController() {}

    @GetMapping(value="/")
    public ResponseEntity<Map.Entry<String, String>> getPingMessage() {
        try {
            String key = "message",
                value = "If you're reading this, it means the Korean Building Blocks app API is running and alive.";
            Map.Entry<String, String> result = new AbstractMap.SimpleEntry<String, String>(key, value);
            return new ResponseEntity<Map.Entry<String, String>>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
