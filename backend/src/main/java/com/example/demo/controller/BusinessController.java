package com.example.demo.controller;


import com.example.demo.models.Business;
import com.example.demo.models.Entrepreneur;
import com.example.demo.repositories.BusinessRepository;
import com.example.demo.repositories.EntrepreneurRepository;
import com.example.demo.service.BusinessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class BusinessController {

    private BusinessService businessService;
    private EntrepreneurRepository entrepreneurRepository;
    private BusinessRepository businessRepository;

    public BusinessController() {
    }

    public BusinessController(BusinessService businessService, EntrepreneurRepository entrepreneurRepository, BusinessRepository businessRepository) {
        this.businessService = businessService;
        this.entrepreneurRepository = entrepreneurRepository;
        this.businessRepository = businessRepository;
    }

    @PostMapping("/business/add")
    public ResponseEntity createBusiness(@RequestParam(value = "name") String name,
                                     @RequestParam(value = "location") String location,
                                     @RequestParam(value = "entrepreneur_id") Integer entrepreneurId) {

        Business business = new Business();
        business.setName(name);
        business.setLocation(location);

        Optional<Entrepreneur> entrepreneur = entrepreneurRepository.findById(entrepreneurId);
        if(entrepreneur.isPresent()) {
            business.setEntrepreneur(entrepreneur.get());
            return ResponseEntity.ok(businessService.addBusiness(business));
        }

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Business could not be added");
    }

    @PatchMapping("/business/{id}")
    ResponseEntity updateBusiness(@PathVariable Integer id,
                                  @RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "location", required = false) String location,
                                  @RequestParam(value = "entrepreneur_id", required = false) Integer entrepreneurId
                                  ) {

        Optional<Business> business = businessRepository.findById(id);
        if(business.isPresent()) {
           Business updated =  business.get();
           if(name != null)
               updated.setName(name);
           if(location != null)
               updated.setLocation(location);
           if(entrepreneurId != null) {
               Optional<Entrepreneur> entrepreneur = entrepreneurRepository.findById(entrepreneurId);
               if(entrepreneur.isPresent()) {
                   updated.setEntrepreneur(entrepreneur.get());
               }
           }

           return ResponseEntity.ok(businessService.updateBusiness(updated));
        }

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Business could not be updated");
    }


    @DeleteMapping("/business/{id}")
    ResponseEntity deleteUser(@PathVariable Integer id) {
            businessService.deleteBusinessWithId(id);
            return ResponseEntity.ok("Business successfully deleted.");
    }
}

