package com.safari.aukcija.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import model.Predmet;

public interface PredmetRepository extends JpaRepository<Predmet, Integer> {

//	@Query("SELECT p FROM Predmet p INNER JOIN p.kategorija k where k.nazivKategorije='nazivKategorije'")
//	List<Predmet> predmetPoKategoriji(@Param("nazivKategorije") Kategorija nazivKategorije );
}
