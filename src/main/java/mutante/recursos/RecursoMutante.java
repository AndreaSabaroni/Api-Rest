package mutante.recursos;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mutante.mensaje.ResponseEstadisticas;
import mutante.modelo.Estadisticas;
import mutante.request.RequestADN;
import mutante.servicio.ServicioMutante;

@RestController
public class RecursoMutante {

	private ServicioMutante servicioMutante;

	@PostMapping(path = "/mutant", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> determinarSiEsMutante(@RequestBody @Valid RequestADN dnaMutant) {

		if (servicioMutante.isMutant(dnaMutant.getAdn()))
			return ResponseEntity.ok().build();

		return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}

	@GetMapping(path = "/stats", produces = "application/json")
	public ResponseEntity<ResponseEstadisticas> obtienerEstadisticas() {

		Estadisticas estadisticas = servicioMutante.obtenerEstadisticasDeMutantes();
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
	public void setServicioMutante(ServicioMutante servicioMutante) {

		this.servicioMutante = servicioMutante;
	}
}
