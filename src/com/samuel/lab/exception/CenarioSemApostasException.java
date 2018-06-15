package com.samuel.lab.exception;

@SuppressWarnings("serial")
public class CenarioSemApostasException extends RuntimeException {

	public CenarioSemApostasException() {
		super("O Cenário não possui apostas cadastradas");
	}
}
