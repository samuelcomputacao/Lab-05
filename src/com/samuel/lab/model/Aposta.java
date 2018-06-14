package com.samuel.lab.model;

import com.samuel.lab.exception.CampoVazioException;

public class Aposta {
	
	private String apostador;
	private int valor;
	private boolean previsao;
	
	
	public Aposta(String apostador, int valor, boolean previsao) {
		if(apostador== null || apostador.isEmpty()) throw new CampoVazioException("Campo apostador vazio");
		this.apostador = apostador;
		this.valor = valor;
		this.previsao = previsao;
	}


	public int getValor() {
		return this.valor;
	}


	public boolean isAcontece() {
		return this.previsao;
	}
	
	@Override
	public String toString() {
		return String.format("%s - R$%.2f - %s", this.apostador,(this.valor/100),this.previsao?"VAI ACONTECER":"N VAI ACONTECER");
	}
}
