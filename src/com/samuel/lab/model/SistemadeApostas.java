package com.samuel.lab.model;

public interface SistemadeApostas {
	
	public void inicializa(int centavos,double taxa);
	
	public void salva();
	
	public int cadastrarCenarios(String descricao);
	
	public String exibirCenario(int id);
	
	public String exibirCenarios();
	
	public void cadastrarAposta(int cenario, String apostador, int valor, boolean previsao);
	
	public int valorTotalDeApostas(int cenario);
	
	public int totalDeApostas(int cenario);
	
	public String exibeApostas(int cenario);
	
	public void fecharAposta(int cenario, boolean ocorreu);
	
	public int getCaixaCenario(int cenario);
	
	public int getTotalRateioCenario(int cenario);
	
}
