package br.amandaflores.unisoma.calculasalario.model.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FuncionarioServiceTest {

	@Test
	void test_calcularAumento_15perc() {
		FuncionarioService funcionario= new FuncionarioService ();
		
		double[] entradas= {0.0d, 100.01, 200.0, 343.13, 400};
		
		for (double entrada : entradas) {
			double aumento= funcionario.calcularAumento(entrada);
			
			assertEquals(0.15,  aumento);
		}
		
	}

	@Test
	void test_calcularAumento_125perc() {
		FuncionarioService funcionario= new FuncionarioService ();
		
		double[] entradas= {400.01d, 502.41, 600.0, 745.83, 800};
		
		for (double entrada : entradas) {
			double aumento= funcionario.calcularAumento(entrada);
			
			assertEquals(0.12,  aumento);
		}
		
	}
	
	@Test
	void test_calcularAumento_10perc() {
		FuncionarioService funcionario= new FuncionarioService ();
		
		double[] entradas= {800.01d, 954, 1000.0, 1143.13, 1200};
		
		for (double entrada : entradas) {
			double aumento= funcionario.calcularAumento(entrada);
			
			assertEquals(0.10,  aumento);
		}
		
	}
	
	@Test
	void test_calcularAumento_07perc() {
		FuncionarioService funcionario= new FuncionarioService ();
		
		double[] entradas= {1200.01d, 1300, 1700, 1943.65, 2000};
		
		for (double entrada : entradas) {
			double aumento= funcionario.calcularAumento(entrada);
			
			assertEquals(0.07,  aumento);
		}
		
	}
	
	@Test
	void test_calcularAumento_04perc() {
		FuncionarioService funcionario= new FuncionarioService ();
		
		double[] entradas= {2000.01, 3000, 5000};
		
		for (double entrada : entradas) {
			double aumento= funcionario.calcularAumento(entrada);
			
			assertEquals(0.04,  aumento);
		}
		
	}
	
	@Test
	void test_calcularTxImposto_isento() {
		FuncionarioService funcionario= new FuncionarioService ();
		
		double[] entradas= {0, 1000, 2000};
		
		for (double entrada : entradas) {
			double imposto= funcionario.calcularTxImposto(entrada);
			
			assertEquals(0.0,  imposto);
		}
		
	}
	
	@Test
	void test_calcularTxImposto_8porc() {
		FuncionarioService funcionario= new FuncionarioService ();
		
		double[] entradas= {2000.01, 3000};
		
		for (double entrada : entradas) {
			double imposto= funcionario.calcularTxImposto(entrada);
			
			assertEquals(0.08,  imposto);
		}
		
	}
	
	@Test
	void test_calcularTxImposto_18porc() {
		FuncionarioService funcionario= new FuncionarioService ();
		
		double[] entradas= {3000.01, 4500};
		
		for (double entrada : entradas) {
			double imposto= funcionario.calcularTxImposto(entrada);
			
			assertEquals(0.18,  imposto);
		}
		
	}
	
	@Test
	void test_calcularTxImposto_28porc() {
		FuncionarioService funcionario= new FuncionarioService ();
		
		double[] entradas= {4500.01, 5000};
		
		for (double entrada : entradas) {
			double imposto= funcionario.calcularTxImposto(entrada);
			
			assertEquals(0.28,  imposto);
		}
		
	}
	
	@Test
	void test_calcularValorImposto() {
		FuncionarioService funcionario= new FuncionarioService ();
		
		double[] entradas= {3002};
		double[] saidas= {80.36};
		
		for (int i= 0; i < entradas.length; i++) {
			double imposto= funcionario.calcularValorImposto(entradas[i]);
			
			assertEquals(saidas[i],  imposto);
		}
		
	}
	
}
