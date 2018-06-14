package com.samuel.lab.exception;

@SuppressWarnings("serial")
public class CenarioNaoEncerradoException extends RuntimeException {
	
	public CenarioNaoEncerradoException() {
		super("O cenário n]ao foi encerrado");
	}

}
