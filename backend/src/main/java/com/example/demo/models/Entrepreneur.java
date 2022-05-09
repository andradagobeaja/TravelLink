package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Entrepreneur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "business")
    private Set<Business> businesses;

    public Entrepreneur() {
    }

    public Entrepreneur(Integer id, Set<Business> business) {
        this.id = id;
        this.businesses = business;
    }

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Business> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(Set<Business> businesses) {
        this.businesses = businesses;
    }
}


