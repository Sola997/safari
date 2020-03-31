package com.safari.aukcija.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Predmet;

public interface PredmetRepository extends JpaRepository<Predmet, Integer> {

}
