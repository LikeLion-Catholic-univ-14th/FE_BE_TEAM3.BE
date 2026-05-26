package com.example.excuse.controller;

import com.example.excuse.dto.ExcuseRequest;
import com.example.excuse.dto.ExcuseResponse;
import com.example.excuse.service.ExcuseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/excuses")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ExcuseController {

    private final ExcuseService excuseService;

    @PostMapping
    public ResponseEntity<ExcuseResponse> createExcuse(@RequestBody ExcuseRequest excuseRequest) {
        return ResponseEntity.ok(excuseService.createExcuse(excuseRequest));
    }

    @GetMapping
    public ResponseEntity<List<ExcuseResponse>> getAllExcuses() {
        return ResponseEntity.ok(excuseService.getAllExcuses());
    }

    @GetMapping("/hall-of-fame")
    public ResponseEntity<List<ExcuseResponse>> getHallOfFame() {
        return ResponseEntity.ok(excuseService.getHallOfFame());
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<Void> likeExcuse(@PathVariable Long id) {
        excuseService.likeExcuse(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExcuseResponse> updateExcuse(@PathVariable Long id, @RequestBody ExcuseRequest requestDto) {
        return ResponseEntity.ok(excuseService.updateExcuse(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExcuse(@PathVariable Long id, @RequestBody Map<String, String> passwordMap) {
        excuseService.deleteExcuse(id, passwordMap.get("password"));
        return ResponseEntity.ok().build();
    }
}
