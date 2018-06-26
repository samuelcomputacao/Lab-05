package com.samuel.lab.controller;

import java.util.SortedMap;
import java.util.TreeMap;

import com.samuel.lab.exception.CampoInvalidoException;
import com.samuel.lab.exception.CenarioNaoCadastradoException;
import com.samuel.lab.exception.CenarioNaoEncerradoException;
import com.samuel.lab.model.Cenario;

/**
 * Classe responsável por controlar tudo que pertence a um cenário
 * 
 * @author Samuel Pereira de Vasconcelos
 *
 */
public class CenarioController {

	/**
	 * Representa o caixa do sistema
	 */
	private int caixa;

	/**
	 * Representa a taxa que será cobrada aos caixas dos cenários
	 */
	private double taxa;

	/**
	 * Representa de onde o id irá inicializar
	 */
	private int idBase;

	/**
	 * Representa todos os cenários cadastrados
	 */
	private SortedMap<Integer, Cenario> cenarios;

	/**
	 * Método responsável por inicializar um cenarioController
	 * 
	 * @param caixa
	 *            : um inteiro com o valor iniciali do caixa
	 * @param taxa:
	 *            Um double representando a taxa que será cobrada aos caixas dos
	 *            cenários
	 */
	public CenarioController(int caixa, double taxa) {
		if (caixa < 0) {
			throw new CampoInvalidoException("Erro na inicializacao: Caixa nao pode ser inferior a 0");
		}
		if (taxa < 0)
			throw new CampoInvalidoException("Erro na inicializacao: Taxa nao pode ser inferior a 0");
		this.idBase = 1;
		this.caixa = caixa;
		this.taxa = taxa;
		this.cenarios = new TreeMap<>();
	}

	/**
	 * Responsável por cadastrar um cenário
	 * 
	 * @param descricao
	 *            : uma string com descrição do cenário
	 * @return o id do cenário que foi cadastrado
	 */
	public int cadastrarCenario(String descricao) {
		Cenario cenario = new Cenario(this.idBase, descricao);
		this.cenarios.put(cenario.getId(), cenario);
		this.idBase++;
		return cenario.getId();
	}

	/**
	 * Responsável por exibir um cenário que está cadastrado
	 * 
	 * @param idCenario
	 *            : Id do cenário que será exibido
	 * @return : Uma representação textual para o cenário
	 */
	public String exibirCenario(int idCenario) {
		if (idCenario <= 0)
			throw new CampoInvalidoException("Erro na consulta de cenario: Cenario invalido");
		if (!cenarios.containsKey(idCenario)) {
			throw new CenarioNaoCadastradoException("Erro na consulta de cenario: Cenario nao cadastrado");
		}
		return this.cenarios.get(idCenario).toString();
	}

	/**
	 * Responsável por exibir todos os cenários do sistema
	 * 
	 * @return : Uma representação textual para todos os cenários cadastrados
	 */
	public String exibirCenarios() {
		String retorno = "";
		for (Cenario cenario : this.cenarios.values()) {
			if (retorno.isEmpty()) {
				retorno = cenario.toString();
			} else {
				retorno += System.lineSeparator() + cenario.toString();
			}
		}
		return retorno;
	}

	/**
	 * Representa a criação de uma aposta
	 * 
	 * @param idCenario
	 *            : O cenário que a aposta será cadastrada
	 * @param apostador
	 *            : O nome do apostador
	 * @param valor
	 *            : O valor da aposta
	 * @param previsao
	 *            : Siginifica se a aposta é favorável ou cotra
	 */
	public void cadastrarAposta(int idCenario, String apostador, int valor, String previsao) {
		if (idCenario <= 0)
			throw new CampoInvalidoException("Erro no cadastro de aposta: Cenario invalido");
		if (!this.cenarios.containsKey(idCenario))
			throw new CenarioNaoCadastradoException("Erro no cadastro de aposta: Cenario nao cadastrado");
		if (apostador == null || apostador.trim().isEmpty())
			throw new CampoInvalidoException("Erro no cadastro de aposta: Apostador nao pode ser vazio ou nulo");
		Cenario cenario = cenarios.get(idCenario);
		cenario.apostar(apostador, valor, previsao);

	}

	/**
	 * Responsável por recuperar o valor total arrecardado por um cenário
	 * 
	 * @param idCenario
	 *            : id do cenário que será verificado
	 * @return o valor resultante do processo
	 */
	public int valorTotalDeApostas(int idCenario) {
		if (idCenario <= 0)
			throw new CampoInvalidoException("Erro na consulta do valor total de apostas: Cenario invalido");
		if (!this.cenarios.containsKey(idCenario))
			throw new CenarioNaoCadastradoException(
					"Erro na consulta do valor total de apostas: Cenario nao cadastrado");
		Cenario cenario = this.cenarios.get(idCenario);
		return cenario.getValorTotalDeApostas();
	}

