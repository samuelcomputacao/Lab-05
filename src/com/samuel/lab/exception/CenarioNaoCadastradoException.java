package com.samuel.lab.exception;

@SuppressWarnings("serial")
public class CenarioNaoCadastradoException extends RuntimeException {
	
	public CenarioNaoCadastradoException() {
		super("Cenário não cadastrado");
	}

}
