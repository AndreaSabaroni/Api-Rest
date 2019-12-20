package mutante.mensaje;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseEstadisticas {
	private int mutantes;
	private int evaluados;
	private double porcentajeDeMutante;
	
	@JsonProperty("count_mutant_dna")
	public int getMutantes() {
		return mutantes;
	}
	public void setMutantes(int mutantes) {
		this.mutantes = mutantes;
	}
	
	@JsonProperty("count_human_dna")
	public int getEvaluados() {
		return evaluados;
	}
	public void setEvaluados(int evaluados) {
		this.evaluados = evaluados;
	}
	
	@JsonProperty("ratio")
	public double getPorcentajeDeMutante() {
		return porcentajeDeMutante;
	}
	public void setPorcentajeDeMutante(double porcentajeDeMutante) {
		this.porcentajeDeMutante = porcentajeDeMutante;
	}
}