	/**
	 * Responsável por recuperar a quantidade de apostas de um cenário
	 * 
	 * @param idCenario
	 *            : id do cenário que será verificado
	 * @return Um inteiro que representa aquantidade de apostas do cenário
	 */
	public int totalDeApostas(int idCenario) {
		if (idCenario <= 0)
			throw new CampoInvalidoException("Erro na consulta do total de apostas: Cenario invalido");
		if (!this.cenarios.containsKey(idCenario))
			throw new CenarioNaoCadastradoException("Erro na consulta do total de apostas: Cenario nao cadastrado");
		return this.cenarios.get(idCenario).totalApostas();
	}

	/**
	 * responsável por recuperar uma representação textual para todas as apostas
	 * cadastradas em um cenário
	 * 
	 * @param idCenario
	 *            : id do cenário que será verificado
	 * @return A representação textual obtida
	 */
	public String exibirApostas(int idCenario) {
		if (!this.cenarios.containsKey(idCenario))
			throw new CenarioNaoCadastradoException("Erro no cadastro de aposta: Cenario nao cadastrado");
		Cenario cenario = this.cenarios.get(idCenario);
		return cenario.exibiApostas();
	}

	/**
	 * responsável por fechar um aposta de um cenário
	 * 
	 * @param idCenario
	 *            : id do cenári oque será trabalhado
	 * @param ocorreu
	 *            indica se a aposta ocorreu ou não
	 */
	public void fecharAposta(int idCenario, boolean ocorreu) {
		if (idCenario <= 0)
			throw new CampoInvalidoException("Erro ao fechar aposta: Cenario invalido");
		if (!this.cenarios.containsKey(idCenario))
			throw new CenarioNaoCadastradoException("Erro ao fechar aposta: Cenario nao cadastrado");
		Cenario cenario = this.cenarios.get(idCenario);
		cenario.ocorrer(ocorreu);
	}

	/**
	 * Recupera o caixa de um cenário
	 * 
	 * @param idCenario
	 *            : id do cenário que será verificado
	 * @return o valor do caixa do cenário
	 */
	public int getCaixaCenario(int idCenario) {
		if (idCenario <= 0)
			throw new CampoInvalidoException("Erro na consulta do caixa do cenario: Cenario invalido");
		if (!this.cenarios.containsKey(idCenario))
			throw new CenarioNaoCadastradoException("Erro na consulta do caixa do cenario: Cenario nao cadastrado");
		Cenario cenario = this.cenarios.get(idCenario);
		if (!cenario.isEncerrado())
			throw new CenarioNaoEncerradoException("Erro na consulta do caixa do cenario: Cenario ainda esta aberto");

		return cenario.calculaCaixa(this.taxa);
	}

	/**
	 * Recupera o valor total de rateio para um cenário
	 * 
	 * @param idCenario
	 *            : id do cenário que será verificado
	 * @return o valor de raterio de um cenário
	 */
	public int getTotalRateio(int idCenario) {
		if (idCenario <= 0)
			throw new CampoInvalidoException("Erro na consulta do total de rateio do cenario: Cenario invalido");
		if (!this.cenarios.containsKey(idCenario))
			throw new CenarioNaoCadastradoException(
					"Erro na consulta do total de rateio do cenario: Cenario nao cadastrado");
		Cenario cenario = this.cenarios.get(idCenario);
		if (!cenario.isEncerrado())
			throw new CenarioNaoEncerradoException(
					"Erro na consulta do total de rateio do cenario: Cenario ainda esta aberto");
		return (int) (cenario.getCaixa() - cenario.calculaCaixa(taxa)) + cenario.getBonus();
	}

	/**
	 * Recupera o valor do caixa do sistema
	 * @return o valor do caixa
	 */
	public int getCaixa() {
		int caixasCenarios = 0;
		int bonus = 0;
		int seguro = 0;
		int custo = 0;
		for (Cenario c : this.cenarios.values()) {
			if (c.isEncerrado()) {
				caixasCenarios += c.calculaCaixa(taxa);
				seguro += c.calculaSeguro();
			}
			if (c.getBonus() > 0) {
				bonus += c.getBonus();
			}
			custo += c.getCustosApostas();
		}
		return (this.caixa - bonus - seguro) + caixasCenarios + custo;
	}

	/**
	 * Método responsável por cadastras um cenário com bonus
	 * @param descricao : Descrição do cenário
	 * @param bonus : bonus do cenário
	 * @return o id do cenário cadastrado
	 */
	public int cadastrarCenario(String descricao, int bonus) {
		if (bonus <= 0)
			throw new CampoInvalidoException("Erro no cadastro de cenario: Bonus invalido");
		Cenario cenario = new Cenario(this.idBase, descricao, bonus);
		this.cenarios.put(cenario.getId(), cenario);
		this.idBase++;
		return cenario.getId();
	}

