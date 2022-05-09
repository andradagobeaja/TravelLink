package com.example.demo.repositories;


import com.example.demo.models.Entrepreneur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrepreneurRepository extends JpaRepository<Entrepreneur, Integer> {
}