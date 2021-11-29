package br.amandaflores.unisoma.calculasalario.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reajuste {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column
	private double novoSalario;
	
	@Column
	private double reajuste;
	
	@Column
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
