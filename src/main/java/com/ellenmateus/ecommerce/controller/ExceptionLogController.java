package com.ellenmateus.ecommerce.controller;

import com.ellenmateus.ecommerce.model.ExceptionLog;
import com.ellenmateus.ecommerce.service.ExceptionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/exceptionlogs")
public class ExceptionLogController {

    @Autowired
    private ExceptionLogService exceptionLogService;

    @GetMapping
    public List<ExceptionLog> getAllExceptionLogs() {
        return exceptionLogService.getAllExceptionLogs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExceptionLog> getExceptionLogById(@PathVariable Long id) {
        Optional<ExceptionLog> exceptionLog = exceptionLogService.getExceptionLogById(id);
        if (exceptionLog.isPresent()) {
            return ResponseEntity.ok(exceptionLog.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ExceptionLog createExceptionLog(@RequestBody ExceptionLog exceptionLog) {
        return exceptionLogService.createExceptionLog(exceptionLog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExceptionLog(@PathVariable Long id) {
        Optional<ExceptionLog> exceptionLog = exceptionLogService.getExceptionLogById(id);
        if (exceptionLog.isPresent()) {
            exceptionLogService.deleteExceptionLog(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
