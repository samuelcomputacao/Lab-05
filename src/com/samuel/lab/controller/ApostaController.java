package com.samuel.lab.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.samuel.lab.exception.ApostaNaoCadastradaException;
import com.samuel.lab.exception.CampoInvalidoException;
import com.samuel.lab.exception.CenarioSemApostasException;
import com.samuel.lab.model.Aposta;
import com.samuel.lab.model.ApostaSeguroTaxa;
import com.samuel.lab.model.ApostaSeguroValor;

public class ApostaController {
	
	/**
	 * Representa todas as apostas cadastradas no cenário
	 */
	private List<Aposta> apostas;

	/**
	 * Representa todas as apostas asseguradas por um valor
	 */
	private Map<Integer, ApostaSeguroValor> apostasSeguroValor;

	/**
	 * Representa todas as apostas asseguradas por um taxa
	 */
	private Map<Integer, ApostaSeguroTaxa> apostasSeguroTaxa;

	/**
	 * Um inteiro usado como base para o cadastramento de apostas asseguradas por valor
	 */
	private int idBaseValor;
	
	/**
	 * Um inteiro usado como base para o cadastramento de apostas asseguradas por uma taxa
	 */
	private int idBaseTaxa;
	
	/**
	 * Representa o valor total das apostas cadastradas 
	 */
	private int valorTotal;
	
	/**
	 * Representa a quantidade de apostas cadastradas
	 */
	private int quantidadeApostas;
	
	/**
	 * Representa a soma dos custos das apostas asseguradas
	 */
	private int custos;

	/**
	 * Método responsável por inicializar o controlador de aposta
	 */
	public ApostaController() {
		this.apostas = new ArrayList<>();
		this.apostasSeguroTaxa = new HashMap<>();
		this.apostasSeguroValor = new HashMap<>();
		this.idBaseTaxa = 1;
		this.idBaseValor = 1;
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
		for (ApostaSeguroValor aposta : this.apostasSeguroValor.values()) {
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

		for (ApostaSeguroTaxa aposta : this.apostasSeguroTaxa.values()) {
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

		for (ApostaSeguroValor aposta : this.apostasSeguroValor.values()) {
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

		for (ApostaSeguroTaxa aposta : this.apostasSeguroTaxa.values()) {
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
		if (this.apostas.isEmpty())
			throw new CenarioSemApostasException();
		String retorno = "";
		for (Aposta aposta : this.apostas) {
			if (retorno.isEmpty()) {
				retorno = aposta.toString();
			} else {
				retorno += System.lineSeparator() + aposta.toString();
			}
		}
		for (ApostaSeguroValor aposta : this.apostasSeguroValor.values()) {
			if (retorno.isEmpty()) {
				retorno = aposta.toString();
			} else {
				retorno += System.lineSeparator() + aposta.toString();
			}
		}
		for (ApostaSeguroTaxa aposta : this.apostasSeguroTaxa.values()) {
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
		ApostaSeguroValor aposta = null;
		if (previsao.equals("VAI ACONTECER")) {
			aposta = new ApostaSeguroValor(apostador, valor, true, seguro, custo);
		} else if (previsao.equals("N VAI ACONTECER")) {
			aposta = new ApostaSeguroValor(apostador, valor, false, seguro, custo);
		} else {
			throw new CampoInvalidoException("Erro no cadastro de aposta: Previsao invalida");
		}
		this.apostasSeguroValor.put(this.idBaseValor, aposta);
		this.idBaseValor++;
		this.valorTotal+= aposta.getValor();
		this.quantidadeApostas++;
		this.custos += aposta.getCusto();
		return this.idBaseValor - 1;
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
		this.apostasSeguroTaxa.put(this.idBaseTaxa, aposta);
		this.idBaseTaxa++;
		this.quantidadeApostas++;
		this.custos += aposta.getCusto();
		return idBaseTaxa - 1;
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
		if (!this.apostasSeguroTaxa.containsKey(idAposta))
			throw new ApostaNaoCadastradaException("Erro ao alterar a aposta: Aposta não cadastrada");
		ApostaSeguroValor aposta = this.apostasSeguroValor.get(idAposta);
		aposta.setSeguro(seguro);
		return idAposta;
	}

	/**
	 * Método responsável poralterar o valor da taxa de seguro de uma aposta
	 * @param idAposta : id da aposta que será alterada
	 * @param taxa : nova taxa da aposta
	 * @return : id da aposta que foi alterada
	 */
	public int alterar(int idAposta, double taxa) {
		if (idAposta <= 0)
			throw new CampoInvalidoException("Erro ao alterar a aposta: Aposta inválida");
		if (!this.apostasSeguroTaxa.containsKey(idAposta))
			throw new ApostaNaoCadastradaException("Erro ao alterar a aposta: Aposta não cadastrada");
		ApostaSeguroTaxa aposta = this.apostasSeguroTaxa.get(idAposta);
		aposta.setTaxa(taxa);
		return idAposta;
	}

	/**
	 * Método responsével por calcular o valor dos seguros de suas apostas
	 * @return : o valor do seguro das apostas do cenário
	 */
	public int seguroPerdedoras(boolean ocorreu) {
		int retorno = 0;
		for (ApostaSeguroValor aposta : this.apostasSeguroValor.values()) {
			if (aposta.isAcontece()) {
				if (!ocorreu)
					retorno += aposta.getSeguro();
			} else {
				if (ocorreu)
					retorno += aposta.getSeguro();
			}
		}
		for (ApostaSeguroTaxa aposta : this.apostasSeguroTaxa.values()) {
			if (aposta.isAcontece()) {
				if (!ocorreu)
					retorno += (aposta.getTaxa() * aposta.getValor());
			} else {
				if (ocorreu) {
					retorno += (aposta.getTaxa() * aposta.getValor());
				}
			}
		}
		return retorno;
	}

	/**
	 * Método responsável por recuperar o custo das apostas do cenário
	 * @return : o valor do custo das apostas
	 */
	public int getCustos() {
		return this.custos;
	}
	
}
