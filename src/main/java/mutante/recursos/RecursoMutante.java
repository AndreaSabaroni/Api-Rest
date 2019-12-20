package mutante.recursos;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Estadisticas> obtienerEstadisticas() {

		Estadisticas estadisticas = servicioMutante.obtenerEstadisticasDeMutantes();
		return ResponseEntity.ok(estadisticas);
	}

	@Autowired
	public void setServicioMutante(ServicioMutante servicioMutante) {

		this.servicioMutante = servicioMutante;
	}
}
