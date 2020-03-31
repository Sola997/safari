package com.safari.aukcija.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Licitacija;

public interface LicitacijaRepository extends JpaRepository<Licitacija, Integer> {

}
