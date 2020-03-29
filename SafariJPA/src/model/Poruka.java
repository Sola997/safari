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

	@EmbeddedId
	private PorukaPK id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datum;

	private int idPosiljaoca;

	private String poruka;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@MapsId("idProdavca")
	@JoinColumn(name="idProdavca")
	private Korisnik korisnik1;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@MapsId("idKupca")
	@JoinColumn(name="idKupca")
	private Korisnik korisnik2;

	public Poruka() {
	}

	public PorukaPK getId() {
		return this.id;
	}

	public void setId(PorukaPK id) {
		this.id = id;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public int getIdPosiljaoca() {
		return this.idPosiljaoca;
	}

	public void setIdPosiljaoca(int idPosiljaoca) {
		this.idPosiljaoca = idPosiljaoca;
	}

	public String getPoruka() {
		return this.poruka;
	}

	public void setPoruka(String poruka) {
		this.poruka = poruka;
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