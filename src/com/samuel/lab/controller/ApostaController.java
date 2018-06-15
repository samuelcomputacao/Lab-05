package com.samuel.lab.controller;

import java.util.List;

import com.samuel.lab.exception.CampoInvalidoException;
import com.samuel.lab.model.Aposta;

public class ApostaController {
	
	private List<Aposta> apostas;

	
	public Aposta cadastrar(String apostador, int valor, boolean previsao) {
		if(apostador == null || apostador.length()==0) throw new CampoInvalidoException("Campo Apostador Vazio");
		if(valor <=0) throw new CampoInvalidoException("Valor da aposta inconcistente");
		Aposta aposta = new Aposta(apostador,valor,previsao);
		apostas.add(aposta);
		return aposta;
	}



}
