package com.example.demo.service;

import com.example.demo.models.Entrepreneur;
import com.example.demo.repositories.EntrepreneurRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EntrepreneurService {
    private EntrepreneurRepository entrepreneurRepository;

    public EntrepreneurService(EntrepreneurRepository entrepreneurRepository) {
        this.entrepreneurRepository = entrepreneurRepository;
    }


    public Entrepreneur addEntrepreneur(Entrepreneur entrepreneur) {
        return entrepreneurRepository.save(entrepreneur);
    }

    public Entrepreneur updateEntrepreneur(Entrepreneur updated) {
        Optional<Entrepreneur> entrepreneur = entrepreneurRepository.findById(updated.getId());
        if(entrepreneur.isPresent()) {
            Entrepreneur updatedEntrepreneur = entrepreneur.get();
            updatedEntrepreneur.setBusinesses(updated.getBusinesses());

            return entrepreneurRepository.save(updatedEntrepreneur);
        }
        return null;
    }

    public void deleteEntrepreneurWithId(Integer id) {
        entrepreneurRepository.deleteById(id);
    }
}
