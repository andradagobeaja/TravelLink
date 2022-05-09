package com.example.demo.service;


import com.example.demo.models.Plan;
import com.example.demo.repositories.PlanRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlanService {

    private PlanRepository planRepository;

    public PlanService() {
    }

    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public Plan addPlan(Plan plan) {
        return planRepository.save(plan);
    }

    public Plan updatePlan(Plan updated) {
        Optional<Plan> plan = planRepository.findById(updated.getId());
        if(plan.isPresent()) {
            Plan updatedPlan = plan.get();
            updatedPlan.setCost(updated.getCost());
            updatedPlan.setLocation(updated.getLocation());
            updatedPlan.setNoOfDays(updated.getNoOfDays());
            return planRepository.save(updatedPlan);
        }
        return null;
    }

    public void deletePlanWithId(Integer id) {
        planRepository.deleteById(id);
    }
}
