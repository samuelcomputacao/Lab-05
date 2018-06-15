package com.samuel.lab.exception;

@SuppressWarnings("serial")
public class CenarioJaEncerradoException extends RuntimeException {

	public CenarioJaEncerradoException() {
	super("O Cenário já foi encerrado");
	}

}
