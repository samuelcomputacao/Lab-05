package com.samuel.lab.model;

/**
 * Interface responsável por posuir todos os métodos do sistema
 * 
 * @author Samuel Pereira de Vasconcelos
 *
 */
public interface SistemadeApostas {

	/**
	 * Método para inicializar o sistema
	 * 
	 * @param centavos
	 *            : centavos inicial no caixa do sistema
	 * @param taxa
	 *            : Taxa que será cobrada aos apostadores
	 */
	public void inicializa(int centavos, double taxa);

	/**
	 * Método responsável por cadastrar um cenário
	 * 
	 * @param descricao
	 * @return um inteiro representando o id do cenário;
	 */
	public int cadastrarCenario(String descricao);

	/**
	 * Método responsável por exibi um cenário
	 * 
	 * @param id
	 *            : Um Inteiro que representa o id do cenário que será exibido
	 * @return : Uma String com a representação textual do cenário
	 */
	public String exibirCenario(int id);

	/**
	 * Método responsável por exibir todos os cenários cadastrados no sistema
	 * 
	 * @return : Uma String representando todos os cenários do sistema
	 */
	public String exibirCenarios();

	/**
	 * Método responsável por cadastrar uma aposta
	 * 
	 * @param cenario
	 *            : Um inteiro representando em qual cenário será feita a aposta
	 * @param apostador
	 *            : Uma String representando o nome do apostador
	 * @param valor
	 *            : Um inteiro representando o valor em centavos da aposta
	 * @param previsao
	 *            : Uma string que representa a previsao do apostador
	 */
	public void cadastrarAposta(int cenario, String apostador, int valor, String previsao);

	/**
	 * Método responsável por calcular o valor total arrecardado com as apostas de
	 * um cenário
	 * 
	 * @param cenario
	 *            : Um inteiro representando o id do cenário que será verificado
	 * @return : Um inteiro representando o tatal arrecardado pelo cenário
	 */
	public int valorTotalDeApostas(int cenario);

	/**
	 * Método responsável por calcular o total de apostas de um cenário
	 * 
	 * @param cenario
	 *            : Um inteiro representando o id do cenário que será verificado
	 * @return : Um valor inteiro com a quantidade de apostas de um cenário
	 */
	public int totalDeApostas(int cenario);

	/**
	 * Método responsável por exibir todas as apostas de um cenário
	 * 
	 * @param cenario
	 *            : Um inteiro representando o id do cenário será feita a aposta
	 * @return : Uma String resepresentando todas as apostas cadastradas no cenário
	 */
	public String exibeApostas(int cenario);

	/**
	 * Método responsável encerrar um cenário
	 * 
	 * @param cenario
	 *            : Um inteiro representando o id do cenário será fechado
	 * @param ocorreu
	 *            : Um valor boleano que indica e o cenário ocorreu ou não
	 */
	public void fecharAposta(int cenario, boolean ocorreu);

	/**
	 * Método responsável por retornar o valor total de um cenário encerrado que
	 * será destinado ao caixa
	 * 
	 * @param cenario:
	 *            Um inteiro representando o id do cenário será recuperado o caixa
	 * @return : Um inteiro representando o caixa do cenário
	 */
	public int getCaixaCenario(int cenario);

	/**
	 * Método responsável por retornar o valor total de um cenário encerrado que
	 * será destinado a distribuição entre as apostas vencedoras
	 * 
	 * @param cenario
	 *            : Um inteiro representando o id do cenário que será verificado
	 * @return Um inteiro com o valor de rateio
	 */
	public int getTotalRateioCenario(int cenario);

}
