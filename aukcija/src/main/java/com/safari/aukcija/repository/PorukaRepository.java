package com.safari.aukcija.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Korisnik;
import model.Poruka;

public interface PorukaRepository extends JpaRepository<Poruka, Integer> {

	List<Poruka> getByKorisnik1(Korisnik k);

}
