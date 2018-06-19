package com.samuel.lab.exception;

@SuppressWarnings("serial")
public class CenarioNaoCadastradoException extends RuntimeException {
	
	public CenarioNaoCadastradoException() {
		super("Erro na consulta de cenario: Cenario nao cadastrado");
	}

}