	/**
	 * Método responsável por cadastrar uma aposta assegurada por valor
	 * @param idCenario : id do cenário que a aposta será cadastrada
	 * @param apostador : Nome do apostador 
	 * @param valor : Valor da aposta
	 * @param previsao : Previsão da aposta para o cenário segunda o apostados
	 * @param seguro : Valor do seguro da aposta
	 * @param custo : Custo da aposta para o cenário
	 * @return o id da aposta que será cadastrada
	 */
	public int cadastrarAposta(int idCenario, String apostador, int valor, String previsao, int seguro,
			int custo) {
		if (idCenario <= 0)
			throw new CampoInvalidoException("Erro no cadastro de aposta assegurada por valor: Cenario invalido");
		if (!this.cenarios.containsKey(idCenario))
			throw new CenarioNaoCadastradoException(
					"Erro no cadastro de aposta assegurada por valor: Cenario invalido");
		if (apostador == null || apostador.trim().length() == 0)
			throw new CampoInvalidoException(
					"Erro no cadastro de aposta assegurada por valor: Apostador nao pode ser vazio ou nulo");
		if (valor <= 0)
			throw new CampoInvalidoException(
					"Erro no cadastro de aposta assegurada por valor: Valor nao pode ser menor ou igual a zero");
		if (previsao == null || previsao.trim().length() == 0)
			throw new CampoInvalidoException(
					"Erro no cadastro de aposta assegurada por valor: Previsao nao pode ser vazia ou nula");
		if (!(previsao.equals("VAI ACONTECER") || previsao.equals("N VAI ACONTECER")))
			throw new CampoInvalidoException("Erro no cadastro de aposta assegurada por valor: Previsao invalida");
		Cenario cenario = this.cenarios.get(idCenario);
		return cenario.apostarSeguroValor(apostador, valor, previsao, seguro, custo);
	}

	/**
	 * Método responsável por cadatrar uma aposta assegurada por uma taxa relacionada ao seu valor
	 * @param idCenario : id do cenário onde a aposta sera cadastrada
	 * @param apostador : nome do apostador
	 * @param valor : valor da aposta
	 * @param previsao : previsão da aposta em relação ao cenário segundo o apostador
	 * @param taxa : taxa do seguro da aposta em relação ao seu valor
	 * @param custo : o custo da aposta para o cenário
	 * @return :id da aposta que foi cadastrada
	 */
	public int cadastrarAposta(int idCenario, String apostador, int valor, String previsao, double taxa,
			int custo) {
		if (idCenario <= 0)
			throw new CampoInvalidoException("Erro no cadastro de aposta assegurada por taxa: Cenario invalido");
		if (!this.cenarios.containsKey(idCenario))
			throw new CenarioNaoCadastradoException("Erro no cadastro de aposta assegurada por taxa: Cenario invalido");
		Cenario cenario = this.cenarios.get(idCenario);
		return cenario.apostarSeguroTaxa(apostador, valor, previsao, taxa, custo);
	}

	/**
	 * Método responsável por alterar o valor do seguro de uma aposta
	 * @param idCenario : id do cenário os a aposta está cadastrada
	 * @param idAposta : id da aposta que será alterada
	 * @param seguro : novo valor que será inserido na aposta
	 * @return : o id da aposta que foi alterada
	 */
	public int alterarSeguro(int idCenario, int idAposta, int seguro) {
		if (idCenario <= 0)
			throw new CampoInvalidoException("a fazer");
		if (!this.cenarios.containsKey(idCenario))
			throw new CenarioNaoCadastradoException("a fazer");
		Cenario cenario = this.cenarios.get(idCenario);
		return cenario.alterarSeguro(idAposta, seguro);
	}

	/**
	 * Método responsável por alterar a taxa do seguro de uma aposta 
	 * @param idCenario : id do cenário onde a aposta esta cadastrada
	 * @param aposta : id da aposta que será alterada
	 * @param taxa : nova taxa a ser inserida na aposta
	 * @return : o id da aposta alterada
	 */
	public int alterarSeguro(int idCenario, int aposta, double taxa) {
		if (idCenario <= 0)
			throw new CampoInvalidoException("Erro ao alterar aposta : Cenario invalido");
		if (!this.cenarios.containsKey(idCenario))
			throw new CenarioNaoCadastradoException("Erro ao alterar aposta: Cenario nao cadastrado");

		Cenario cenario = this.cenarios.get(idCenario);
		return cenario.alterarSeguro(aposta, taxa);
	}

}
