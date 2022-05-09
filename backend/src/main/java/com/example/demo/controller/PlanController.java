package com.example.demo.controller;

import com.example.demo.models.Plan;
import com.example.demo.repositories.PlanRepository;
import com.example.demo.service.PlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class PlanController {

    private PlanService planService;
    private PlanRepository planRepository;

    public PlanController() {
    }

    public PlanController(PlanService planService, PlanRepository planRepository) {
        this.planService = planService;
        this.planRepository = planRepository;
    }

    @PostMapping("/plan/add")
    public ResponseEntity createPlan(@RequestParam(value = "location") String location,
                                         @RequestParam(value = "cost") Double cost,
                                         @RequestParam(value = "no_of_days") Integer noOfDays) {

        Plan plan = new Plan();
        plan.setLocation(location);
        plan.setCost(cost);
        plan.setNoOfDays(noOfDays);

        return ResponseEntity.ok(planService.addPlan(plan));
    }

    @PatchMapping("/plan/{id}")
    ResponseEntity updatePlan(@PathVariable Integer id,
                               @RequestParam(value = "location", required = false) String location,
                               @RequestParam(value = "cost", required = false) Double cost,
                               @RequestParam(value = "no_of_days", required = true) Integer noOfDays
    ) {

        Optional<Plan> plan = planRepository.findById(id);
        if(plan.isPresent()) {
            Plan updated =  plan.get();
            if(location != null)
                updated.setLocation(location);
            if(cost != null)
                updated.setCost(cost);
            if(noOfDays != null) {
                updated.setNoOfDays(noOfDays);
            }

            return ResponseEntity.ok(planService.updatePlan(updated));
        }

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Plan could not be updated");
    }


    @DeleteMapping("/plan/{id}")
    ResponseEntity deleteUser(@PathVariable Integer id) {
        planService.deletePlanWithId(id);
        return ResponseEntity.ok("Plan successfully deleted.");
    }
}
