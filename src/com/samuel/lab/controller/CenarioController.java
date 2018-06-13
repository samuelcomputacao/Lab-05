package com.samuel.lab.controller;

import java.util.HashMap;
import java.util.Iterator;

import com.samuel.lab.model.Aposta;
import com.samuel.lab.model.Cenario;

public class CenarioController {

	private int idBase;
	private HashMap<Integer, Cenario> cenarios;

	public CenarioController() {
		this.idBase = 1;
		this.cenarios = new HashMap<>();
	}

	public int cadastrar(String descricao) {
		Cenario cenario = new Cenario(idBase++, descricao);
		this.cenarios.put(cenario.getId(), cenario);
		return cenario.getId();
	}

	public String exibirCenario(int id) {
		return this.cenarios.get(id).toString();
	}

	public String exibirCenarios() {
		String retorno = "";
		Iterator<Cenario> iterator = this.cenarios.values().iterator();

		while (iterator.hasNext()) {
			if (retorno.isEmpty()) {
				retorno = iterator.next().toString();
			} else {
				retorno += System.lineSeparator() + iterator.next().toString();
			}
		}
		return retorno;
	}

	public void apostar(int idCenario, Aposta aposta) {
		Cenario cenario = cenarios.get(idCenario);
		if (cenario != null) {
			cenario.apostar(aposta);
		}
	}

	public int valorTotal(int idCenario) {
		Cenario cenario = this.cenarios.get(idCenario);
		int resultado = 0;
		Iterator<Aposta> iterator = cenario.getApostas().iterator();
		while (iterator.hasNext()) {
			resultado += iterator.next().getValor();
		}
		return resultado;
	}

	public int totalApostas(int idCenario) {
		return this.cenarios.get(idCenario).getApostas().size();
	}

	public String exibirApostas(int idCenario) {
		String retorno = "";
		Cenario cenario = this.cenarios.get(idCenario);
		for(Aposta aposta : cenario.getApostas()) {
			if(retorno.isEmpty()) {
				retorno = aposta.toString();
			}else {
				retorno += System.lineSeparator()  + aposta.toString();
			}
		}
		return retorno;
	}

	public void fecharAposta(int idCenario, boolean ocorreu) {
		Cenario cenario  = this.cenarios.get(idCenario);
		cenario.ocorrer(ocorreu);
	}

	public int getCaixa(int idCenario,double taxa) {
		
		Cenario cenario = this.cenarios.get(idCenario);
		return cenario.calculaCaixa(taxa);
		
		
	}

	public int getTotalArrecardado(int idCenario) {
		Cenario cenario  = this.cenarios.get(idCenario);
		return cenario.getCaixa();
	}

}
