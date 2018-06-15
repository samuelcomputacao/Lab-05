package com.samuel.lab.exception;

@SuppressWarnings("serial")
public class CampoInvalidoException extends RuntimeException{
	
	public CampoInvalidoException(String msg) {
		super(msg);
	}
}
