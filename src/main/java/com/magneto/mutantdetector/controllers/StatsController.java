package com.magneto.mutantdetector.controllers;

import com.magneto.mutantdetector.dto.StatsDto;
import com.magneto.mutantdetector.services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class StatsController {

    @Autowired
    private StatsService statsService;

    @GetMapping
    public ResponseEntity<StatsDto> getStats() {
        StatsDto stats = statsService.getStats();
        return ResponseEntity.ok(stats);
    }
}
