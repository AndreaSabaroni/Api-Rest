package mutante.servicio;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theory;

public class ServicioMutanteTest {

	ServicioMutante servicio = new ServicioMutante();

	@DataPoints("MUTANTE_A")
	public static final String[] MUTANTE_A = { "TAAAAC", "AAAAGt", "ATAAAA", "TCAAAA", "CCAAAA" };
	@DataPoints("MUTANTE_T")
	public static final String[] MUTANTE_T = { "TTTTGa", "ATTTTG", "AGTTTT", "TCTTTT", "ATTTTg" };
	@DataPoints("MUTANTE_C")
	public static final String[] MUTANTE_C = { "TCCCCG", "CCCCCT", "ACCCCC", "TTCCCC", "CCCCTA" };
	@DataPoints("MUTANTE_G")
	public static final String[] MUTANTE_G = { "GGGGCT", "ACGGGG", "GGGGGC", "GGGGTa", "CGGGgA" };

	@Test
	public void establecerMutanteInformaEsMutante() throws Exception {

		List<String> dnaMutant = Arrays.asList("ATGCGA", "CAGTGC", "TTATGT", "AGAACG", "CCCTAG", "TCACTG");

		boolean mutante = servicio.isMutant(dnaMutant);

		assertTrue(mutante);

	}

	@Theory
	public void unADNConCuatroA_ConsecutivosDeterminaQueEsMutante(@FromDataPoints("MUTANTE_A") String adn_mutanteA) {
		try {
			List<String> dnaMutant = Arrays.asList(adn_mutanteA, "AATTCC", "AATTCC", "AATTCC", "AATTCC", "AATTCC");

			assertTrue(servicio.isMutant(dnaMutant));
		} catch (Exception ex) {
			fail("No debe fallar");
		}
	}

	@Theory
	public void unADNConCuatroT_ConsecutivosDeterminaQueEsMutante(@FromDataPoints("MUTANTE_T") String adn_mutanteT) {
		try {
			List<String> dnaMutant = Arrays.asList(adn_mutanteT, "AATTCC", "AATTCC", "AATTCC", "AATTCC", "AATTCC");

			assertTrue(servicio.isMutant(dnaMutant));
		} catch (Exception ex) {
			fail("No debe fallar");
		}
	}

	@Theory
	public void unADNConCuatroG_ConsecutivosDeterminaQueEsMutante(@FromDataPoints("MUTANTE_G") String adn_mutanteG) {
		try {
			List<String> dnaMutant = Arrays.asList(adn_mutanteG, "AATTCC", "AATTCC", "AATTCC", "AATTCC", "AATTCC");

			assertTrue(servicio.isMutant(dnaMutant));
		} catch (Exception ex) {
			fail("No debe fallar");
		}
	}

	@Theory
	public void unADNConCuatroC_ConsecutivosDeterminaQueEsMutante(@FromDataPoints("MUTANTE_C") String adn_mutanteC) {
		try {
			List<String> dnaMutant = Arrays.asList(adn_mutanteC, "AATTCC", "AATTCC", "AATTCC", "AATTCC", "AATTCC");

			assertTrue(servicio.isMutant(dnaMutant));
		} catch (Exception ex) {
			fail("No debe fallar");
		}
	}

	@Theory
	public void SiTodasLasCadenasComienzanConLaMisaLetraAceptada_EntoncesEsMutanteHorizontal(
			@FromDataPoints("LETRAS_PERMITIDAS") String letraPermitida) {

		try {
			List<String> dnaMutant = Arrays.asList(letraPermitida + "AATC", letraPermitida + "CGTA",
					letraPermitida + "ACCG", letraPermitida + "AAGG", letraPermitida + "CCtg");

			assertTrue(servicio.isMutant(dnaMutant));
		} catch (Exception ex) {
			fail("No debe fallar");
		}
	}

	@Theory
	public void SiTodasLasCadenasFinalizanConLaMisaLetraAceptada_EntoncesEsMutanteHorizontal(
			@FromDataPoints("LETRAS_PERMITIDAS") String letraPermitida) {

		try {
			List<String> dnaMutant = Arrays.asList("AATC" + letraPermitida, "CGTA" + letraPermitida,
					"ACCG" + letraPermitida, "AAGG" + letraPermitida, "CCtg" + letraPermitida);

			assertTrue(servicio.isMutant(dnaMutant));
		} catch (Exception ex) {
			fail("No debe fallar");
		}
	}

}
