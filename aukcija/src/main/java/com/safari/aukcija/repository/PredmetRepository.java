package com.safari.aukcija.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Kategorija;
import model.Predmet;

public interface PredmetRepository extends JpaRepository<Predmet, Integer> {

	List<Predmet> findByKategorija(Kategorija k);

	@Query("SELECT p FROM Predmet p INNER JOIN p.kategorija k where k.nazivKategorije='nazivKategorije'")
	List<Predmet> predmetPoKategoriji(@Param("nazivKategorije") Kategorija nazivKategorije );
}
