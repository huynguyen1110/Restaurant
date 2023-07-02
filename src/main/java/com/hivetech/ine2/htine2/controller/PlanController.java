package com.hivetech.ine2.htine2.controller;
import com.hivetech.ine2.htine2.model.storage.entity.Plan;
import com.hivetech.ine2.htine2.service.PlanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api/v1/plan")
public class PlanController {
    @Autowired
    private PlanServiceImpl planService;
    @GetMapping("/create")
    public ModelAndView addPlan() {
        ModelAndView modelAndView = new ModelAndView("private/admin/add_plan");
        return modelAndView;
    }
    @PostMapping(value = "/create")
    public ResponseEntity savePlan(@RequestBody Plan plan ) {
        return  planService.save(plan);
    }
    @GetMapping("/list")
    public ModelAndView getAllPlan() {
        ModelAndView modelAndView = new ModelAndView("private/admin/list_plan");
        return modelAndView;
    }
}
