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
	 * @param caixa : um inteiro com o valor iniciali do caixa
	 * @param taxa: Um double representando a taxa que será cobrada aos caixas dos cenários
	 */
	public CenarioController(int caixa,double taxa) {
		this.idBase = 1;
		this.caixa = caixa;
		this.taxa = taxa;
		this.cenarios = new TreeMap<>();
	}

	/**
	 * Responsável por cadastrar um cenário
	 * @param descricao : uma string com  descrição do cenário
	 * @return o id do cenário que foi cadastrado
	 */
	public int cadastrar(String descricao) {
		Cenario cenario = new Cenario(idBase++, descricao);
		this.cenarios.put(cenario.getId(), cenario);
		return cenario.getId();
	}

	/**
	 * Responsável por exibir um cenário que está cadastrado
	 * @param idCenario : Id do cenário que será exibido
	 * @return : Uma representação textual para o cenário
	 */
	public String exibirCenario(int idCenario) {
		if(idCenario <= 0) throw new CampoInvalidoException("O id é inválido");
		if(!cenarios.containsKey(idCenario)) {
			throw new CenarioNaoCadastradoException();
		}
		return this.cenarios.get(idCenario).toString();
	}

	/**
	 * Responsável por exibir todos os cenários do sistema
	 * @return : Uma representação textual para todos os cenários cadastrados
	 */
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

	/**
	 * Representa a criação de uma aposta 
	 * @param idCenario : O cenário que a aposta será cadastrada
	 * @param apostador : O nome do apostador
	 * @param valor : O valor da aposta
	 * @param previsao : Siginifica se a aposta é favorável ou cotra
	 */
	public void apostar(int idCenario, String apostador, int valor, boolean previsao) {
		if(!this.cenarios.containsKey(idCenario)) throw new CenarioNaoCadastradoException();
		Cenario cenario = cenarios.get(idCenario);
		cenario.apostar(apostador,valor,previsao);
		
	}
	
	/**
	 * Responsável por recuperar o valor total arrecardado por um cenário
	 * @param idCenario : id do cenário que será verificado
	 * @return o valor resultante do processo
	 */
	public int valorTotal(int idCenario) {
		if(!this.cenarios.containsKey(idCenario)) throw new CenarioNaoCadastradoException();
		Cenario cenario = this.cenarios.get(idCenario);
		return cenario.valorTotalDeApostas();
	}

	/**
	 * Responsável por recuperar a quantidade de apostas de um cenário
	 * @param idCenario : id do cenário que será verificado
	 * @return Um inteiro que representa aquantidade de apostas do cenário
	 */
	public int totalApostas(int idCenario) {
		if(!this.cenarios.containsKey(idCenario)) throw new CenarioNaoCadastradoException();
		return this.cenarios.get(idCenario).totalApostas();
	}

	/**
	 * responsável por recuperar uma representação textual para todas as apostas cadastradas em um cenário
	 * @param idCenario : id do cenário que será verificado
	 * @return A representação textual obtida
	 */ 
	public String exibirApostas(int idCenario) {
		if(!this.cenarios.containsKey(idCenario)) throw new CenarioNaoCadastradoException();
		Cenario cenario = this.cenarios.get(idCenario);
		return cenario.exibiApostas();
	}
	
	/**
	 * responsável por fechar um aposta de um cenário
	 * @param idCenario : id do cenári oque será trabalhado
	 * @param ocorreu indica se a aposta ocorreu ou não
	 */
	public void fecharAposta(int idCenario, boolean ocorreu) {
		if(idCenario <=0) throw new CampoInvalidoException("O id cenário informado é invalido");
		if(!this.cenarios.containsKey(idCenario)) throw new CenarioNaoCadastradoException();
		Cenario cenario  = this.cenarios.get(idCenario);
		cenario.ocorrer(ocorreu);
	}
	
	/**
	 * Recupera o caixa de um cenário 
	 * @param idCenario : id do cenário que será verificado
	 * @return o valor do caixa do cenário
	 */
	public int getCaixaCenario(int idCenario) {
		if(!this.cenarios.containsKey(idCenario)) throw new CenarioNaoCadastradoException();
		Cenario cenario = this.cenarios.get(idCenario);
		if(!cenario.isEncerrado()) throw new CenarioNaoEncerradoException();
		return cenario.calculaCaixa(this.taxa);
	}

	/**
	 * Recupera o valor total de rateio para um cenário 
	 * @param idCenario : id do cenário que será verificado
	 * @return o valor de raterio de um cenário
	 */
	public int getTotalRateio(int idCenario) {
		if(!this.cenarios.containsKey(idCenario)) throw new CenarioNaoCadastradoException();
		Cenario cenario  = this.cenarios.get(idCenario);
		if(!cenario.isEncerrado()) throw new CenarioNaoEncerradoException();
		return cenario.getCaixa()-cenario.calculaCaixa(taxa);
	}

	/**
	 * Recupera o valor do caixa do sistema 
	 * @return o valor do caixa
	 */
	public int getCaixa() {
		return this.caixa;
	}

}
