package com.samuel.lab.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.samuel.lab.exception.ApostaNaoCadastradaException;
import com.samuel.lab.exception.CampoInvalidoException;
import com.samuel.lab.exception.CenarioSemApostasException;
import com.samuel.lab.model.Aposta;
import com.samuel.lab.model.ApostaAssegurada;
import com.samuel.lab.model.ApostaSeguroTaxa;
import com.samuel.lab.model.ApostaSeguroValor;

public class ApostaController {
	
	/**
	 * Representa todas as apostas cadastradas no cenário
	 */
	private List<Aposta> apostas;

	/**
	 * Representa todas as apostas asseguradas
	 */

	private Map<Integer, ApostaAssegurada> apostasAsseguradas;

	/**
	 * Um inteiro usado como base para o cadastramento de apostas asseguradas
	 */
	private int idBaseAsseguradas;
	
	
	/**
	 * Representa o valor total das apostas cadastradas 
	 */
	private int valorTotal;
	
	/**
	 * Representa a quantidade de apostas cadastradas
	 */
	private int quantidadeApostas;

	/**
	 * Método responsável por inicializar o controlador de aposta
	 */
	public ApostaController() {
		this.apostas = new ArrayList<>();
		this.apostasAsseguradas = new HashMap<>();
		this.idBaseAsseguradas = 1;
		this.valorTotal =0;
	}
	
	/**
	 * Método responsável por gerar uma aposta para de um cenário
	 * 
	 * @param apostador
	 *            : Nome do apostador
	 * @param valor
	 *            : Valor total da aposta
	 * @param previsao
	 *            : Previsão da aposta para o cenário segundo o apostador
	 */
	public void cadastrar(String apostador, int valor, String previsao) {
		if (previsao == null || previsao.trim().isEmpty())
			throw new CampoInvalidoException("Erro no cadastro de aposta: Previsao nao pode ser vazia ou nula");
		Aposta aposta = null;
		if (previsao.equals("VAI ACONTECER")) {
			aposta = new Aposta(apostador, valor, true);
		} else if (previsao.equals("N VAI ACONTECER")) {
			aposta = new Aposta(apostador, valor, false);
		} else {
			throw new CampoInvalidoException("Erro no cadastro de aposta: Previsao invalida");
		}
		this.apostas.add(aposta);
		this.valorTotal += aposta.getValor();
		this.quantidadeApostas++;
	}
	
	/**
	 * Método responsável por calcular o veloar do caixa de um cenário que será
	 * destinado ao sistema
	 * 
	 * @param taxa
	 *            : A taxa que o sistema usa para o cálculo
	 * @return Um valor inteiroi representando o tatal em centavos do valor que será
	 *         adicionado ao sistema
	 */
	public int calculaCaixaPerdedoras(boolean ocorreu, double taxa) {
		int valor = 0;
		for (Aposta aposta : this.apostas) {
			if (aposta.isAcontece()) {
				if (!ocorreu) {
					valor += aposta.getValor();
				}
			} else {
				if (ocorreu) {
					valor += aposta.getValor();
				}
			}

		}
		
		for(ApostaAssegurada aposta : this.apostasAsseguradas.values()) {
			if (aposta.isAcontece()) {
				if (!ocorreu) {
					valor += aposta.getValor();
				}
			} else {
				if (ocorreu) {
					valor += aposta.getValor();
				}
			}
		}
		valor = (int) Math.floor((valor * taxa));
		return valor;
	}

	/**
	 * Método responsável por calcular o caixa completo de um cenário
	 * 
	 * @return o valor do caixa de um cenário
	 */
	public int calculaCaixa(boolean ocorreu) {
		int valor = 0;
		valor = 0;
		for (Aposta aposta : this.apostas) {
			if (aposta.isAcontece()) {
				if (!ocorreu) {
					valor += aposta.getValor();
				}
			} else {
				if (ocorreu) {
					valor += aposta.getValor();
				}
			}
		}
		
		for(Aposta aposta : this.apostasAsseguradas.values()) {
			if (aposta.isAcontece()) {
				if (!ocorreu) {
					valor += aposta.getValor();
				}
			} else {
				if (ocorreu) {
					valor += aposta.getValor();
				}
			}
		}
		return valor;
	}

