package com.safari.aukcija.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Ocena;

public interface OcenaRepository extends JpaRepository<Ocena, Integer> {

	@Query("SELECT o FROM Ocena o where o.korisnik.idKorisnik=:idKorisnik")
	List<Ocena> getByKorisnik(@Param("idKorisnik") Integer idKorisnik);
	
	@Query("SELECT o FROM Ocena o JOIN o.korisnik k WHERE k.username=:username")
	List<Ocena> getByUsername(@Param("username") String username);
	
}
