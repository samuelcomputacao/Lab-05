package com.samuel.lab.controller;

import java.util.SortedMap;
import java.util.TreeMap;

import com.samuel.lab.exception.CampoInvalidoException;
import com.samuel.lab.exception.CenarioNaoCadastradoException;
import com.samuel.lab.exception.CenarioNaoEncerradoException;
import com.samuel.lab.model.Cenario;

public class CenarioController {
	
	private int caixa;
	private double taxa;
	private int idBase;
	private SortedMap<Integer, Cenario> cenarios;
	

	public CenarioController(int caixa,double taxa) {
		this.idBase = 1;
		this.caixa = caixa;
		this.taxa = taxa;
		this.cenarios = new TreeMap<>();
	}

	public int cadastrar(String descricao) {
		Cenario cenario = new Cenario(idBase++, descricao);
		this.cenarios.put(cenario.getId(), cenario);
		return cenario.getId();
	}

	public String exibirCenario(int idCenario) {
		if(!cenarios.containsKey(idCenario)) {
			throw new CenarioNaoCadastradoException();
		}
		return this.cenarios.get(idCenario).toString();
	}

	public String exibirCenarios() {
		String retorno = "";
		//List<Integer> keysOrdenadas = setToList();
		for(Cenario cenario : this.cenarios.values()) {
			//cenario = this.cenarios.get(key);
			if (retorno.isEmpty()) {
				retorno = cenario.toString();
			} else {
				retorno += System.lineSeparator() + cenario.toString();
			}
		}
		return retorno;
	}

//	private List<Integer> setToList() {
//		Set<Integer> keys = this.cenarios.keySet();
//		
//		List<Integer> keysOrdenadas = new ArrayList<Integer>();
//		for(Integer key : keys) {
//			keysOrdenadas.add(key);
//		}
//		Collections.sort(keysOrdenadas);
//		return keysOrdenadas;
//	}

	public void apostar(int idCenario, String apostador, int valor, boolean previsao) {
		if(!this.cenarios.containsKey(idCenario)) throw new CenarioNaoCadastradoException();
		Cenario cenario = cenarios.get(idCenario);
		cenario.apostar(apostador,valor,previsao);
		
	}

	public int valorTotal(int idCenario) {
		if(!this.cenarios.containsKey(idCenario)) throw new CenarioNaoCadastradoException();
		Cenario cenario = this.cenarios.get(idCenario);
		return cenario.valorTotalDeApostas();
	}

	public int totalApostas(int idCenario) {
		if(!this.cenarios.containsKey(idCenario)) throw new CenarioNaoCadastradoException();
		return this.cenarios.get(idCenario).totalApostas();
	}

	public String exibirApostas(int idCenario) {
		if(!this.cenarios.containsKey(idCenario)) throw new CenarioNaoCadastradoException();
		Cenario cenario = this.cenarios.get(idCenario);
		return cenario.exibiApostas();
	}

	public void fecharAposta(int idCenario, boolean ocorreu) {
		if(idCenario <=0) throw new CampoInvalidoException("O id cenário informado é invalido");
		if(!this.cenarios.containsKey(idCenario)) throw new CenarioNaoCadastradoException();
		Cenario cenario  = this.cenarios.get(idCenario);
		cenario.ocorrer(ocorreu);
	}

	public int getCaixa(int idCenario) {
		if(!this.cenarios.containsKey(idCenario)) throw new CenarioNaoCadastradoException();
		Cenario cenario = this.cenarios.get(idCenario);
		if(!cenario.isEncerrado()) throw new CenarioNaoEncerradoException();
		return cenario.calculaCaixa(this.taxa);
	}

	public int getTotalRateio(int idCenario) {
		if(!this.cenarios.containsKey(idCenario)) throw new CenarioNaoCadastradoException();
		Cenario cenario  = this.cenarios.get(idCenario);
		if(!cenario.isEncerrado()) throw new CenarioNaoEncerradoException();
		return cenario.getCaixa()-cenario.calculaCaixa(taxa);
	}

	public int getCaixa() {
		return this.caixa;
	}

}
