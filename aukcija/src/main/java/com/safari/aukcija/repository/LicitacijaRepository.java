package com.safari.aukcija.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Licitacija;
import model.LicitacijaPK;
import model.Predmet;

public interface LicitacijaRepository extends JpaRepository<Licitacija, Integer> {

	List<Licitacija> findByPredmet(Predmet predmet);

	Optional<Licitacija> findByPobedio(byte b);

	Licitacija findById(LicitacijaPK id);

	Optional<Licitacija> findByPredmetAndPobedio(Predmet predmet, byte b);

}
