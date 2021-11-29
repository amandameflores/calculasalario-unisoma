package br.amandaflores.unisoma.calculasalario.model;

import java.util.ArrayList;
import java.util.List;

public enum Imposto {

	ISENTO ("Isento", 0, 0, 0, 2000),
	TAXA_8 ("8%", 0.08, 2000, 2000.01, 3000),
	TAXA_18 ("18%", 0.18, 3000, 3000.01, 4500),	
	TAXA_28 ("28%", 0.28, 4500, 4500.01, Double.MAX_VALUE);
		
	private String nome;
	private double taxa;
	private double limiteReferencia;
	private double limiteMinimo;
	private double limiteMaximo;
	
	private Imposto(String nome, double taxa, double limiteReferencia, double limMin, double limMax) {
		this.nome= nome;
		this.taxa= taxa;
		this.limiteReferencia= limiteReferencia;
		this.limiteMinimo= limMin;
		this.limiteMaximo= limMax;
	}

	public String getNome() {
		return nome;
	}

	public double getTaxa() {
		return taxa;
	}

	public double getLimiteMinimo() {
		return limiteMinimo;
	}

	public double getLimiteMaximo() {
		return limiteMaximo;
	}

	public static List<Double> getListaMinimos() {
		List<Double> lista= new ArrayList<>();
		
		for (Imposto imp : values()) {
			lista.add(imp.getLimiteMinimo());
		}
		
		lista.sort(null);
		
		return lista;
	}
	
	public double getLimiteReferencia() {
		return limiteReferencia;
	}

	public static List<Double> getListaMaximos() {
		List<Double> lista= new ArrayList<>();
		
		for (Imposto imp : values()) {
			lista.add(imp.getLimiteMaximo());
		}
		
		lista.sort(null);
		
		return lista;
	}
	
	public static Imposto getImpostoPeloMaximo(double maximo) {
				
		for (Imposto imp : values()) {
			if (imp.getLimiteMaximo() == maximo)
				return imp;
		}
				
		return null;
	}

}
