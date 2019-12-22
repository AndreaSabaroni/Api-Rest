package mutante.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mutante.modelo.Estadisticas;
import mutante.repositorios.RepositorioDeADN;

@Service
public class ServicioEstadisticas {

	private RepositorioDeADN repositorioDeAdn;

	
	@Autowired
	public void setRepositorioEstadisticas(RepositorioDeADN repositorioEstadisticas) {
		this.repositorioDeAdn = repositorioEstadisticas;
	}

	
	public Estadisticas obtenerEstadisticasDeMutantes() {
		
		List<Boolean> evaluaciones = repositorioDeAdn.findAllEvaluaciones();
		int mutantes = 
				evaluaciones.stream()
				.filter(mutanes -> mutanes == true)
				.collect(Collectors.toList()).size();
		Estadisticas estadisticas = new Estadisticas(mutantes, evaluaciones.size());
		
		return estadisticas;
	}

}
