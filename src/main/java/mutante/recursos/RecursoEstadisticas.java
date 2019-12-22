package mutante.recursos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import mutante.mensaje.ResponseEstadisticas;
import mutante.modelo.Estadisticas;
import mutante.servicio.ServicioEstadisticas;

@RestController
public class RecursoEstadisticas {

	private ServicioEstadisticas servicioEstadisticas;

	@GetMapping(path = "/stats", produces = "application/json")
	public ResponseEntity<ResponseEstadisticas> obtienerEstadisticas() {

		Estadisticas estadisticas = servicioEstadisticas.obtenerEstadisticasDeMutantes();
		ResponseEstadisticas response = transformar(estadisticas);
		return ResponseEntity.ok(response);
	}

	private ResponseEstadisticas transformar(Estadisticas estadisticas) {
		ResponseEstadisticas response = new ResponseEstadisticas();
		response.setEvaluados(estadisticas.getEvaluados());
		response.setMutantes(estadisticas.getMutantes());
		response.setPorcentajeDeMutante(estadisticas.getPorcentajeDeMutantes());
		
		return response;
	}

	@Autowired
	public void setServicioEstadisticas(ServicioEstadisticas servicioEstadisticas) {

		this.servicioEstadisticas = servicioEstadisticas;
	}
}
