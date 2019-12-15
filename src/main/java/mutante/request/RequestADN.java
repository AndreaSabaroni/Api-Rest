package mutante.request;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import mutante.validaciones.CadenaAdn;

public class RequestADN {
	@NotEmpty
	@CadenaAdn(message="Cadena de Adn inválida")
	private List<String> adn;


	public List<String> getAdn() {
		return adn;
	}

	public void setAdn(List<String> adn) {
		this.adn = adn;
	}
}