	/**
	 * Método responsável por recuperar o valor total das apostas de um cenário
	 * 
	 * @return um valor inteiro representando o valor total acumulado do cenário em
	 *         centavos
	 */
	public int getValorTotal() {
		return this.valorTotal;
	}
	
	/**
	 * Método responsável por gerar um representação textual para todas as apostas
	 * de um cenário
	 * 
	 * @return um String representando todas as Strings do cenário
	 */
	public String exibir() {
		if (this.apostas.isEmpty() && this.apostasAsseguradas.isEmpty())
			throw new CenarioSemApostasException();
		String retorno = "";
		for (Aposta aposta : this.apostas) {
			if (retorno.isEmpty()) {
				retorno = aposta.toString();
			} else {
				retorno += System.lineSeparator() + aposta.toString();
			}
		}
		for(Aposta aposta : this.apostasAsseguradas.values()) {
			if (retorno.isEmpty()) {
				retorno = aposta.toString();
			} else {
				retorno += System.lineSeparator() + aposta.toString();
			}
		}
		return retorno;
	}

	/**
	 * Método responsável por calcular a quantidade de apostas cadastradas em um cenário
	 * @return
	 */
	public int getQuantidade() {
		return this.quantidadeApostas;
	}

	/**
	 * Método responsável por cadastrar uma aposta assegurada por um valor
	 * @param apostador : Nome do apostador
	 * @param valor : Valor da aposta
	 * @param previsao : Previsão da aposta
	 * @param seguro : Valor do seguro da aposta
	 * @param custo : Custo da aposta para o cenário
	 * @return : O id da aposta cadastrada
	 */
	public int cadastrar(String apostador, int valor, String previsao, int seguro, int custo) {
		if (previsao == null || previsao.trim().isEmpty())
			throw new CampoInvalidoException("Erro no cadastro de aposta: Previsao nao pode ser vazia ou nula");
		
		boolean previsaoBool = verificaPrevisao(previsao);
		ApostaSeguroValor aposta  = new ApostaSeguroValor(apostador, valor, previsaoBool, seguro, custo);
		//this.apostasSeguroValor.put(this.idBaseValor, aposta);
		this.apostasAsseguradas.put(this.idBaseAsseguradas, aposta);
		this.idBaseAsseguradas++;
		this.valorTotal+= aposta.getValor();
		this.quantidadeApostas++;
		return this.idBaseAsseguradas - 1;
	}

	/**
	 * Método responsável por vrificar a previsão de uma aposta a partir de ema String
	 * @param previsao :  Previsão em forma de uma String
	 * @return Um valor bolleano representando a previsão. 
	 */
	private boolean verificaPrevisao(String previsao) {
		if (previsao.equals("VAI ACONTECER")) {
			return true;
		} else if (previsao.equals("N VAI ACONTECER")) {
			return  false;
		} else {
			throw new CampoInvalidoException("Erro no cadastro de aposta: Previsao invalida");
		}
	}
	
