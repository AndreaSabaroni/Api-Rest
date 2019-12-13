package mutante.servicio;

import org.springframework.stereotype.Service;

@Service
public class ServicioMutante {

	private static final String ADN_DISTINTA_LONGITUD = "ADN mal formado, cadenas de distinta longitud";
	private static final String CARACTERES_VALIDOS = "ATCG";
	private static final String MUTANTE_A = "AAAA";
	private static final String MUTANTE_C = "CCCC";
	private static final String MUTANTE_T = "TTTT";
	private static final String MUTANTE_G = "GGGG";
	private static final String ADN_CARACTER_INVALIDO = "ADN mal formado, sólo se permite ATCG";
	private static final String ADN_LONGITUD_INVALIDA = "ADN mal formado, debe contener al menos 4 carateres";
	private static final String ADN_TAMAÑO_INCORRECTO = "ADN mal formado, no se puede armar matríz de evaluación";

	public boolean isMutant(String[] dnaMutant) throws Exception {
		esADNValido(dnaMutant);
		return esMutante(dnaMutant);

	}

	private void esADNValido(String[] dnaMutant) throws Exception {
		int longitudCadena = dnaMutant[0].length();
		if (dnaMutant.length != longitudCadena)
			throw new Exception(ADN_TAMAÑO_INCORRECTO);

		for (String adn : dnaMutant) {
			if (adn.length() != longitudCadena)
				throw new Exception(ADN_DISTINTA_LONGITUD);

			esCadenaDeADNValida(adn.toUpperCase());
		}
	}

	private void esCadenaDeADNValida(String adn) throws Exception {
		if (adn == null || adn.isBlank() || adn.length() < 4)
			throw new Exception(ADN_LONGITUD_INVALIDA);
		for (int i = 0; i < adn.length(); i++) {
			if (CARACTERES_VALIDOS.indexOf(adn.charAt(i)) < 0)
				throw new Exception(ADN_CARACTER_INVALIDO);
		}
	}

	private boolean esMutante(String[] dnaMutant) {
		return esMutanteHorizontal(dnaMutant) 
				|| esMutanteVertical(dnaMutant) 
				|| esMutanteDiagonal(dnaMutant);
	}

	private boolean esMutanteDiagonal(String[] dnaMutant) {
		String cadenaAdn = "";
		int indiceDiagonal = 0;
		for (String adn : dnaMutant) {
			cadenaAdn = cadenaAdn + adn.charAt(indiceDiagonal);
			indiceDiagonal++;
		}
		if (esMutante(cadenaAdn))
			return true;

		return false;
	}

	private boolean esMutanteVertical(String[] dnaMutant) {

		String cadenaAdn = "";
		for (int indiceVertical = 0; indiceVertical < dnaMutant.length; indiceVertical++) {
			for (String adn : dnaMutant) {
				cadenaAdn = cadenaAdn + adn.charAt(indiceVertical);
			}
			if (esMutante(cadenaAdn))
				return true;

		}
		return false;
	}

	private boolean esMutanteHorizontal(String[] dnaMutant) {

		boolean esMutante = false;

		for (String adn : dnaMutant) {
			if (esMutante(adn))
				return true;

		}
		return esMutante;
	}

	private boolean esMutante(String adn) {
		String adnIgnoreCase = adn.toUpperCase();

		return adnIgnoreCase.contains(MUTANTE_A) || adnIgnoreCase.contains(MUTANTE_C)
				|| adnIgnoreCase.contains(MUTANTE_G) || adnIgnoreCase.contains(MUTANTE_T);
	}


}
