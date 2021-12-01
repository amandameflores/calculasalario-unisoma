package br.amandaflores.unisoma.calculasalario.model.dto;

public class ReajusteDto {
	private String cpf;
	private double novoSalario;
	private double reajuste;
	private double indicePercentual;
	
	public double getNovoSalario() {
		return novoSalario;
	}
	public void setNovoSalario(double novoSalario) {
		this.novoSalario = novoSalario;
	}
	public double getReajuste() {
		return reajuste;
	}
	public void setReajuste(double reajuste) {
		this.reajuste = reajuste;
	}
	public double getIndicePercentual() {
		return indicePercentual;
	}
	public void setIndicePercentual(double indicePercentual) {
		this.indicePercentual = indicePercentual;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
}
