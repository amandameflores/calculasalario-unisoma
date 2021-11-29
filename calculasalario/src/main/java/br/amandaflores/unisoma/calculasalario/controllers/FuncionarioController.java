package br.amandaflores.unisoma.calculasalario.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.amandaflores.unisoma.calculasalario.model.dto.FuncionarioDto;
import br.amandaflores.unisoma.calculasalario.model.dto.ImpostoDto;
import br.amandaflores.unisoma.calculasalario.model.dto.ReajusteDto;
import br.amandaflores.unisoma.calculasalario.model.services.FuncionarioService;

@RestController
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;

	/*
	 * 
curl -X GET -i 'http://127.0.0.1:8080/api/funcionario/000.000.000-00'
	 */
	@GetMapping("/api/funcionario/{cpf}")
	public String listar(@PathVariable String cpf) {
		
		ReajusteDto reajuste= funcionarioService.obterReajuste(cpf);
		if (reajuste == null)			
			return "Não houve reajuste";
		
		return String.format("CPF: %s\nNovo salario: %f\nReajuste ganho: %f\nEm percentual: %.2f %%", cpf, 
				reajuste.getNovoSalario(), reajuste.getReajuste(), reajuste.getIndicePercentual());
		
		/*
		CPF: 000.000.000-00
		Novo salario: 460.00
		Reajuste ganho: 60.00
		Em percentual: 15 %
		*/
	}

	/*
	 * TESTE:
	 * 
curl -X POST -H 'Content-Type: application/json' -i 'http://127.0.0.1:8080/api/funcionario' --data '{
"nome": "aaa",    
"cpf": "000.000.000-00",
"nascimento": "01/01/1981",
"telefone": "2222",
"endereco": "xpto",
"salario": 200.0
}'
	 */
	@PostMapping("/api/funcionario")
	public boolean cadastrar(@RequestBody FuncionarioDto funcionarioDto) {
		
		return funcionarioService.cadastraFuncionario(funcionarioDto);
	}

	/*
	 * 
curl -X GET -H 'Content-Type: application/json' -i 'http://127.0.0.1:8080/api/aumento/000.000.000-00'
	 */
	@GetMapping("/api/aumento/{cpf}")
	public ReajusteDto calcularAumentoSalario(@PathVariable String cpf) {
		
		return funcionarioService.aumentaSalario(cpf);
	}
	
	/*
	 * 
curl -X GET -i 'http://127.0.0.1:8080/api/irpf/01' 
	 */
	@GetMapping("/api/irpf/{cpf}")
	public String calcularIrpf(@PathVariable String cpf) {
		
		ImpostoDto imposto= funcionarioService.obterImposto (cpf);
		if (imposto == null)
			return "Não encontrado";
		
		return String.format("CPF: %s\n%s", cpf, imposto.getImposto());
	}
}