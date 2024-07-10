package com.ellenmateus.ecommerce.service;

import com.ellenmateus.ecommerce.model.ExceptionLog;
import com.ellenmateus.ecommerce.repository.ExceptionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExceptionLogService {

    @Autowired
    private ExceptionLogRepository exceptionLogRepository;

    public List<ExceptionLog> getAllExceptionLogs() {
        return exceptionLogRepository.findAll();
    }

    public Optional<ExceptionLog> getExceptionLogById(Integer id) {
        return exceptionLogRepository.findById(id);
    }

    public ExceptionLog createExceptionLog(ExceptionLog exceptionLog) {
        return exceptionLogRepository.save(exceptionLog);
    }

    public void deleteExceptionLog(Integer id) {
        exceptionLogRepository.deleteById(id);
    }

   
}
