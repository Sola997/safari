package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the licitacija database table.
 * 
 */
@Embeddable
public class LicitacijaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int korisnik_idKorisnik;

	@Column(insertable=false, updatable=false)
	private int predmet_idPredmet;

	public LicitacijaPK() {
	}
	public int getKorisnik_idKorisnik() {
		return this.korisnik_idKorisnik;
	}
	public void setKorisnik_idKorisnik(int korisnik_idKorisnik) {
		this.korisnik_idKorisnik = korisnik_idKorisnik;
	}
	public int getPredmet_idPredmet() {
		return this.predmet_idPredmet;
	}
	public void setPredmet_idPredmet(int predmet_idPredmet) {
		this.predmet_idPredmet = predmet_idPredmet;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof LicitacijaPK)) {
			return false;
		}
		LicitacijaPK castOther = (LicitacijaPK)other;
		return 
			(this.korisnik_idKorisnik == castOther.korisnik_idKorisnik)
			&& (this.predmet_idPredmet == castOther.predmet_idPredmet);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.korisnik_idKorisnik;
		hash = hash * prime + this.predmet_idPredmet;
		
		return hash;
	}
}