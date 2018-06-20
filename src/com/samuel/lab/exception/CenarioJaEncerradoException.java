package com.samuel.lab.exception;

@SuppressWarnings("serial")
public class CenarioJaEncerradoException extends RuntimeException {

	public CenarioJaEncerradoException() {
	super("Erro ao fechar aposta: Cenario ja esta fechado");
	}

}
