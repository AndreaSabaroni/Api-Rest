package mutante.recursos;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mutante.servicio.ServicioMutante;



@RestController
public class RecursoMutante {
	public static final String PATH = "v1.0" + "/mutantes";
	
	private ServicioMutante servicioMutante;

    @PostMapping("/mutantes")
	public ResponseEntity<?> determinarSiEsMutante(@NotNull @RequestBody String[] dnaMutant) {

		try {
			if (servicioMutante.isMutant(dnaMutant))
				return ResponseEntity.ok().build();

			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}
    

	@Autowired
	public void setServicioMutante(ServicioMutante servicioMutante) {

		this.servicioMutante = servicioMutante;
	}
}
