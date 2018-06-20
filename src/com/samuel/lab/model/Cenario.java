package com.samuel.lab.model;

import java.util.ArrayList;
import java.util.List;

import com.samuel.lab.exception.CampoInvalidoException;
import com.samuel.lab.exception.CenarioJaEncerradoException;
import com.samuel.lab.exception.CenarioSemApostasException;

/**
 * Classe que representa um cenário que será exposto para apostas
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
	 *Representa se o cenário ocorreu ou não 
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
	 * Representa o caixa do cenário
	 */
	private int caixa;
	
	/**
	 * Método responsável por inicializar um cenário no sistema
	 * @param id : Um inteiro represetando o id do cenário que será criado
	 * @param descricao : Uma String representando a descrição do senŕio que será criado
	 */
	public Cenario(int id,String descricao) {
		if(id <= 0) throw new CampoInvalidoException("Erro no cadastro de aposta: Cenario invalido");
		if(descricao==null || descricao.isEmpty()) throw new CampoInvalidoException("Erro no cadastro de cenario: Descricao nao pode ser vazia");
		if(id <= 0) throw new CampoInvalidoException("");
		
		this.id = id;
		this.descricao = descricao;
		this.apostas = new ArrayList<>();
		this.encerrado = false;
		this.ocorreu = false;
		
	}
	
	/**
	 * Método responsável finalizar o cenário
	 */
	public void encerrar() {
		if(this.encerrado) throw new CenarioJaEncerradoException();
		this.encerrado = true;
	}
	
	/**
	 * Método acessível para o atribulto finalizado
	 * @return o valor do atribulto finalizado
	 */
	public boolean isEncerrado() {
		return this.encerrado;
	}
	
	/**
	 * Método responsável por indicar se um cenário ocorreu ou não
	 * @param ocorreu : Representa se o cenário ocorreu ou não
	 */
	public void ocorrer(boolean ocorreu) {
		if(this.encerrado) throw new CenarioJaEncerradoException();
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
	 * @return : Um String cok a representação textual do cenário
	 */
	@Override
	public String toString() {
		String retorno = String.format("%d - %s - ", this.id,this.descricao);
		
		if(this.encerrado) {
			if(this.ocorreu) {
				retorno += "finalizado (ocorreu)";
			}else {
				retorno += "finalizado (n ocorreu)";
			}
		}else {
			retorno += "Nao finalizado";
		}
		return retorno;
	}
	
	/**
	 * Método acessível para o id de um cenário
	 * @return : Um inteiro que representa o id do cenário
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Método responsável por gerar uma aposta para do cenário
	 * @param apostador : Nome do apostador
	 * @param valor : Valor total da aposta
	 * @param previsao : Previsão da aposta para o cenário segundo o apostador
	 */
	public void apostar(String apostador,int valor,String previsao) {
		if(previsao==null || previsao.trim().isEmpty()) throw new CampoInvalidoException("Erro no cadastro de aposta: Previsao nao pode ser vazia ou nula");
		Aposta aposta = null;
		if(previsao.equals("VAI ACONTECER")) {
			aposta = new Aposta(apostador, valor, true);
		}else if(previsao.equals("N VAI ACONTECER")){
			aposta = new Aposta(apostador, valor, false);
		}else {
			throw new CampoInvalidoException("Erro no cadastro de aposta: Previsao invalida");
		}
		this.caixa += aposta.getValor();
		this.apostas.add(aposta);
		
	}
	
	/**
	 * Método responsável por retornar todas as apostas do cenário
	 * @return uma lista de apostas
	 */
	public List<Aposta> getApostas() {
		return this.apostas;
	}
	
	/**
	 * Método responsável por verificar se o cenário ocorreu ou não
	 * @return
	 */
	public boolean ocorreu() {
		return this.ocorreu;
	}
	
	/**
	 * Método responsável por calcular o veloar do caixa do cenário que será destinado ao sistema
	 * @param taxa : A taxa que o sistema usa para o cálculo
	 * @return Um valor inteiroi representando o tatal em centavos do valor que será adicionado ao sistema
	 */
	public int calculaCaixa(double taxa) {
		if(this.apostas.isEmpty()) throw new CenarioSemApostasException();
		int valor  = 0;
		for(Aposta aposta : this.apostas){
			if(aposta.isAcontece()){
				if(!this.ocorreu) {
					valor += aposta.getValor();
				}
			}else {
				if(this.ocorreu) {
					valor += aposta.getValor();
				}
			}
	
		}
		return  (int) Math.floor((valor*taxa));
	}

	/**
	 * Método acessível para pegar o caixa completo do cenário
	 * @return o valor do caixa do cenário
	 */
	public int getCaixa() {
		int valor = 0;
		for(Aposta aposta : this.apostas){
			if(aposta.isAcontece()){
				if(!this.ocorreu) {
					valor += aposta.getValor();
				}
			}else {
				if(this.ocorreu) {
					valor += aposta.getValor();
				}
			}
	
		}
		return valor;
	}
	
	/**
	 * Método responsável por calcular o valor total das apostas do cenário
	 * @return um valor inteiro representando o valor total acumulado do cenário em centavos
	 */
	public int valorTotalDeApostas() {
//		int resultado = 0;
//		Iterator<Aposta> iterator = this.apostas.iterator();
//		while (iterator.hasNext()) {
//			resultado += iterator.next().getValor();
//		}
		if(this.apostas.isEmpty()) throw new CenarioSemApostasException();
		return this.caixa;
	}
	
	/**
	 * Método responsável por gerar um representação textual para todas as apostas do cenário
	 * @return um String representando todas as Strings do cenário
	 */
	public String exibiApostas() {
		if(this.apostas.isEmpty()) throw new CenarioSemApostasException();
		String retorno = "";
		for(Aposta aposta : this.apostas) {
			if(retorno.isEmpty()) {
				retorno = aposta.toString();
			}else {
				retorno += System.lineSeparator()  + aposta.toString();
			}
		}
		return retorno;
	}

	public int totalApostas() {
		if(this.apostas.isEmpty()) throw new CenarioSemApostasException();
		return this.apostas.size();
	}
	
	// Método respónsáve por calcular o caixa do cenário
	public int getCaixaTotal() {
		int valor = 0;
		for(Aposta aposta : this.apostas) {
			valor += aposta.getValor();
		}
		return valor;
	}
}
