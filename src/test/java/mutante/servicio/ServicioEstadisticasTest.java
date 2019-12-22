package mutante.servicio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import mutante.modelo.Estadisticas;
import mutante.repositorios.RepositorioDeADN;

@RunWith(MockitoJUnitRunner.class)
public class ServicioEstadisticasTest {

	@InjectMocks
	ServicioEstadisticas servicio;

	@Mock
	RepositorioDeADN repositorio;

	@Test
	public void obtenerEstadisticasRetornaLasEstadísticasCalculadasCorrectamente() {

		int mutantes = 1;
		int evaluados = 4;
		List<Boolean> estadisticasPreExistentes = Arrays.asList(true, false, false, false);

		Mockito.when(repositorio.findAllEvaluaciones()).thenReturn(estadisticasPreExistentes);

		Estadisticas estadisticas = servicio.obtenerEstadisticasDeMutantes();

		assertNotNull(estadisticas);
		assertEquals(evaluados, estadisticas.getEvaluados());
		assertEquals(mutantes, estadisticas.getMutantes());
		assertEquals(0.25, estadisticas.getPorcentajeDeMutantes(), 0);
	}

}
