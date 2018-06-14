package com.samuel.lab.model;

import com.samuel.lab.controller.CenarioController;

public class Facade implements SistemadeApostas{
	
	
	private CenarioController cenarioController;
	
	public Facade(int centavos,double taxa) {
		this.inicializa(centavos, taxa);
	}
	
	@Override
	public void inicializa(int centavos, double taxa) {
		this.cenarioController = new CenarioController(centavos,taxa);
	}


	public int getCaixa() {
		return this.cenarioController.getCaixa();
	}
	
	@Override
	public int cadastrarCenarios(String descricao) {
		return this.cenarioController.cadastrar(descricao);
	}

	@Override
	public String exibirCenario(int id) {
		return cenarioController.exibirCenario(id);
	}

	@Override
	public String exibirCenarios() {
		return cenarioController.exibirCenarios();
	}

	@Override
	public void cadastrarAposta(int cenario, String apostador, int valor, boolean previsao) {
		this.cenarioController.apostar(cenario,apostador,valor, previsao);
	}

	@Override
	public int valorTotalDeApostas(int cenario) {
		return  this.cenarioController.valorTotal(cenario);
	}

	@Override
	public int totalDeApostas(int cenario) {
		return  this.cenarioController.totalApostas(cenario);
	}

	@Override
	public String exibeApostas(int cenario) {
		return  this.cenarioController.exibirApostas(cenario);

	}

	@Override
	public void fecharAposta(int cenario, boolean ocorreu) {
		this.cenarioController.fecharAposta(cenario,ocorreu);
		
	}

	@Override
	public int getCaixaCenario(int cenario) {
		return this.cenarioController.getCaixa(cenario);
	}

	@Override
	public int getTotalRateioCenario(int cenario) {
		return this.cenarioController.getTotalRateio(cenario);
		
	}
	
	
	

}
