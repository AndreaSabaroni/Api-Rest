package mutante.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class ADN implements Serializable{

	private static final long serialVersionUID = -6329305028263171775L;
	private boolean mutante;
	private String cadenaADN; 

	protected Long id;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean getMutante() {
		return mutante;
	}

	public void setMutante(boolean mutante) {
		this.mutante = mutante;
	}

	public String getCadenaADN() {
		return cadenaADN;
	}

	public void setCadenaADN(String cadenaADN) {
		this.cadenaADN = cadenaADN;
	}
/*
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ADN other = (ADN) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
	
*/
}
