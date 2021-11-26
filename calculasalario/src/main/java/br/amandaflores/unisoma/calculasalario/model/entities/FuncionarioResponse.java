package br.amandaflores.unisoma.calculasalario.model.entities;


public class FuncionarioResponse {

    private Funcionario client;
    private String message;

    public FuncionarioResponse(Funcionario client, String message) {
        this.client = client;
        this.message = message;
    }

    public FuncionarioResponse() {
    }

    public Funcionario getClient() {
        return client;
    }

    public void setClient(Funcionario client) {
        this.client = client;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}