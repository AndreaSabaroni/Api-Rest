package mutante.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mutante.modelo.ADN;
import mutante.modelo.Estadisticas;
import mutante.repositorios.RepositorioDeADN;

@Service
public class ServicioMutante {

	private static final String MUTANTE_A = "AAAA";
	private static final String MUTANTE_C = "CCCC";
	private static final String MUTANTE_T = "TTTT";
	private static final String MUTANTE_G = "GGGG";

	private RepositorioDeADN repositorioAdn;

	@Autowired
	public void setRepositorioAdn(RepositorioDeADN repositorioAdn) {
		this.repositorioAdn = repositorioAdn;
	}

	public boolean isMutant(List<String> dnaMutant) {
		
		String cadena = transformarACadena(dnaMutant);
		
		var adnPersistido = repositorioAdn.findByCadenaADN(cadena);

		if (adnPersistido == null) {
			adnPersistido = generarAdn(dnaMutant, cadena);

			repositorioAdn.save(adnPersistido);
		}

		return adnPersistido.getMutante();

	}
	public Estadisticas obtenerEstadisticasDeMutantes() {
		List<ADN> adns = repositorioAdn.findAll();
		
		Estadisticas estadistica = new Estadisticas();
		estadistica.setMutantes(obtenerCantidadDeMutantes(adns, true));
		estadistica.setEvaluados(adns.size());
		
		return estadistica;
	}

	private ADN generarAdn(List<String> dnaMutant, String cadena) {
		var adnPersistido= new ADN();

		boolean esMutante = esMutante(dnaMutant);
		adnPersistido.setMutante(esMutante);
		adnPersistido.setCadenaADN(cadena);
		
		return adnPersistido;
	}

	private String transformarACadena(List<String> dnaMutant) {
		 return String.join(", ", dnaMutant);
	}

	private boolean esMutante(List<String> dnaMutant) {
		return esMutanteHorizontal(dnaMutant) || esMutanteVertical(dnaMutant) || esMutanteDiagonal(dnaMutant);
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
			cadenaAdn = "";
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


	private int obtenerCantidadDeMutantes(List<ADN> adns, boolean esMutante) {
		var mutantes = adns.stream().filter(cadena -> cadena.getMutante() == esMutante );
		return mutantes.toArray().length;
	}

}
