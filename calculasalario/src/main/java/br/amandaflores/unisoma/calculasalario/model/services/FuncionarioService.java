package br.amandaflores.unisoma.calculasalario.model.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.amandaflores.unisoma.calculasalario.model.Imposto;
import br.amandaflores.unisoma.calculasalario.model.dto.FuncionarioDto;
import br.amandaflores.unisoma.calculasalario.model.dto.ImpostoDto;
import br.amandaflores.unisoma.calculasalario.model.dto.ReajusteDto;
import br.amandaflores.unisoma.calculasalario.model.entities.Funcionario;
import br.amandaflores.unisoma.calculasalario.model.entities.Reajuste;
import br.amandaflores.unisoma.calculasalario.model.repository.FuncionarioRepository;
import br.amandaflores.unisoma.calculasalario.model.repository.ReajusteRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private ReajusteRepository reajusteRepository;
	
	public boolean cadastraFuncionario(FuncionarioDto funcionarioDto) {
		Funcionario funcionario = funcionarioDto.converter();
		if (funcionario == null) {
			return false;
		}
		
		funcionarioRepository.save(funcionario);
		return true;
	}

	public ReajusteDto aumentaSalario(String cpf) {
		Funcionario funcionario = funcionarioRepository.findByCpf(cpf);
		double salario = funcionario.getSalario();
		
		Double indice= calcularAumento(salario);
		Double aumento= indice + 1;
		
		double salarioNovo= salario*aumento;
		funcionario.setSalario(salarioNovo);

		Reajuste reajuste= new Reajuste();
		reajuste.setIndicePercentual(indice*100);
		reajuste.setNovoSalario(salarioNovo);
		reajuste.setReajuste(salarioNovo-salario);
		funcionario.setReajuste(reajuste);
		
		reajusteRepository.saveAndFlush(reajuste);
		
		funcionario = funcionarioRepository.saveAndFlush(funcionario);

		ReajusteDto aumentoSal= new ReajusteDto ();
		aumentoSal.setNovoSalario(salarioNovo);
		aumentoSal.setReajuste(salarioNovo-salario);
		aumentoSal.setIndicePercentual(indice*100);
		
		return aumentoSal;
	}

	public ImpostoDto obterImposto (String cpf) {
		Funcionario funcionario = funcionarioRepository.findByCpf(cpf);
		if (funcionario == null) {
			return null;
		}
		
		double salario = funcionario.getSalario();
		
		ImpostoDto impostoDto= new ImpostoDto ();
		
		Imposto imposto= buscarTxImposto(salario);
		if (imposto != null) {
			if (imposto.equals(Imposto.ISENTO))
				impostoDto.setImposto(imposto.getNome());
			else
				impostoDto.setImposto(String.format("Imposto: R$ %.2f", calcularValorImposto (salario)));
		}
		else
			return null;
		
		impostoDto.setCpf(cpf);
		
		return impostoDto;
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

	public ReajusteDto obterReajuste(String cpf) {
		
		Funcionario funcionario= funcionarioRepository.findByCpf(cpf);
		if ( (funcionario == null) || (funcionario.getReajuste() == null) )
			return null;
		
		ReajusteDto reajuste= new ReajusteDto ();
		reajuste.setCpf(cpf);
		reajuste.setIndicePercentual(funcionario.getReajuste().getIndicePercentual());
		reajuste.setNovoSalario(funcionario.getReajuste().getNovoSalario());
		reajuste.setReajuste(funcionario.getReajuste().getReajuste());
		
		return reajuste;
	}
}
