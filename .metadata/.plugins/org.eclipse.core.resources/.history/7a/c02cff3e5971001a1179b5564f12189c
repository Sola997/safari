package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the poruka database table.
 * 
 */
@Embeddable
public class PorukaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int idProdavca;

	@Column(insertable=false, updatable=false)
	private int idKupca;

	public PorukaPK() {
	}
	public int getIdProdavca() {
		return this.idProdavca;
	}
	public void setIdProdavca(int idProdavca) {
		this.idProdavca = idProdavca;
	}
	public int getIdKupca() {
		return this.idKupca;
	}
	public void setIdKupca(int idKupca) {
		this.idKupca = idKupca;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PorukaPK)) {
			return false;
		}
		PorukaPK castOther = (PorukaPK)other;
		return 
			(this.idProdavca == castOther.idProdavca)
			&& (this.idKupca == castOther.idKupca);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idProdavca;
		hash = hash * prime + this.idKupca;
		
		return hash;
	}
}