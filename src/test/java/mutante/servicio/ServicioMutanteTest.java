package mutante.servicio;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import mutante.modelo.ADN;
import mutante.repositorios.RepositorioDeADN;

@RunWith(MockitoJUnitRunner.class)
public class ServicioMutanteTest {
	@InjectMocks
	ServicioMutante servicio;

	@Mock
	RepositorioDeADN repositorio;

	@Test
	public void establecerMutanteInformaEsMutante() throws Exception {

		dadoQueElADNNoExisteEnLaBase();
		List<String> dnaMutant = Arrays.asList("ATGCGA", "CAGTGC", "TTATGT", "AGAACG", "CCCTAG", "TCACTG");

		boolean mutante = servicio.isMutant(dnaMutant);

		assertTrue(mutante);
		Mockito.verify(repositorio).save(Mockito.any(ADN.class));
	}
	@Test
	public void consultarPorUnAdnExistenteEnLABaseNoVuelveAPersistirlo() throws Exception {

		dadoQueElADNExisteEnLaBaseRetornaElAdnPersistidoIndicandoMutante(true);
		List<String> dnaMutant = Arrays.asList("ATGCGA", "CAGTGC", "TTATGT", "AGAACG", "CCCTAG", "TCACTG");

		boolean mutante = servicio.isMutant(dnaMutant);

		assertTrue(mutante);
		Mockito.verify(repositorio, Mockito.never()).save(Mockito.any(ADN.class));
	}

	@Test
	public void consultarPorUnAdnExistenteEnLABaseRespondeSiEsMutanteSegunLoYaDeterminado() throws Exception {

		dadoQueElADNExisteEnLaBaseRetornaElAdnPersistidoIndicandoMutante(false);
		List<String> dnaMutant = Arrays.asList("ATGCGA", "CAGTGC", "TTATGT", "AGAACG", "CCCTAG", "TCACTG");

		boolean mutante = servicio.isMutant(dnaMutant);

		assertFalse(mutante);
		Mockito.verify(repositorio, Mockito.never()).save(Mockito.any(ADN.class));
	}

	@Test
	public void unADNConCuatroA_ConsecutivosDeterminaQueEsMutante() {
		try {
			dadoQueElADNNoExisteEnLaBase();
			List<String> dnaMutant = Arrays.asList("CCAAAA", "AATTCC", "AATTCC", "AATTCC", "AATTCC", "AATTCC");

			assertTrue(servicio.isMutant(dnaMutant));
		} catch (Exception ex) {
			fail("No debe fallar");
		}
	}

	@Test
	public void unADNConCuatroT_ConsecutivosDeterminaQueEsMutante() {
		try {
			dadoQueElADNNoExisteEnLaBase();

			List<String> dnaMutant = Arrays.asList("cATTCC", "AATTCC", "AATTCC", "AATTCC", "AATTCC", "AATTCC");

			assertTrue(servicio.isMutant(dnaMutant));
		} catch (Exception ex) {
			fail("No debe fallar");
		}
	}

	@Test
	public void unADNConCuatroG_ConsecutivosDeterminaQueEsMutante() {
		try {
			dadoQueElADNNoExisteEnLaBase();

			List<String> dnaMutant = Arrays.asList("gATTCC", "AATTCC", "AATTCC", "AATTCC", "AATTCC", "AATTCC");

			assertTrue(servicio.isMutant(dnaMutant));
		} catch (Exception ex) {
			fail("No debe fallar");
		}
	}

	@Test
	public void unADNConCuatroC_ConsecutivosDeterminaQueEsMutante() {
		try {
			dadoQueElADNNoExisteEnLaBase();

			List<String> dnaMutant = Arrays.asList("gctaac", "AATTCC", "AATTCC", "AATTCC", "AATTCC", "AATTCC");

			assertTrue(servicio.isMutant(dnaMutant));
		} catch (Exception ex) {
			fail("No debe fallar");
		}
	}

	@Test
	public void SiTodasLasCadenasComienzanConLaMisaLetraAceptada_EntoncesEsMutanteHorizontal() {

		try {
			dadoQueElADNNoExisteEnLaBase();

			List<String> dnaMutant = Arrays.asList("gAATC", "GCGTA", "gACCG", "GAAGG", "aCCtg");

			assertTrue(servicio.isMutant(dnaMutant));
		} catch (Exception ex) {
			fail("No debe fallar");
		}
	}

	@Test
	public void SiTodasLasCadenasFinalizanConLaMisaLetraAceptada_EntoncesEsMutanteHorizontal() {

		try {
			dadoQueElADNNoExisteEnLaBase();

			List<String> dnaMutant = Arrays.asList("AATCc", "CGTAC", "ACCGc", "AAGGC", "CCtga");

			assertTrue(servicio.isMutant(dnaMutant));
		} catch (Exception ex) {
			fail("No debe fallar");
		}
	}
	
	@Test
	public void siLASegundaDiagonalTieneCadenaDeLetrasConsecutivas_EntoncesEsMutanteDiagonal() {

		try {
			dadoQueElADNNoExisteEnLaBase();

			List<String> dnaMutant = Arrays.asList("cATCc", "CAGTC", "CCAGc", "AGGAC", "CCtga");

			assertTrue(servicio.isMutant(dnaMutant));
		} catch (Exception ex) {
			fail("No debe fallar");
		}
	}

	@Test
	public void siLaSegundaCadenaYLASegundaDiagonalTieneCadenaDeLetrasConsecutivas_EntoncesEsMutanteDiagonal() {

		try {
			dadoQueElADNNoExisteEnLaBase();

			List<String> dnaMutant = Arrays.asList("ccTCc", "CAGTC", "CCAGc", "AGGAC", "CCtga");

			assertTrue(servicio.isMutant(dnaMutant));
		} catch (Exception ex) {
			fail("No debe fallar");
		}
	}

	@Test
	public void unAdnDeUnHumanoNoMutanteIndicaQueNoEsMutante() {
		try {
			dadoQueElADNNoExisteEnLaBase();

			List<String> dnaMutant = Arrays.asList("TACA", "AgTT", "AATC", "AcGT");

			assertFalse(servicio.isMutant(dnaMutant));
		} catch (Exception ex) {
			fail("No debe fallar");
		}

	}

	private void dadoQueElADNNoExisteEnLaBase() {
		Mockito.when(repositorio.findByCadenaADN(Mockito.anyString())).thenReturn(null);
	}

	private void dadoQueElADNExisteEnLaBaseRetornaElAdnPersistidoIndicandoMutante(boolean mutante) {
		ADN adnConsultado = new ADN();
		adnConsultado.setMutante(mutante);
		Mockito.when(repositorio.findByCadenaADN(Mockito.anyString())).thenReturn(adnConsultado);

	}

}
