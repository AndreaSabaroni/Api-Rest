package mutante.recursos;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import mutante.mensaje.ResponseEstadisticas;
import mutante.modelo.Estadisticas;
import mutante.servicio.ServicioEstadisticas;

@RunWith(MockitoJUnitRunner.class)
public class RecursoEstadisticasTest {

	@InjectMocks
	RecursoEstadisticas recurso;

	@Mock
	public ServicioEstadisticas servicio;

	@Test
	public void cuandoNuncaSeEvaluaronAdnParaMutantesEntoncesElServicioDevuelveOKConEstadisticasEnCero()
			throws Exception {

		Mockito.when(servicio.obtenerEstadisticasDeMutantes()).thenReturn(new Estadisticas());

		ResponseEntity<ResponseEstadisticas> respuesta = recurso.obtienerEstadisticas();

		assertThat(respuesta.getStatusCode(), is(HttpStatus.OK));
		assertEquals(0, respuesta.getBody().getEvaluados());
		assertEquals(0, respuesta.getBody().getMutantes());
		assertEquals(0, respuesta.getBody().getPorcentajeDeMutante(), 0);
	}

	@Test
	public void cuandoSeEvaluaronAdnParaMutantesEntoncesElServicioDevuelveOKConEstadisticasDeEvaluaciones()
			throws Exception {

		Estadisticas estadisticas = new Estadisticas();
		estadisticas.setEvaluados(10);
		estadisticas.setMutantes(4);
		Mockito.when(servicio.obtenerEstadisticasDeMutantes()).thenReturn(estadisticas);

		ResponseEntity<ResponseEstadisticas> respuesta = recurso.obtienerEstadisticas();

		assertThat(respuesta.getStatusCode(), is(HttpStatus.OK));
		assertEquals(10, respuesta.getBody().getEvaluados());
		assertEquals(4, respuesta.getBody().getMutantes());
		assertEquals(0.4, respuesta.getBody().getPorcentajeDeMutante(), 0);
	}

	
}
