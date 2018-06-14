package com.samuel.lab.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.samuel.lab.exception.CenarioNaoCadastradoException;
import com.samuel.lab.exception.CenarioNaoEncerradoException;
import com.samuel.lab.model.Aposta;
import com.samuel.lab.model.Cenario;

public class CenarioController {
	
	private int caixa;
	private double taxa;
	private int idBase;
	private HashMap<Integer, Cenario> cenarios;
	

	public CenarioController(int caixa,double taxa) {
		this.idBase = 1;
		this.caixa = caixa;
		this.taxa = taxa;
		this.cenarios = new HashMap<>();
	}

	public int cadastrar(String descricao) {
		Cenario cenario = new Cenario(idBase++, descricao);
		this.cenarios.put(cenario.getId(), cenario);
		return cenario.getId();
	}

	public String exibirCenario(int id) {
		if(!cenarios.containsKey(id)) {
			throw new CenarioNaoCadastradoException();
		}
		return this.cenarios.get(id).toString();
	}

	public String exibirCenarios() {
		String retorno = "";
		List<Integer> keysOrdenadas = setToList();
		Cenario cenario;
		for(Integer key : keysOrdenadas) {
			cenario = this.cenarios.get(key);
			if (retorno.isEmpty()) {
				retorno = cenario.toString();
			} else {
				retorno += System.lineSeparator() + cenario.toString();
			}
		}
		return retorno;
	}

	private List<Integer> setToList() {
		Set<Integer> keys = this.cenarios.keySet();
		
		List<Integer> keysOrdenadas = new ArrayList<Integer>();
		for(Integer key : keys) {
			keysOrdenadas.add(key);
		}
		Collections.sort(keysOrdenadas);
		return keysOrdenadas;
	}

	public void apostar(int idCenario, String apostador, int valor, boolean previsao) {
		if(!this.cenarios.containsKey(idCenario)) throw new CenarioNaoCadastradoException();
		Cenario cenario = cenarios.get(idCenario);
		//Aposta aposta = apostaController.cadastrar(apostador, valor, previsao);
		Aposta aposta = new Aposta(apostador,valor,previsao);
		cenario.apostar(aposta);
		
	}

	public int valorTotal(int idCenario) {
		if(!this.cenarios.containsKey(idCenario)) throw new CenarioNaoCadastradoException();
		Cenario cenario = this.cenarios.get(idCenario);
		return cenario.valorTotalDeApostas();
	}

	public int totalApostas(int idCenario) {
		if(!this.cenarios.containsKey(idCenario)) throw new CenarioNaoCadastradoException();
		return this.cenarios.get(idCenario).getApostas().size();
	}

	public String exibirApostas(int idCenario) {
		if(!this.cenarios.containsKey(idCenario)) throw new CenarioNaoCadastradoException();
		Cenario cenario = this.cenarios.get(idCenario);
		return cenario.exibiApostas();
	}

	public void fecharAposta(int idCenario, boolean ocorreu) {
		if(!this.cenarios.containsKey(idCenario)) throw new CenarioNaoCadastradoException();
		Cenario cenario  = this.cenarios.get(idCenario);
		cenario.ocorrer(ocorreu);
	}

	public int getCaixa(int idCenario) {
		if(!this.cenarios.containsKey(idCenario)) throw new CenarioNaoCadastradoException();
		Cenario cenario = this.cenarios.get(idCenario);
		if(!cenario.isFinalizado()) throw new CenarioNaoEncerradoException();
		return cenario.calculaCaixa(this.taxa);
	}

	public int getTotalRateio(int idCenario) {
		if(!this.cenarios.containsKey(idCenario)) throw new CenarioNaoCadastradoException();
		Cenario cenario  = this.cenarios.get(idCenario);
		if(!cenario.isFinalizado()) throw new CenarioNaoEncerradoException();
		return cenario.getCaixa()-cenario.calculaCaixa(taxa);
	}

	public int getCaixa() {
		return this.caixa;
	}

}
