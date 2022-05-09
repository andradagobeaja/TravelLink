package com.example.demo.controller;

import com.example.demo.models.Business;
import com.example.demo.models.Entrepreneur;
import com.example.demo.repositories.BusinessRepository;
import com.example.demo.repositories.EntrepreneurRepository;
import com.example.demo.service.BusinessService;
import com.example.demo.service.EntrepreneurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@Controller
public class EntrepreneurController {

    private EntrepreneurService entrepreneurService;
    private EntrepreneurRepository entrepreneurRepository;


    public EntrepreneurController() {
    }

    public EntrepreneurController(EntrepreneurService entrepreneurService, EntrepreneurRepository entrepreneurRepository, BusinessRepository businessRepository) {
        this.entrepreneurService = entrepreneurService;
        this.entrepreneurRepository = entrepreneurRepository;
    }

    @PostMapping("/entrepreneur/add")
    public ResponseEntity createEntrepreneur(@RequestParam(value = "businesses") Set<Business> businesses)  {
        Entrepreneur entrepreneur = new Entrepreneur();
        entrepreneur.setBusinesses(businesses);
        return ResponseEntity.ok(entrepreneurService.addEntrepreneur(entrepreneur));
    }

    @PatchMapping("/entrepreneur/{id}")
    ResponseEntity updateEntrepreneur(@PathVariable Integer id, @RequestParam(value = "businesses") Set<Business> businesses) {
        Optional<Entrepreneur> entrepreneur = entrepreneurRepository.findById(id);
        if (entrepreneur.isPresent()) {
            Entrepreneur updated = entrepreneur.get();
            if (businesses != null) {
                updated.setBusinesses(businesses);
            }

            return ResponseEntity.ok(entrepreneurService.updateEntrepreneur(updated));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Entrepreneur could not be updated");
    }

    @DeleteMapping("/business/{id}")
    ResponseEntity deleteUser(@PathVariable Integer id) {
        entrepreneurService.deleteEntrepreneurWithId(id);
        return ResponseEntity.ok("Business successfully deleted.");
    }
}
