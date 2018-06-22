package com.samuel.lab.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.samuel.lab.exception.ApostaNaoCadastradaException;
import com.samuel.lab.exception.CampoInvalidoException;
import com.samuel.lab.exception.CenarioJaEncerradoException;
import com.samuel.lab.exception.CenarioSemApostasException;

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
	 * Representa o bonus do cenário
	 */
	private int bonus;

	/**
	 * Um inteiro usado como base para o cadastramento de apostas asseguradas por valor
	 */
	private int idBaseValor;
	
	/**
	 * Um inteiro usado como base para o cadastramento de apostas asseguradas por uma taxa
	 */
	private int idBaseTaxa;
	
	private int custo;
	
	private int valorTotalDeApostas;

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
		if (descricao == null || descricao.isEmpty())
			throw new CampoInvalidoException("Erro no cadastro de cenario: Descricao nao pode ser vazia");
		if (id <= 0)
			throw new CampoInvalidoException("");
		this.id = id;
		this.descricao = descricao;
		this.apostas = new ArrayList<>();
		this.apostasSeguroValor = new HashMap<>();
		this.apostasSeguroTaxa = new HashMap<>();
		this.encerrado = false;
		this.ocorreu = false;
		this.bonus = 0;
		this.idBaseTaxa = 1;
		this.idBaseValor = 1;
		this.custo = 0;
		this.valorTotalDeApostas = 0;

	}

	/**
	 * Método reposnável por inicializar um cenário com bônus
	 * @param id : id do cenário que será inicnalizado
	 * @param descricao : Descrição do cenário
	 * @param bonus : Bônus do cenário
	 */
	public Cenario(int id, String descricao, int bonus) {
		this(id, descricao);
		this.bonus = bonus;
		this.custo = 0;
		this.valorTotalDeApostas = 0;
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
				retorno += "finalizado (ocorreu)";
			} else {
				retorno += "finalizado (n ocorreu)";
			}
		} else {
			retorno += "Nao finalizado";
		}
		return retorno + (this.bonus > 0 ? String.format(" - R$ %.2f", Double.valueOf(this.bonus / 100)) : "");
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
		Aposta aposta = null;
		if (previsao.equals("VAI ACONTECER")) {
			aposta = new Aposta(apostador, valor, true);
		} else if (previsao.equals("N VAI ACONTECER")) {
			aposta = new Aposta(apostador, valor, false);
		} else {
			throw new CampoInvalidoException("Erro no cadastro de aposta: Previsao invalida");
		}
		this.apostas.add(aposta);
		this.valorTotalDeApostas+= aposta.getValor();

	}

