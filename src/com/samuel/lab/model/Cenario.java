package com.samuel.lab.model;

import com.samuel.lab.controller.ApostaController;
import com.samuel.lab.exception.CampoInvalidoException;
import com.samuel.lab.exception.CenarioJaEncerradoException;

/**
 * Classe que representa um cenário que será exposto para apostas
 * 
 * @author Samuel Pereira de Vasconcelos
 */
public class Cenario {

	/**
	 * Representa o id do cenário
	 */
	private int id;

	/**
	 * Representa se o cenário está finalizado ou não
	 */
	private boolean encerrado;

	/**
	 * Representa se o cenário ocorreu ou não
	 */
	private boolean ocorreu;

	/**
	 * Representa a descrição do cenário
	 */
	private String descricao;

	/**
	 * Representa o bonus do cenário
	 */
	private int bonus;

	/**
	 * Representao controlador das apostas de um cenário
	 */
	private ApostaController apostaController;

	/**
	 * Método responsável por inicializar um cenário no sistema
	 * 
	 * @param id
	 *            : Um inteiro represetando o id do cenário que será criado
	 * @param descricao
	 *            : Uma String representando a descrição do senŕio que será criado
	 */
	public Cenario(int id, String descricao) {
		if (id <= 0)
			throw new CampoInvalidoException("Erro no cadastro de aposta: Cenario invalido");
		if (descricao == null || descricao.trim().isEmpty())
			throw new CampoInvalidoException("Erro no cadastro de cenario: Descricao nao pode ser vazia");
		this.id = id;
		this.descricao = descricao;
		this.ocorreu = false;
		this.bonus = 0;
		this.apostaController = new ApostaController();
	}

	/**
	 * Método reposnável por inicializar um cenário com bônus
	 * 
	 * @param id
	 *            : id do cenário que será inicnalizado
	 * @param descricao
	 *            : Descrição do cenário
	 * @param bonus
	 *            : Bônus do cenário
	 */
	public Cenario(int id, String descricao, int bonus) {
		this(id, descricao);
		this.bonus = bonus;
	}

	/**
	 * Método responsável finalizar o cenário
	 */
	public void encerrar() {
		if (this.encerrado)
			throw new CenarioJaEncerradoException();
		this.encerrado = true;
	}

	/**
	 * Método acessível para o atribulto finalizado
	 * 
	 * @return o valor do atribulto finalizado
	 */
	public boolean isEncerrado() {
		return this.encerrado;
	}

	/**
	 * Método responsável por indicar se um cenário ocorreu ou não
	 * 
	 * @param ocorreu
	 *            : Representa se o cenário ocorreu ou não
	 */
	public void ocorrer(boolean ocorreu) {
		if (this.encerrado)
			throw new CenarioJaEncerradoException();
		this.ocorreu = ocorreu;
		this.encerrado = true;
	}

	/**
	 * Método responsável por verificar se um cenário ocorreu ou não
	 */
	public boolean isOcorreu() {
		return this.ocorreu;
	}

	/**
	 * Método responsável por gerar uma string representando do cenário
	 * 
	 * @return : Um String cok a representação textual do cenário
	 */
	@Override
	public String toString() {
		String retorno = String.format("%d - %s - ", this.id, this.descricao);

		if (this.encerrado) {
			if (this.ocorreu) {
				retorno += "Finalizado (ocorreu)";
			} else {
				retorno += "Finalizado (n ocorreu)";
			}
		} else {
			retorno += "Nao finalizado";
		}
		return retorno + (this.bonus > 0 ? String.format(" - R$ %.2f", (this.bonus / 100.0)) : "");
	}

	/**
	 * Método acessível para o id de um cenário
	 * 
	 * @return : Um inteiro que representa o id do cenário
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Método responsável por gerar uma aposta para do cenário
	 * 
	 * @param apostador
	 *            : Nome do apostador
	 * @param valor
	 *            : Valor total da aposta
	 * @param previsao
	 *            : Previsão da aposta para o cenário segundo o apostador
	 */
	public void apostar(String apostador, int valor, String previsao) {
		if (previsao == null || previsao.trim().isEmpty())
			throw new CampoInvalidoException("Erro no cadastro de aposta: Previsao nao pode ser vazia ou nula");
		this.apostaController.cadastrar(apostador, valor, previsao);

	}

	/**
	 * Método responsável por verificar se o cenário ocorreu ou não
	 * 
	 * @return
	 */
	public boolean ocorreu() {
		return this.ocorreu;
	}

	/**
	 * Método responsável por calcular o veloar do caixa do cenário que será
	 * destinado ao sistema
	 * 
	 * @param taxa
	 *            : A taxa que o sistema usa para o cálculo
	 * @return Um valor inteiroi representando o tatal em centavos do valor que será
	 *         adicionado ao sistema
	 */
	public int calculaCaixa(double taxa) {
		return this.apostaController.calculaCaixaPerdedoras(this.ocorreu, taxa);
	}

