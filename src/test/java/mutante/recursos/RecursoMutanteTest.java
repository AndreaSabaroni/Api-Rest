package mutante.recursos;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import mutante.request.RequestADN;
import mutante.servicio.ServicioMutante;

@RunWith(MockitoJUnitRunner.class)
public class RecursoMutanteTest {

	@InjectMocks
	RecursoMutante recurso;

	@Mock
	public ServicioMutante servicio;

	@Test
	public void alHacerUnPostAMutanteDevuelveOK() throws Exception {

		List<String> dnaMutant = Arrays.asList("TAAAAC", "AAAAGt", "ATAAAA", "TCAAAA", "CCAAAA");
		RequestADN request = new RequestADN();
		request.setAdn(dnaMutant);

		Mockito.when(servicio.isMutant(dnaMutant)).thenReturn(true);

		ResponseEntity<?> respuesta = recurso.determinarSiEsMutante(request);

		assertThat(respuesta.getStatusCode(), is(HttpStatus.OK));
	}

	@Test
	public void alHacerUnPostAMutanteConAdnNoMutanteDevuelve403() throws Exception {

		List<String> dnaMutant = Arrays.asList("TAAAAC", "gtAAGt", "ATGCC", "TCCAAA", "CCTAAA");
		RequestADN request = new RequestADN();
		request.setAdn(dnaMutant);

		Mockito.when(servicio.isMutant(dnaMutant)).thenReturn(false);

		ResponseEntity<?> respuesta = recurso.determinarSiEsMutante(request);

		assertThat(respuesta.getStatusCode(), is(HttpStatus.FORBIDDEN));
	}

}
