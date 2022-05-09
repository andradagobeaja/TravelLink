package com.example.demo.controller;


import com.example.demo.models.Business;
import com.example.demo.models.Entrepreneur;
import com.example.demo.models.Plan;
import com.example.demo.models.Traveler;
import com.example.demo.repositories.TravelerRepository;
import com.example.demo.service.TravelerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@Controller
public class TravelerController {
    private TravelerService travelerService;
    private TravelerRepository travelerRepository;

    public TravelerController() {
    }

    public TravelerController(TravelerService travelerService) {
        this.travelerService = travelerService;
    }

    @PostMapping("/traveler/add")
    public ResponseEntity createTraveler(@RequestParam(value = "plans") Set<Plan> plans) {

        Traveler traveler = new Traveler();
        traveler.setPlans(plans);

        return ResponseEntity.ok(travelerService.addTraveler(traveler));
    }

    @PatchMapping("/traveler/{id}")
    ResponseEntity updateTraveler(@PathVariable Integer id,
                                  @RequestParam(value = "plans") Set<Plan> plans) {

        Optional<Traveler> traveler = travelerRepository.findById(id);
        if(traveler.isPresent()) {
            Traveler updated =  traveler.get();
            if(plans != null)
                updated.setPlans(plans);
            return ResponseEntity.ok(travelerService.updateTraveler(updated));
        }
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Business could not be updated");
    }


    @DeleteMapping("/business/{id}")
    ResponseEntity deleteUser(@PathVariable Integer id) {
        travelerService.deleteTravelerWithId(id);
        return ResponseEntity.ok("Business successfully deleted.");
    }

}
