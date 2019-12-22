package mutante.modelo;

public class Estadisticas {

	private int mutantes;
	private int evaluados;
	
	public Estadisticas() {}
	public Estadisticas(int mutantes, int evaluados) {
		this.mutantes = mutantes;
		this.evaluados = evaluados;
	}
	
	public int getMutantes() {
		return mutantes;
	}
	public void setMutantes(int mutantes) {
		this.mutantes = mutantes;
	}
	public int getEvaluados() {
		return evaluados;
	}
	public void setEvaluados(int evaluados) {
		this.evaluados= evaluados;
	}
	
	public double getPorcentajeDeMutantes() {
		Double porcentaje = 0.0;
		if (evaluados != 0)
			porcentaje =(mutantes*1.0/evaluados);
		
		return porcentaje;
	}
	
	
}
