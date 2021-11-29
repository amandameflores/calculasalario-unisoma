package br.amandaflores.unisoma.calculasalario.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.amandaflores.unisoma.calculasalario.model.entities.Reajuste;

public interface ReajusteRepository extends JpaRepository<Reajuste, Integer> {

	//Reajuste findByCpf(String cpf);

}
