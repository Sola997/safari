package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the predmet database table.
 * 
 */
@Entity
@NamedQuery(name="Predmet.findAll", query="SELECT p FROM Predmet p")
public class Predmet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPredmet;

	@Temporal(TemporalType.TIMESTAMP)
	private Date krajAukcije;

	private String nazivPredmeta;

	private String opis;

	private int pocetnaCena;

	private String stanje;

	private byte status;

	//bi-directional many-to-one association to Licitacija
	@OneToMany(mappedBy="predmet")
	@Transient
	private List<Licitacija> licitacijas;

	//bi-directional many-to-one association to Kategorija
	@ManyToOne
	private Kategorija kategorija;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="idProdavca")
	private Korisnik korisnik;

	//bi-directional many-to-one association to Slika
	@OneToMany(mappedBy="predmet")
	@Transient
	private List<Slika> slikas;

	public Predmet() {
	}

	public int getIdPredmet() {
		return this.idPredmet;
	}

	public void setIdPredmet(int idPredmet) {
		this.idPredmet = idPredmet;
	}

	public Date getKrajAukcije() {
		return this.krajAukcije;
	}

	public void setKrajAukcije(Date krajAukcije) {
		this.krajAukcije = krajAukcije;
	}

	public String getNazivPredmeta() {
		return this.nazivPredmeta;
	}

	public void setNazivPredmeta(String nazivPredmeta) {
		this.nazivPredmeta = nazivPredmeta;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getPocetnaCena() {
		return this.pocetnaCena;
	}

	public void setPocetnaCena(int pocetnaCena) {
		this.pocetnaCena = pocetnaCena;
	}

	public String getStanje() {
		return this.stanje;
	}

	public void setStanje(String stanje) {
		this.stanje = stanje;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public List<Licitacija> getLicitacijas() {
		return this.licitacijas;
	}

	public void setLicitacijas(List<Licitacija> licitacijas) {
		this.licitacijas = licitacijas;
	}

	public Licitacija addLicitacija(Licitacija licitacija) {
		getLicitacijas().add(licitacija);
		licitacija.setPredmet(this);

		return licitacija;
	}

	public Licitacija removeLicitacija(Licitacija licitacija) {
		getLicitacijas().remove(licitacija);
		licitacija.setPredmet(null);

		return licitacija;
	}

	public Kategorija getKategorija() {
		return this.kategorija;
	}

	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public List<Slika> getSlikas() {
		return this.slikas;
	}

	public void setSlikas(List<Slika> slikas) {
		this.slikas = slikas;
	}

	public Slika addSlika(Slika slika) {
		getSlikas().add(slika);
		slika.setPredmet(this);

		return slika;
	}

	public Slika removeSlika(Slika slika) {
		getSlikas().remove(slika);
		slika.setPredmet(null);

		return slika;
	}

	@Override
	public String toString() {
		return "idPredmet=" + idPredmet + ", nazivPredmeta=" + nazivPredmeta + ", pocetnaCena=" + pocetnaCena;
	}
	

}