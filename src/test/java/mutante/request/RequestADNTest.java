package mutante.request;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import mutante.request.RequestADN;

@RunWith(Theories.class)
public class RequestADNTest {
	private Validator validator;
	private static final String ADN_MAL_FORMADO = "Cadena de Adn inválida";

	
	@DataPoints("ADN_CARACTER_INVALIDO")
	public static final String[] ADN_CARACTER_INVALIDO = { "389YTA", "234156", "QWER?4", "T$$$$A", "KOFDF3", "DFASDF" };
	@DataPoints("ADN_CORTO")
	public static final String[] ADN_CORTO = { "A", "GCC", "C", "TG", " " };
	@DataPoints("LETRAS_PERMITIDAS")
	public static final String[] LETRAS_PERMITIDAS = { "A", "C", "T", "G", "a", "c", "t", "g" };

	@Before
	public void inicializar() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Theory
	public void unRequestConCaracteresInvalidosIndicaErrorDeValidacionDeCadena(
			@FromDataPoints("ADN_CARACTER_INVALIDO") String adn_caracteres_invalidos) {

		List<String> dnaMutant = Arrays.asList(adn_caracteres_invalidos, "CAGTGC", "TTATGT", "AGAAGG", "CCCCCC",
				"ttttTT");

		RequestADN requestADN = obtenerRequestConCadena(dnaMutant);

		Set<ConstraintViolation<RequestADN>> violacionesDeValidaciones = validator.validate(requestADN);

		assertTrue(violacionesDeValidaciones.stream().anyMatch(c -> c.getMessageTemplate().equals(ADN_MAL_FORMADO)));

	}

	@Theory
	public void RequestConMenosdeCuatroCaracteresEnLaCadenasDeADN_EsInvalido(@FromDataPoints("ADN_CORTO") String adn_corto) {

		List<String> dnaMutant = Arrays.asList(adn_corto);
		RequestADN requestADN = obtenerRequestConCadena(dnaMutant);

		Set<ConstraintViolation<RequestADN>> violacionesDeValidaciones = validator.validate(requestADN);

		assertTrue(violacionesDeValidaciones.stream().anyMatch(c -> c.getMessageTemplate().equals(ADN_MAL_FORMADO)));
	}

	@Test
	public void siLasCadenasDeUnADN_NoTienenLaMismaLongitudEntoncesEsInvalido() {

		List<String> dnaMutant = Arrays.asList("AAAT", "TCGTAAA", "AACCGGTT", "AACCTTAAGG");
		RequestADN requestADN = obtenerRequestConCadena(dnaMutant);

		Set<ConstraintViolation<RequestADN>> violacionesDeValidaciones = validator.validate(requestADN);

		assertTrue(violacionesDeValidaciones.stream().anyMatch(c -> c.getMessageTemplate().equals(ADN_MAL_FORMADO)));
	}

	@Test
	public void siLaCantidadDeCadenasDelADN_EsDistintaALaLongitudDeLaCadena_EntoncesEsInvalido() {

		List<String> dnaMutant = Arrays.asList("AAATC", "TCGTA", "AACCG", "TAAGG");

		RequestADN requestADN = obtenerRequestConCadena(dnaMutant);

		Set<ConstraintViolation<RequestADN>> violacionesDeValidaciones = validator.validate(requestADN);

		assertTrue(violacionesDeValidaciones.stream().anyMatch(c -> c.getMessageTemplate().equals(ADN_MAL_FORMADO)));
	}


	@Theory
	public void unRequestConCadenaADNCorrecta_EsValido(@FromDataPoints("LETRAS_PERMITIDAS") String letraPermitida) {

		List<String> dnaMutant = Arrays.asList("ATGCG"+letraPermitida, "CAGTGC", "TTATGT", "AGAACG", "CCCTAG", "TCACTG");
		
		RequestADN requestADN = obtenerRequestConCadena(dnaMutant);

		Set<ConstraintViolation<RequestADN>> violacionesDeValidaciones = validator.validate(requestADN);

		assertFalse(violacionesDeValidaciones.stream().anyMatch(c -> c.getMessageTemplate().equals(ADN_MAL_FORMADO)));
	}

	
	private RequestADN obtenerRequestConCadena(List<String> dnaMutant) {
		RequestADN requestADN = new RequestADN();
		requestADN.setAdn(dnaMutant);
		return requestADN;
	}

}
