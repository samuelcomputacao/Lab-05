package com.samuel.lab.model;

import com.samuel.lab.controller.ApostaController;
import com.samuel.lab.controller.CenarioController;

public class Facade implements SistemadeApostas{
	
	private int caixa;
	private double taxa;
	
	private ApostaController apostaController;
	private CenarioController cenarioController;
	
	public Facade(int centavos,double taxa) {
		this.inicializa(centavos, taxa);
	}
	
	@Override
	public void inicializa(int centavos, double taxa) {
		this.caixa = centavos;
		this.taxa =taxa;
		this.cenarioController = new CenarioController();
	}

	@Override
	public void salva() {
		// TODO Auto-generated method stub
		
	}

	public int getCaixa() {
		return this.caixa;
	}
	
	@Override
	public int cadastrarCenarios(String descricao) {
		return this.cenarioController.cadastrar(descricao);
	}

	@Override
	public String exibirCenario(int id) {
		String retorno = cenarioController.exibirCenario(id);
		return retorno;
	}

	@Override
	public String exibirCenarios() {
		String retorno = cenarioController.exibirCenarios();
		return retorno;
	}

	@Override
	public void cadastrarAposta(int cenario, String apostador, int valor, String previsao) {
		Aposta aposta = this.apostaController.cadastrar(apostador,valor,previsao);
		this.cenarioController.apostar(cenario,aposta);
	}

	@Override
	public int valorTotalDeApostas(int cenario) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int totalDeApostas(int cenario) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String exibeApostas(int cenario) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