	/**
	 * Método responsável por buscar o caixa completo do cenário
	 * 
	 * @return o valor do caixa do cenário
	 */
	public int getCaixa() {
		return this.apostaController.calculaCaixa(this.ocorreu);
	}

	/**
	 * Método responsável por recuperar o valor total das apostas do cenário
	 * 
	 * @return um valor inteiro representando o valor total acumulado do cenário em
	 *         centavos
	 */
	public int getValorTotalDeApostas() {
		return this.apostaController.getValorTotal();
	}

	/**
	 * Método responsável por gerar um representação textual para todas as apostas
	 * do cenário
	 * 
	 * @return um String representando todas as Strings do cenário
	 */
	public String exibiApostas() {
		return this.apostaController.exibir();
	}

	/**
	 * Método responsável por calcular a quantidade de apostas cadastradas no
	 * cenário
	 * 
	 * @return
	 */
	public int totalApostas() {
		return this.apostaController.getQuantidade();
	}

	/**
	 * Método responsável por recuperar o bonus do cenário
	 * 
	 * @return : o bonus do funcionário
	 */
	public int getBonus() {
		return this.bonus;
	}

	/**
	 * Método responsável por cadastrar uma aposta assegurada por um valor
	 * 
	 * @param apostador
	 *            : Nome do apostador
	 * @param valor
	 *            : Valor da aposta
	 * @param previsao
	 *            : Previsão da aposta
	 * @param seguro
	 *            : Valor do seguro da aposta
	 * @param custo
	 *            : Custo da aposta para o cenário
	 * @return : O id da aposta cadastrada
	 */
	public int apostarSeguroValor(String apostador, int valor, String previsao, int seguro, int custo) {
		return this.apostaController.cadastrar(apostador, valor, previsao, seguro, custo);
	}

	/**
	 * Método responsável por cadastrar uma aposta assegurada por uma taxa
	 * relacionada ao seu valor
	 * 
	 * @param apostador
	 *            : Nome do apostador
	 * @param valor
	 *            : Valor da aposta
	 * @param previsao
	 *            : Previsão da aposta
	 * @param taxa
	 *            : Valor da taxa que assegura a aposta
	 * @param custo
	 *            : Custo da aposta para o cenário
	 * @return : O id da aposta cadastrada
	 */
	public int apostarSeguroTaxa(String apostador, int valor, String previsao, double taxa, int custo) {
		if (apostador == null || apostador.trim().length() == 0)
			throw new CampoInvalidoException(
					"Erro no cadastro de aposta assegurada por taxa: Apostador nao pode ser vazio ou nulo");
		if (valor <= 0)
			throw new CampoInvalidoException(
					"Erro no cadastro de aposta assegurada por taxa: Valor nao pode ser menor ou igual a zero");
		if (previsao == null || previsao.trim().length() == 0)
			throw new CampoInvalidoException(
					"Erro no cadastro de aposta assegurada por taxa: Previsao nao pode ser vazia ou nula");
		if (!(previsao.equals("VAI ACONTECER") || previsao.equals("N VAI ACONTECER")))
			throw new CampoInvalidoException("Erro no cadastro de aposta assegurada por taxa: Previsao invalida");
		return this.apostaController.cadastrar(apostador, valor, previsao, taxa, custo);
	}

	/**
	 * Método responsável por alterar o valor do seguro de uma aposta
	 * 
	 * @param idAposta
	 *            : id da aposta que será alterada
	 * @param seguro
	 *            : novo valor do seguro
	 * @return : id da aposta que foi alterada
	 */
	public int alterarSeguro(int idAposta, int seguro) {
		return this.apostaController.alterar(idAposta, seguro);
	}

	/**
	 * Método responsável poralterar o valor da taxa de seguro de uma aposta
	 * 
	 * @param idAposta
	 *            : id da aposta que será alterada
	 * @param taxa
	 *            : nova taxa da aposta
	 * @return : id da aposta que foi alterada
	 */
	public int alterarSeguro(int idAposta, double taxa) {
		return this.apostaController.alterar(idAposta, taxa);
	}

	/**
	 * Método responsével por calcular o valor dos seguros de suas apostas
	 * 
	 * @return : o valor do seguro das apostas do cenário
	 */
	public int calculaSeguro() {
		return this.apostaController.seguroPerdedoras(this.ocorreu);
	}

	/**
	 * Método responsável por recuperar o custo das apostas do cenário
	 * 
	 * @return : o valor do custo das apostas
	 */
	public int getCustosApostas() {
		return this.apostaController.getCustos();
	}

	public String getNome() {
		return this.descricao;
	}

}
