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
		this.apostaController = new ApostaController();
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
		return cenarioController.exibirCenario(id);
	}

	@Override
	public String exibirCenarios() {
		return cenarioController.exibirCenarios();
	}

	@Override
	public void cadastrarAposta(int cenario, String apostador, int valor, boolean previsao) {
		this.cenarioController.apostar(cenario,this.apostaController.cadastrar(apostador,valor,previsao));
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
		int valor = this.cenarioController.getCaixa(cenario,this.taxa);
		this.caixa += valor;
		return valor;
	}

	@Override
	public int getTotalRateioCenario(int cenario) {
		return this.cenarioController.getTotalArrecardado(cenario);
		
	}
	
	
	

}
