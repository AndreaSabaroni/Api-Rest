package mutante.request;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import mutante.validaciones.CadenaAdn;

public class RequestADN implements Serializable{
	private static final long serialVersionUID = 9099716755396814646L;

	private List<String> adn;

	@NotEmpty(message = "Debe informar el adn")
	@CadenaAdn(message = "Cadena de Adn inválida")
	public List<String> getAdn() {
		return adn;
	}

	public void setAdn(List<String> adn) {
		this.adn = adn;
	}
}