//	/**
//	 * Método responsável por retornar todas as apostas do cenário
//	 * 
//	 * @return uma lista de apostas
//	 */
//	public List<Aposta> getApostas() {
//		
//		return this.apostas;
//	}

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
		int valor = 0;
		for (Aposta aposta : this.apostas) {
			if (aposta.isAcontece()) {
				if (!this.ocorreu) {
					valor += aposta.getValor();
				}
			} else {
				if (this.ocorreu) {
					valor += aposta.getValor();
				}
			}

		}
		for (ApostaSeguroValor aposta : this.apostasSeguroValor.values()) {
			if (aposta.isAcontece()) {
				if (!this.ocorreu) {
					valor += (aposta.getValor());
				}
			} else {
				if (this.ocorreu) {
					valor += aposta.getValor();
				}
			}

		}

		for (ApostaSeguroTaxa aposta : this.apostasSeguroTaxa.values()) {
			if (aposta.isAcontece()) {
				if (!this.ocorreu) {
					valor += aposta.getValor();
				}
			} else {
				if (this.ocorreu) {
					valor += aposta.getValor();
				}
			}
		}
		valor = (int) Math.floor((valor * taxa));
		return valor;
	}

	/**
	 * Método acessível para pegar o caixa completo do cenário
	 * 
	 * @return o valor do caixa do cenário
	 */
	public int getCaixa() {
		int valor = 0;
		valor = 0;
		for (Aposta aposta : this.apostas) {
			if (aposta.isAcontece()) {
				if (!this.ocorreu) {
					valor += aposta.getValor();
				}
			} else {
				if (this.ocorreu) {
					valor += aposta.getValor();
				}
			}

		}

		for (ApostaSeguroValor aposta : this.apostasSeguroValor.values()) {
			if (aposta.isAcontece()) {
				if (!this.ocorreu) {
					valor += aposta.getValor();
				}
			} else {
				if (this.ocorreu) {
					valor += aposta.getValor();
				}
			}
		}

		for (ApostaSeguroTaxa aposta : this.apostasSeguroTaxa.values()) {
			if (aposta.isAcontece()) {
				if (!this.ocorreu) {
					valor += aposta.getValor();
				}
			} else {
				if (this.ocorreu) {
					valor += aposta.getValor();
				}
			}
		}
		return valor;
	}

	/**
	 * Método responsável por recuperar o valor total das apostas do cenário
	 * 
	 * @return um valor inteiro representando o valor total acumulado do cenário em
	 *         centavos
	 */
	public int getValorTotalDeApostas() { 
		return this.valorTotalDeApostas;
	}

	/**
	 * Método responsável por gerar um representação textual para todas as apostas
	 * do cenário
	 * 
	 * @return um String representando todas as Strings do cenário
	 */
	public String exibiApostas() {
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
	 * Método responsável por calcular a quantidade de apostas cadastrado no cenário
	 * @return
	 */
	public int totalApostas() {
		if (this.apostas.isEmpty())
			throw new CenarioSemApostasException();
		return this.apostas.size() + this.apostasSeguroTaxa.size()+this.apostasSeguroValor.size();
	}

//	// Método respónsáve por calcular o caixa do cenário
//	public int getCaixaTotal() {
//		int valor = 0;
//		for (Aposta aposta : this.apostas) {
//			valor += aposta.getValor();
//		}
//		return valor;
//	}

	/**
	 * Método responsável por recuperar o bonus do cenário
	 * @return : o bonus do funcionário
	 */
	public int getBonus() {
		return this.bonus;
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
	public int apostarSeguroValor(String apostador, int valor, String previsao, int seguro, int custo) {
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
		this.custo += custo;
		this.valorTotalDeApostas+= aposta.getValor();
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
	public int apostarSeguroTaxa(String apostador, int valor, String previsao, double taxa, int custo) {
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
		this.valorTotalDeApostas+= aposta.getValor();
		this.apostasSeguroTaxa.put(this.idBaseTaxa, aposta);
		this.idBaseTaxa++;
		this.custo += custo;
		return idBaseTaxa - 1;
	}

	/**
	 * Método responsável por alterar o valor do seguro de uma aposta
	 * @param idAposta : id da aposta que será alterada
	 * @param seguro : novo valor do seguro
	 * @return : id da aposta que foi alterada
	 */
	public int alterarSeguro(int idAposta, int seguro) {
		if (idAposta <= 0)
			throw new CampoInvalidoException("A fazer...");
		if (!this.apostasSeguroTaxa.containsKey(idAposta))
			throw new ApostaNaoCadastradaException("A fazer ....");
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
	public int alterarSeguro(int idAposta, double taxa) {
		if (idAposta <= 0)
			throw new CampoInvalidoException("A fazer...");
		if (!this.apostasSeguroTaxa.containsKey(idAposta))
			throw new ApostaNaoCadastradaException("A fazer ....");
		ApostaSeguroTaxa aposta = this.apostasSeguroTaxa.get(idAposta);
		aposta.setTaxa(taxa);
		return idAposta;
	}

	/**
	 * Método responsével por calcular o valor dos seguros de suas apostas
	 * @return : o valor do seguro das apostas do cenário
	 */
	public int calculaSeguro() {
		int retorno = 0;
		for (ApostaSeguroValor aposta : this.apostasSeguroValor.values()) {
			if (aposta.isAcontece()) {
				if (!this.ocorreu)
					retorno += aposta.getSeguro();
			} else {
				if (this.ocorreu)
					retorno += aposta.getSeguro();
			}
		}
		for (ApostaSeguroTaxa aposta : this.apostasSeguroTaxa.values()) {
			if (aposta.isAcontece()) {
				if (!this.ocorreu)
					retorno += (aposta.getTaxa() * aposta.getValor());
			} else {
				if (this.ocorreu) {
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
	public int getCusto() {
		return this.custo;
	}

}
