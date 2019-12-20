package mutante.modelo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EstadisticasTest {

	@Test
	public void inicialmenteLaEstadisticasInformaCero() {

		Estadisticas estadisticas = new Estadisticas();

		assertEquals(0, estadisticas.getEvaluados());
		assertEquals(0, estadisticas.getMutantes());
		assertEquals(0, estadisticas.getPorcentajeDeMutantes(), 0);
	}

	@Test
	public void laEstadisticasInformanLosValoresEstablecidos() {

		Estadisticas estadisticas = new Estadisticas();
		estadisticas.setEvaluados(1);
		estadisticas.setMutantes(1);
		
		assertEquals(1, estadisticas.getEvaluados());
		assertEquals(1, estadisticas.getMutantes());
	}
	
	
	@Test
	public void laEstadisticasCalculaEnFormaCorrectaElPorcentaje() {

		Estadisticas estadisticas = new Estadisticas();
		estadisticas.setEvaluados(10);
		estadisticas.setMutantes(4);
		
		assertEquals(10, estadisticas.getEvaluados());
		assertEquals(4, estadisticas.getMutantes());
		assertEquals(0.4, estadisticas.getPorcentajeDeMutantes(), 0);
	}

}
