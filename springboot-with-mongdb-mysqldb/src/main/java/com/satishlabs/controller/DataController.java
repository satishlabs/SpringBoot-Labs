package com.satishlabs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.satishlabs.service.DataService;

@RestController
@RequestMapping("/api/data")
public class DataController {
    @Autowired
    private DataService dataService;

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getCombinedData() {
        return ResponseEntity.ok(dataService.getCombinedData());
    }
}
