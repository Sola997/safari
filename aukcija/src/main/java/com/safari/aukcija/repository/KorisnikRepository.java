package com.safari.aukcija.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {
	@Query("select k from Korisnik k where k.e_mail = :e_mail and k.password = :password ")
	Korisnik login(@Param("e_mail") String e_mail, @Param("password") String password);
}
