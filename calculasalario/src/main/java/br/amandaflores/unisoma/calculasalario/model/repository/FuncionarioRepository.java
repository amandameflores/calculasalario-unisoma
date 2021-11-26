package br.amandaflores.unisoma.calculasalario.model.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;

import br.amandaflores.unisoma.calculasalario.model.entities.Funcionario;


public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

	Funcionario findByCpf(String cpf);
	
}
