package mutante.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ServicioMutante {

	private static final String MUTANTE_A = "AAAA";
	private static final String MUTANTE_C = "CCCC";
	private static final String MUTANTE_T = "TTTT";
	private static final String MUTANTE_G = "GGGG";

	public boolean isMutant(List<String> dnaMutant){
		return esMutante(dnaMutant);

	}

	private boolean esMutante(List<String> dnaMutant) {
		return esMutanteHorizontal(dnaMutant) 
				|| esMutanteVertical(dnaMutant) 
				|| esMutanteDiagonal(dnaMutant);
	}

	private boolean esMutanteDiagonal(List<String> dnaMutant) {
		String cadenaAdn = "";
		int indiceDiagonal = 0;
		for (String adn : dnaMutant) {
			cadenaAdn = cadenaAdn + adn.charAt(indiceDiagonal);
			indiceDiagonal++;
		}
		if (esCadenaMutante(cadenaAdn))
			return true;

		return false;
	}

	private boolean esMutanteVertical(List<String> dnaMutant) {

		String cadenaAdn = "";
		for (int indiceVertical = 0; indiceVertical < dnaMutant.size(); indiceVertical++) {
			for (String adn : dnaMutant) {
				cadenaAdn = cadenaAdn + adn.charAt(indiceVertical);
			}
			if (esCadenaMutante(cadenaAdn))
				return true;
			cadenaAdn ="";
		}
		return false;
	}

	private boolean esMutanteHorizontal(List<String> dnaMutant) {

		boolean esMutante = false;

		for (String adn : dnaMutant) {
			if (esCadenaMutante(adn))
				return true;

		}
		return esMutante;
	}

	private boolean esCadenaMutante(String adn) {
		String adnIgnoreCase = adn.toUpperCase();

		return adnIgnoreCase.contains(MUTANTE_A) || adnIgnoreCase.contains(MUTANTE_C)
				|| adnIgnoreCase.contains(MUTANTE_G) || adnIgnoreCase.contains(MUTANTE_T);
	}


}
