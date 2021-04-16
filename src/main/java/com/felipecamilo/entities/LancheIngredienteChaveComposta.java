package com.felipecamilo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LancheIngredienteChaveComposta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "lanche_id")
	private Long lancheID;

	@Column(name = "ingrediente_id")
	private Long ingredienteID;

	public LancheIngredienteChaveComposta() {
	}
	
	public LancheIngredienteChaveComposta(Long lancheID, Long ingredienteID) {
		super();
		this.lancheID = lancheID;
		this.ingredienteID = ingredienteID;
	}

	public Long getLancheID() {
		return lancheID;
	}

	public Long getIngredienteID() {
		return ingredienteID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ingredienteID == null) ? 0 : ingredienteID.hashCode());
		result = prime * result + ((lancheID == null) ? 0 : lancheID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LancheIngredienteChaveComposta other = (LancheIngredienteChaveComposta) obj;
		if (ingredienteID == null) {
			if (other.ingredienteID != null)
				return false;
		} else if (!ingredienteID.equals(other.ingredienteID))
			return false;
		if (lancheID == null) {
			if (other.lancheID != null)
				return false;
		} else if (!lancheID.equals(other.lancheID))
			return false;
		return true;
	}

}
