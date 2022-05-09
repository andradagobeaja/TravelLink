package com.example.demo.service;


import com.example.demo.models.Business;
import com.example.demo.repositories.BusinessRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BusinessService {

    private BusinessRepository businessRepository;

    public BusinessService() {}

    public BusinessService(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }


    public Business addBusiness(Business business) {
        return businessRepository.save(business);
    }

    public Business updateBusiness(Business updated) {
        Optional<Business> business = businessRepository.findById(updated.getId());
        if(business.isPresent()) {
            Business updatedBusiness = business.get();
            updatedBusiness.setName(updated.getName());
            updatedBusiness.setLocation(updated.getLocation());
            updatedBusiness.setEntrepreneur(updated.getEntrepreneur());
            return businessRepository.save(updatedBusiness);
        }
        return null;
    }

    public void deleteBusinessWithId(Integer id) {
        businessRepository.deleteById(id);
    }
}
