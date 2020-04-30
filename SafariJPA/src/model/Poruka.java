package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the poruka database table.
 * 
 */
@Entity
@NamedQuery(name="Poruka.findAll", query="SELECT p FROM Poruka p")
public class Poruka implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPoruka;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datum;

	private String tekstPoruke;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="idKorisnik1")
	private Korisnik korisnik1;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="idKorisnik2")
	private Korisnik korisnik2;

	public Poruka() {
	}

	public int getIdPoruka() {
		return this.idPoruka;
	}

	public void setIdPoruka(int idPoruka) {
		this.idPoruka = idPoruka;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getTekstPoruke() {
		return this.tekstPoruke;
	}

	public void setTekstPoruke(String tekstPoruke) {
		this.tekstPoruke = tekstPoruke;
	}

	public Korisnik getKorisnik1() {
		return this.korisnik1;
	}

	public void setKorisnik1(Korisnik korisnik1) {
		this.korisnik1 = korisnik1;
	}

	public Korisnik getKorisnik2() {
		return this.korisnik2;
	}

	public void setKorisnik2(Korisnik korisnik2) {
		this.korisnik2 = korisnik2;
	}

}