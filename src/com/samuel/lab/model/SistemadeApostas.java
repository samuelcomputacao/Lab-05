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
	
	/**
	 * Método responsável por cadastrar um cenário que possui bônus
	 * @param descricao : Uma String que representa a descrição do cenário
	 * @param bonus : Um inteiro que representa o bônus do cenário 
	 * @return : Um inteiro representando o id do cenário qu será cadastrado
	 */
	public int cadastrarCenario(String descricao, int bonus);
	
	/**
	 * Método responsável por cadatrar uma aposta assegurada por valor
	 * @param cenario: Um inteiro que representa o id do cenário onde a aposta será cadastrada
	 * @param apostador : Uma String que representa o  nome do apostador
	 * @param valor : Um inteiro representando o valor da aposta
	 * @param previsao : Uma String representando a previsão da aposta
	 * @param seguro : Um inteiro representando o valor do seguro da aposta
	 * @param custo : Um inteiro representando o valor so custo da aposta
	 * @return : Um inteiro que representa o id da aposta que foi cadastrada
	 */
	public int cadastrarApostaSeguraValor(int cenario, String apostador, int valor, String previsao, int seguro, int custo);
	
	/**
	 * Método responsável por cadatrar uma aposta assegurada por uma taxa relacionada ao seu valor
	 * @param cenario: Um inteiro que representa o id do cenário onde a aposta será cadastrada
	 * @param apostador : Uma String que representa o  nome do apostador
	 * @param valor : Um inteiro representando o valor da aposta
	 * @param previsao : Uma String representando a previsão da aposta
	 * @param taxa : Um valor de ponto flutuante representando o valor da taxa de seguro da aposta
	 * @param custo : Um inteiro representando o valor so custo da aposta
	 * @return : Um inteiro que representa o id da aposta que foi cadastrada
	 */
	public int cadastrarApostaSeguraTaxa(int cenario, String apostador, int valor, String previsao, double taxa, int custo);
	
	/**
	 * Método responsável por alterar o valor do seguro de uma aposta assegurada por valor
	 * @param cenario : Um inteiro representando o id do cenário onde a aposta que vai ser alterada está cadastrada
	 * @param apostaAssegurada : Um inteiro representando o id da aposta que será alterada
	 * @param valor : Um inteiro representando o novo valor da aposta 
	 * @return : O id da aposta alterada
	 */
	public int alterarSeguroValor(int cenario, int apostaAssegurada, int valor);
	
	/**
	 * Método responsável por alterar o valor da taxa de seguro de uma aposta assegurada por taxa
	 * @param cenario : Um inteiro representando o id do cenário onde a aposta que vai ser alterada está cadastrada
	 * @param apostaAssegurada : Um inteiro representando o id da aposta que será alterada
	 * @param taxa : Um valor de ponto flutuante representando a nova taxa de seguro da aposta 
	 * @return : O id da aposta alterada
	 */
	public int alterarSeguroTaxa(int cenario, int apostaAssegurada, double taxa);
	
	public void alterarOrdem(String ordem);
	
	public String exibirCenarioOrdenado(int cenario);
	

}
