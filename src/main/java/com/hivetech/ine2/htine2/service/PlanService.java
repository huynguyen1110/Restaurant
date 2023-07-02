package com.hivetech.ine2.htine2.service;

import com.hivetech.ine2.htine2.model.storage.entity.Plan;
import org.springframework.http.ResponseEntity;

public interface PlanService {
    ResponseEntity save(Plan plan);
    ResponseEntity validateTitle(String title);
}
