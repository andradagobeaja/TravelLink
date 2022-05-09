package com.example.demo.service;


import com.example.demo.models.Traveler;
import com.example.demo.repositories.TravelerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TravelerService {

    private TravelerRepository travelerRepository;

    public TravelerService() {
    }

    public TravelerService(TravelerRepository travelerRepository) {
        this.travelerRepository = travelerRepository;
    }

    public Traveler addTraveler(Traveler traveler) {
        return travelerRepository.save(traveler);
    }

    public Traveler updateTraveler(Traveler updated) {
        Optional<Traveler> traveler = travelerRepository.findById(updated.getId());
        if(traveler.isPresent()) {
            Traveler updatedTraveler = new Traveler();
            return travelerRepository.save(updatedTraveler);
        }
        return null;
    }

    public void deleteTravelerWithId(Integer id) {
        travelerRepository.deleteById(id);
    }
}
