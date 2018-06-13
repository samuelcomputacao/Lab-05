package com.samuel.lab.controller;

import java.util.List;

import com.samuel.lab.model.Aposta;

public class ApostaController {
	
	private List<Aposta> apostas;

	
	public Aposta cadastrar(String apostador, int valor, boolean previsao) {
		Aposta aposta = new Aposta(apostador,valor,previsao);
		apostas.add(aposta);
		return aposta;
	}



}
