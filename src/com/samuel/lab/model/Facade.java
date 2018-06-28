 package com.samuel.lab.model;

import com.samuel.lab.controller.CenarioController;

import easyaccept.EasyAccept;

/**
 * Facade responsável por direcionar todos os métodos do sistema
 * 
 * @author Samuel Pereira de Vasconcelos
 *
 */
public class Facade implements SistemadeApostas {

	/**
	 * Controller utilizado para controlar toas as atividades de cenários
	 */
	private CenarioController cenarioController;
	
	/**
	 * Método para inicializar o sistema
	 * 
	 * @param centavos
	 *            : centavos inicial no caixa do sistema
	 * @param taxa
	 *            : Taxa que será cobrada aos apostadores
	 */
	@Override
	public void inicializa(int centavos, double taxa) {
		this.cenarioController = new CenarioController(centavos, taxa);
	}

	/**
	 * Método responsável por retornar o caixa do sistema
	 * 
	 * @return um inteiro com o valor em caixa do sistema
	 */
	public int getCaixa() {
		return this.cenarioController.getCaixa();
	}

	/**
	 * Método responsável por cadastrar um cenário
	 * 
	 * @param descricao
	 * @return um inteiro representando o id do cenário;
	 */
	@Override
	public int cadastrarCenario(String descricao) {
		return this.cenarioController.cadastrarCenario(descricao);
	}

	/**
	 * Método responsável por exibi um cenário
	 * 
	 * @param id
	 *            : Um Inteiro que representa o id do cenário que será exibido
	 * @return : Uma String com a representação textual do cenário
	 */
	@Override
	public String exibirCenario(int id) {
		return cenarioController.exibirCenario(id);
	}

	/**
	 * * Método responsável por exibir todos os cenários cadastrados no sistema
	 * 
	 * @return : Uma String representando todos os cenários do sistema
	 */
	@Override
	public String exibirCenarios() {
		return cenarioController.exibirCenarios();
	}

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
	@Override
	public void cadastrarAposta(int cenario, String apostador, int valor, String previsao) {
		this.cenarioController.cadastrarAposta(cenario, apostador, valor, previsao);
	}

	/**
	 * Método responsável por calcular o valor total arrecardado com as apostas de
	 * um cenário
	 * 
	 * @param cenario
	 *            : Um inteiro representando o id do cenário que será verificado
	 * @return : Um inteiro representando o tatal arrecardado pelo cenário
	 */
	@Override
	public int valorTotalDeApostas(int cenario) {
		return this.cenarioController.valorTotalDeApostas(cenario);
	}

	/**
	 * Método responsável por calcular o total de apostas de um cenário
	 * 
	 * @param cenario
	 *            : Um inteiro representando o id do cenário que será verificado
	 * @return : Um valor inteiro com a quantidade de apostas de um cenário
	 */
	@Override
	public int totalDeApostas(int cenario) {
		return this.cenarioController.totalDeApostas(cenario);
	}

	/**
	 * Método responsável por exibir todas as apostas de um cenário
	 * 
	 * @param cenario
	 *            : Um inteiro representando o id do cenário será feita a aposta
	 * @return : Uma String resepresentando todas as apostas cadastradas no cenário
	 */
	@Override
	public String exibeApostas(int cenario) {
		return this.cenarioController.exibirApostas(cenario);
	}

	/**
	 * Método responsável encerrar um cenário
	 * 
	 * @param cenario
	 *            : Um inteiro representando o id do cenário será fechado
	 * @param ocorreu
	 *            : Um valor boleano que indica e o cenário ocorreu ou não
	 */
	@Override
	public void fecharAposta(int cenario, boolean ocorreu) {
		this.cenarioController.fecharAposta(cenario, ocorreu);
	}

	/**
	 * Método responsável por retornar o valor total de um cenário encerrado que
	 * será destinado ao caixa
	 * 
	 * @param cenario:
	 *            Um inteiro representando o id do cenário será recuperado o caixa
	 * @return : Um inteiro representando o caixa do cenário
	 */
	@Override
	public int getCaixaCenario(int cenario) {
		return this.cenarioController.getCaixaCenario(cenario);
	}

	/**
	 * Método responsável por retornar o valor total de um cenário encerrado que
	 * será destinado a distribuição entre as apostas vencedoras
	 * 
	 * @param cenario
	 *            : Um inteiro representando o id do cenário que será verificado
	 * @return Um inteiro com o valor de rateio
	 */
	@Override
	public int getTotalRateioCenario(int cenario) {
		return this.cenarioController.getTotalRateio(cenario);
	}

	
	/**
	 * Método responsável por realizar os testes de validação da facade
	 * @param args
	 */
	public static void main(String[] args) {

		args = new String[] { "com.samuel.lab.model.Facade", "easy_accept/us1_test.txt", "easy_accept/us2_test.txt",
				"easy_accept/us3_test.txt", "easy_accept/us4_test.txt","easy_accept/us5_test.txt","easy_accept/us6_test.txt","easy_accept/us7_test.txt" };
		EasyAccept.main(args);

	}

	/**
	 * Método responsável por cadastrar um cenário que possui bônus
	 * @param descricao : Uma String que representa a descrição do cenário
	 * @param bonus : Um inteiro que representa o bônus do cenário 
	 * @return : Um inteiro representando o id do cenário qu será cadastrado
	 */
	@Override
	public int cadastrarCenario(String descricao, int bonus) {
		return this.cenarioController.cadastrarCenario(descricao, bonus);
	}


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
	@Override
	public int cadastrarApostaSeguraValor(int cenario, String apostador, int valor, String previsao, int seguro,int custo) {
		return this.cenarioController.cadastrarAposta( cenario, apostador, valor, previsao, seguro,custo);
	}

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
	@Override
	public int cadastrarApostaSeguraTaxa(int cenario, String apostador, int valor, String previsao, double taxa,int custo) {
		return this.cenarioController.cadastrarAposta( cenario, apostador, valor, previsao, taxa,custo);
	}

	/**
	 * Método responsável por alterar o valor do seguro de uma aposta assegurada por valor
	 * @param cenario : Um inteiro representando o id do cenário onde a aposta que vai ser alterada está cadastrada
	 * @param apostaAssegurada : Um inteiro representando o id da aposta que será alterada
	 * @param valor : Um inteiro representando o novo valor da aposta 
	 * @return : O id da aposta alterada
	 */
	@Override
	public int alterarSeguroValor(int cenario, int aposta, int valor) {
		return this.cenarioController.alterarSeguro(cenario,aposta,valor);
	}

	/**
	 * Método responsável por alterar o valor da taxa de seguro de uma aposta assegurada por taxa
	 * @param cenario : Um inteiro representando o id do cenário onde a aposta que vai ser alterada está cadastrada
	 * @param apostaAssegurada : Um inteiro representando o id da aposta que será alterada
	 * @param taxa : Um valor de ponto flutuante representando a nova taxa de seguro da aposta 
	 * @return : O id da aposta alterada
	 */
	@Override
	public int alterarSeguroTaxa(int cenario, int aposta, double taxa) {
		
		return this.cenarioController.alterarSeguro(cenario,aposta,taxa);
	
	}

	@Override
	public void alterarOrdem(String ordem) {
		this.cenarioController.alterarOrdem(ordem);
		
	}

	@Override
	public String exibirCenarioOrdenado(int cenario) {
		return this.cenarioController.exibirCenarioOrdenado(cenario);
	}
}
