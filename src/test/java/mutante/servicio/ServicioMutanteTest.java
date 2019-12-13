package mutante.servicio;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theory;

public class ServicioMutanteTest {

	ServicioMutante servicio = new ServicioMutante();

	@DataPoints("ADN_CARACTER_INVALIDO")
	public static final String[] ADN_CARACTER_INVALIDO = { "389YTA", "234156", "QWER?4", "T$$$$A", "KOFDF3", "DFASDF" };
	@DataPoints("ADN_CORTO")
	public static final String[] ADN_CORTO = { "A", "GCC", "C", "TG", " " };

	@DataPoints("MUTANTE_A")
	public static final String[] MUTANTE_A = { "TAAAAC", "AAAAGt", "ATAAAA", "TCAAAA", "CCAAAA" };
	@DataPoints("MUTANTE_T")
	public static final String[] MUTANTE_T = { "TTTTGa", "ATTTTG", "AGTTTT", "TCTTTT", "ATTTTg" };
	@DataPoints("MUTANTE_C")
	public static final String[] MUTANTE_C = { "TCCCCG", "CCCCCT", "ACCCCC", "TTCCCC", "CCCCTA" };
	@DataPoints("MUTANTE_G")
	public static final String[] MUTANTE_G = { "GGGGCT", "ACGGGG", "GGGGGC", "GGGGTa", "CGGGgA" };
	@DataPoints("LETRAS_PERMITIDAS")
	public static final String[] LETRAS_PERMITIDAS = { "A", "C", "T", "G", "a", "c", "t", "g" };

	@Test
	public void establecerMutanteInformaEsMutante() throws Exception {

		String[] dnaMutant = { "ATGCGA", "CAGTGC", "TTATGT", "AGAACG", "CCCTAG", "TCACTG" };

		boolean mutante = servicio.isMutant(dnaMutant);

		assertTrue(mutante);

	}

	@Theory
	public void lasLetrasDelADNSoloPermiten_ATCG(
			@FromDataPoints("ADN_CARACTER_INVALIDO") String adn_caracteres_invalidos) {

		String[] dnaMutant = { adn_caracteres_invalidos, "CAGTGC", "TTATGT", "AGAAGG", "CCCCCC", "ttttTT" };

		try {
			servicio.isMutant(dnaMutant);
			fail("debe lanzar excepción");
		} catch (Exception ex) {
			assertThat(ex.getMessage(), is("ADN mal formado, sólo se permite ATCG"));
		}
	}

	@Theory
	public void cadenasDeADNMEnoresACuatroNoEsMutante(@FromDataPoints("ADN_CORTO") String adn_corto) {

		String[] dnaMutant = { adn_corto };

		try {
			servicio.isMutant(dnaMutant);
			fail("debe lanzar excepción");
		} catch (Exception ex) {
			assertThat(ex.getMessage(), is("ADN mal formado, debe contener al menos 4 carateres"));
		}
	}

	@Theory
	public void unADNConCuatroA_ConsecutivosDeterminaQueEsMutante(@FromDataPoints("MUTANTE_A") String adn_mutanteA) {
		try {
			String[] dnaMutant = { adn_mutanteA, "AATTCC", "AATTCC", "AATTCC", "AATTCC", "AATTCC" };

			assertTrue(servicio.isMutant(dnaMutant));
		} catch (Exception ex) {
			fail("No debe fallar");
		}
	}

	@Theory
	public void unADNConCuatroT_ConsecutivosDeterminaQueEsMutante(@FromDataPoints("MUTANTE_T") String adn_mutanteT) {
		try {
			String[] dnaMutant = { adn_mutanteT, "AATTCC", "AATTCC", "AATTCC", "AATTCC", "AATTCC" };

			assertTrue(servicio.isMutant(dnaMutant));
		} catch (Exception ex) {
			fail("No debe fallar");
		}
	}

	@Theory
	public void unADNConCuatroG_ConsecutivosDeterminaQueEsMutante(@FromDataPoints("MUTANTE_G") String adn_mutanteG) {
		try {
			String[] dnaMutant = { adn_mutanteG, "AATTCC", "AATTCC", "AATTCC", "AATTCC", "AATTCC" };

			assertTrue(servicio.isMutant(dnaMutant));
		} catch (Exception ex) {
			fail("No debe fallar");
		}
	}

	@Theory
	public void unADNConCuatroC_ConsecutivosDeterminaQueEsMutante(@FromDataPoints("MUTANTE_C") String adn_mutanteC) {
		try {
			String[] dnaMutant = { adn_mutanteC, "AATTCC", "AATTCC", "AATTCC", "AATTCC", "AATTCC" };

			assertTrue(servicio.isMutant(dnaMutant));
		} catch (Exception ex) {
			fail("No debe fallar");
		}
	}

	@Test
	public void siLasCadenasDeUnADN_NoTienenLaMismaLongitudEntoncesEsInvalido() {

		try {
			String[] dnaMutant = { "AAAT", "TCGTAAA", "AACCGGTT", "AACCTTAAGG" };

			servicio.isMutant(dnaMutant);
			fail("Debe fallar");
		} catch (Exception ex) {
			assertThat(ex.getMessage(), is("ADN mal formado, cadenas de distinta longitud"));

		}
	}

	@Test
	public void siLaCantidadDeCadenasDelADN_EsDistintaALaLongitudDeLaCadena_EntoncesEsInvalido() {

		try {
			String[] dnaMutant = { "AAATC", "TCGTA", "AACCG", "TAAGG" };

			servicio.isMutant(dnaMutant);
			fail("Debe fallar");
		} catch (Exception ex) {
			assertThat(ex.getMessage(), is("ADN mal formado, no se puede armar matríz de evaluación"));

		}
	}

	@Theory
	public void SiTodasLasCadenasComienzanConLaMisaLetraAceptada_EntoncesEsMutanteHorizontal(
			@FromDataPoints("LETRAS_PERMITIDAS") String letraPermitida) {

		try {
			String[] dnaMutant = { letraPermitida + "AATC", letraPermitida + "CGTA", letraPermitida + "ACCG",
					letraPermitida + "AAGG", letraPermitida + "CCtg" };

			assertTrue(servicio.isMutant(dnaMutant));
		} catch (Exception ex) {
			fail("No debe fallar");
		}
	}

	@Theory
	public void SiTodasLasCadenasFinalizanConLaMisaLetraAceptada_EntoncesEsMutanteHorizontal(
			@FromDataPoints("LETRAS_PERMITIDAS") String letraPermitida) {

		try {
			String[] dnaMutant = { "AATC" + letraPermitida, "CGTA" + letraPermitida, "ACCG" + letraPermitida,
					"AAGG" + letraPermitida, "CCtg" + letraPermitida };

			assertTrue(servicio.isMutant(dnaMutant));
		} catch (Exception ex) {
			fail("No debe fallar");
		}
	}

}