	/**
	 * Método responsável por cadastrar uma aposta assegurada por uma taxa relacionada ao seu valor
	 * @param apostador : Nome do apostador
	 * @param valor : Valor da aposta
	 * @param previsao : Previsão da aposta
	 * @param taxa : Valor da taxa que assegura a aposta
	 * @param custo : Custo da aposta para o cenário
	 * @return : O id da aposta cadastrada
	 */
	public int cadastrar(String apostador, int valor, String previsao, double taxa, int custo) {
		if (previsao == null || previsao.trim().isEmpty())
			throw new CampoInvalidoException("Erro no cadastro de aposta: Previsao nao pode ser vazia ou nula");
		ApostaSeguroTaxa aposta = null;
		if (previsao.equals("VAI ACONTECER")) {
			aposta = new ApostaSeguroTaxa(apostador, valor, true, taxa, custo);
		} else if (previsao.equals("N VAI ACONTECER")) {
			aposta = new ApostaSeguroTaxa(apostador, valor, false, taxa, custo);
		} else {
			throw new CampoInvalidoException("Erro no cadastro de aposta: Previsao invalida");
		}
		this.valorTotal+= aposta.getValor();
		//this.apostasSeguroTaxa.put(this.idBaseTaxa, aposta);
		this.apostasAsseguradas.put(this.idBaseAsseguradas, aposta);
		this.idBaseAsseguradas++;
		this.quantidadeApostas++;
		return idBaseAsseguradas - 1;
	}

	/**
	 * Método responsável por alterar o valor do seguro de uma aposta
	 * @param idAposta : id da aposta que será alterada
	 * @param seguro : novo valor do seguro
	 * @return : id da aposta que foi alterada
	 */
	public int alterar(int idAposta, int seguro) {
		if (idAposta <= 0)
			throw new CampoInvalidoException("Erro ao alterar a aposta: Aposta inválida");
		if (!this.apostasAsseguradas.containsKey(idAposta))
			throw new ApostaNaoCadastradaException("Erro ao alterar a aposta: Aposta não cadastrada");
		ApostaAssegurada aposta =  this.apostasAsseguradas.get(idAposta);
		String previsao = aposta.isAcontece()?"VAI ACONTECER":"N VAI ACONTECER";
		this.apostasAsseguradas.put(idAposta, new ApostaSeguroValor(aposta.getApostador(), aposta.getValor(), this.verificaPrevisao(previsao), seguro, aposta.getCusto()));
		return idAposta;
	}

	/**
	 * Método responsável por alterar o valor da taxa de seguro de uma aposta
	 * @param idAposta : id da aposta que será alterada
	 * @param taxa : nova taxa da aposta
	 * @return : id da aposta que foi alterada
	 */
	public int alterar(int idAposta, double taxa) {
		if (idAposta <= 0)
			throw new CampoInvalidoException("Erro ao alterar a aposta: Aposta inválida");
		if (!this.apostasAsseguradas.containsKey(idAposta))
			throw new ApostaNaoCadastradaException("Erro ao alterar a aposta: Aposta não cadastrada");
		ApostaAssegurada aposta = this.apostasAsseguradas.get(idAposta);
		String previsao = aposta.isAcontece()?"VAI ACONTECER":"N VAI ACONTECER";
		this.apostasAsseguradas.remove(idAposta);
		this.apostasAsseguradas.put(idAposta, new ApostaSeguroTaxa(aposta.getApostador(), aposta.getValor(), this.verificaPrevisao(previsao), taxa, aposta.getCusto()));
		return idAposta;
	}

	/**
	 * Método responsével por calcular o valor dos seguros de suas apostas
	 * @return : o valor do seguro das apostas do cenário
	 */
	public int seguroPerdedoras(boolean ocorreu) {
		int retorno = 0;
		for(ApostaAssegurada aposta : this.apostasAsseguradas.values()) {
			if (aposta.isAcontece()) {
				if (!ocorreu)
					retorno += aposta.getSeguro();
			} else {
				if (ocorreu)
					retorno += aposta.getSeguro();
			}
		}
		return retorno;
	}

	/**
	 * Método responsável por recuperar o custo das apostas do cenário
	 * @return : o valor do custo das apostas
	 */
	public int getCustos() {
		int custos = 0;
		for(ApostaAssegurada aposta : this.apostasAsseguradas.values()) {
			custos += aposta.getCusto();
		}
		return custos;
	}
	
}
