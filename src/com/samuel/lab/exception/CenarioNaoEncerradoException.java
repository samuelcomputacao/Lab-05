package com.samuel.lab.exception;

@SuppressWarnings("serial")
public class CenarioNaoEncerradoException extends RuntimeException {
	
	public CenarioNaoEncerradoException() {
		super("Erro na consulta do total de rateio do cenario: Cenario ainda esta aberto");
	}

}
