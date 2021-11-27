package br.amandaflores.unisoma.calculasalario.model.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.amandaflores.unisoma.calculasalario.model.Imposto;
import br.amandaflores.unisoma.calculasalario.model.dto.FuncionarioDto;
import br.amandaflores.unisoma.calculasalario.model.entities.Funcionario;
import br.amandaflores.unisoma.calculasalario.model.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	public boolean cadastraFuncionario(FuncionarioDto funcionarioDto) {
		Funcionario funcionario = funcionarioDto.converter();
		if (funcionario == null) {
			return false;
		}
		
		funcionarioRepository.save(funcionario);
		return true;
	}

	public FuncionarioDto aumentaSalario(String cpf) {
		Funcionario funcionario = funcionarioRepository.findByCpf(cpf);
		double salario = funcionario.getSalario();
		
		Double aumento= calcularAumento(salario);
		aumento+= 1;
		funcionario.setSalario(salario*aumento);

		funcionario = funcionarioRepository.saveAndFlush(funcionario);

		return new FuncionarioDto(funcionario);
	}

	public double obterImposto (String cpf) {
		Funcionario funcionario = funcionarioRepository.findByCpf(cpf);
		if (funcionario == null) {
			return -1;
		}
		
		double salario = funcionario.getSalario();
		return calcularTxImposto(salario);
	}
	
	public Imposto buscarTxImposto(double salario) {
		for (Imposto objImposto: Imposto.values()) {
			
			if ( (salario >= objImposto.getLimiteMinimo()) && (salario <= objImposto.getLimiteMaximo()) ) {
				return objImposto;
			}
			
		}
		
		return null;
	}
	
	public double calcularTxImposto(double salario) {

		Imposto imposto= buscarTxImposto(salario); 
//		
//		if (salario <= 2000) {
//			imposto = 0.0d;
//		} else if (salario <= 3000) {
//			imposto = 0.08d;
//		} else if (salario <= 4500) {
//			imposto = 0.18d;
//		} else {
//			imposto = 0.28d;
//		}

		if (imposto != null)
			return imposto.getTaxa();
		
		return 0;
	}
	
	public Double calcularAumento (Double salario) {

		Double aumento= 0.0d;

		if (salario <= 400) {
			aumento= 0.15;
		}
		else if (salario <= 800) {
			aumento= 0.12;
		}
		else if (salario <= 1200) {
			aumento= 0.10;
		}
		else if (salario <= 2000) {
			aumento= 0.07;
		}
		else {
			aumento= 0.04;
		}

		return aumento;	
	}


	public List<FuncionarioDto> listaFuncionario() {
		List<Funcionario> funcionarios = funcionarioRepository.findAll();

		if (funcionarios != null) {
			List<FuncionarioDto> funcionariosDto = new ArrayList<>();
			for (Funcionario func : funcionarios) {
				funcionariosDto.add(new FuncionarioDto(func));
			}
			return funcionariosDto;
		}
		return null;
	}

	public FuncionarioDto obterFuncionario(String cpf) {
		Funcionario funcionario = funcionarioRepository.findByCpf(cpf);
		
		if (funcionario != null) {

			return new FuncionarioDto(funcionario);

		}
		return null;
	}

	public double calcularValorImposto(double salario) {
		double imposto= 0;
		
		//List<Double> minimos= Imposto.getListaMinimos ();
		List<Double> maximos= Imposto.getListaMaximos ();
				
		double resta= salario;
		
		for (int i= maximos.size()-1; i >= 0; i--) {
		//for (Double maximo: maximos) {
			
			Double maximo= maximos.get(i);
			
			Imposto imp= Imposto.getImpostoPeloMaximo(maximo);
			if (imp == null) {
				continue;
			}
			
			double minimo= imp.getLimiteReferencia();
			
			double parcial= resta - minimo;
			if (parcial <= 0) {
				continue;
			}
			
			imposto += (parcial * imp.getTaxa());
						
			resta-= parcial;
			if (resta <= 0) {
				break;
			}
				
		}
		
		
		return imposto;
	}
}
