package com.ellenmateus.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ellenmateus.ecommerce.model.ExceptionLog;
import com.ellenmateus.ecommerce.service.ExceptionLogService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/exceptionlogs")
@Tag(name = "ExceptionLogs", description = "Endpoints for managing application exception logs")
public class ExceptionLogController {

    @Autowired
    private ExceptionLogService exceptionLogService;
    
    
    @GetMapping
    @Operation(summary = "Get all exception logs")
    public List<ExceptionLog> getAllExceptionLogs() {
        return exceptionLogService.getAllExceptionLogs();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an exception log by ID")
    public ResponseEntity<ExceptionLog> getExceptionLogById(@PathVariable Integer id) {
        Optional<ExceptionLog> exceptionLog = exceptionLogService.getExceptionLogById(id);
        if (exceptionLog.isPresent()) {
            return ResponseEntity.ok(exceptionLog.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Create a new exception log")
    public ExceptionLog createExceptionLog(@RequestBody ExceptionLog exceptionLog) {
        return exceptionLogService.createExceptionLog(exceptionLog);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an exception log by ID")
    public ResponseEntity<Void> deleteExceptionLog(@PathVariable Integer id) {
        Optional<ExceptionLog> exceptionLog = exceptionLogService.getExceptionLogById(id);
        if (exceptionLog.isPresent()) {
            exceptionLogService.deleteExceptionLog(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
