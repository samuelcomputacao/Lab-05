package com.samuel.lab.controller;

import java.util.HashMap;
import java.util.Iterator;

import com.samuel.lab.model.Aposta;
import com.samuel.lab.model.Cenario;

public class CenarioController {
	
	private int idBase;
	private HashMap<Integer,Cenario> cenarios;
	
	public CenarioController() {
		this.idBase = 1;
		this.cenarios = new HashMap<>();
	}

	public int cadastrar( String descricao) {
		Cenario cenario = new Cenario(idBase++, descricao);
		this.cenarios.put(cenario.getId(),cenario);
		return cenario.getId();
	}

	public String exibirCenario(int id) {
		return this.cenarios.get(id).toString();
	}

	public String exibirCenarios() {
		String retorno = "";
		Iterator<Cenario> iterator = this.cenarios.values().iterator();
		
		while(iterator.hasNext()) {
			if(retorno.isEmpty()) {
				retorno = iterator.next().toString();
			}else {
				retorno += System.lineSeparator() + iterator.next().toString();
			}
		}
		return retorno;
	}

	public void apostar(int idCenario,Aposta aposta) {
		Cenario cenario = cenarios.get(idCenario);
		if(cenario != null) { 
			cenario.apostar(aposta);
		}
	}

	

}
