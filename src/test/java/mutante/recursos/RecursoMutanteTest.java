package mutante.recursos;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import mutante.servicio.ServicioMutante;

@RunWith(MockitoJUnitRunner.class)
public class RecursoMutanteTest {

	@InjectMocks
	RecursoMutante recurso;

	@Mock
	public ServicioMutante servicio;
	
	
	@Test
	public void alHacerUnPostAMutanteDevuelveOK() throws Exception {

		String[] dnaMutant = { "TAAAAC", "AAAAGt", "ATAAAA", "TCAAAA", "CCAAAA" };
		Mockito.when(servicio.isMutant(dnaMutant)).thenReturn(true);

		ResponseEntity<?> respuesta = recurso.determinarSiEsMutante(dnaMutant);

		assertThat(respuesta.getStatusCode(), is(HttpStatus.OK));
	}

}
