package br.amandaflores.unisoma.calculasalario.model.dto;

import br.amandaflores.unisoma.calculasalario.model.entities.Funcionario;

public class FuncionarioDto {

//    private int id;

    private String nome;
    
    private String cpf;

    private String nascimento;
    
    private String telefone;
    
    private String endereco;
    
    private double salario;
    
    public FuncionarioDto() {
    	
    }

    public FuncionarioDto(Funcionario funcionario) {
        this.nome = funcionario.getNome();
        this.cpf = funcionario.getCpf();
        this.nascimento = funcionario.getNascimento();
        this.telefone = funcionario.getTelefone();
        this.endereco = funcionario.getEndereco();
        this.salario = funcionario.getSalario();
    }

    public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Funcionario converter() {
		Funcionario funcionario = new Funcionario();
		funcionario.setCpf(this.cpf);
		funcionario.setNome(this.nome);
		funcionario.setNascimento(this.nascimento);
		funcionario.setEndereco(this.endereco);
		funcionario.setTelefone(this.telefone);
		funcionario.setSalario(this.salario);
		
		return funcionario;
	}

}

