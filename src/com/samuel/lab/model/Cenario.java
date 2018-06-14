package com.samuel.lab.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.samuel.lab.exception.CampoVazioException;

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
	private boolean finalizado;
	
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
		if(descricao==null || descricao.isEmpty()) {
			throw new CampoVazioException("Campo descrição vazio");
		}
		this.id = id;
		this.descricao = descricao;
		this.apostas = new ArrayList<>();
		this.finalizado = false;
		this.ocorreu = false;
		
	}
	
	/**
	 * Método responsável finalizar o cenário
	 */
	public void encerrar() {
		this.finalizado = true;
	}
	
	/**
	 * Método acessível para o atribulto finalizado
	 * @return o valor do atribulto finalizado
	 */
	public boolean isFinalizado() {
		return this.finalizado;
	}
	
	/**
	 * Método responsável por indicar se um cenário ocorreu ou não
	 * @param ocorreu : Representa se o cenário ocorreu ou não
	 */
	public void ocorrer(boolean ocorreu) {
		this.ocorreu = ocorreu;
		this.finalizado = true;
	}
	
	/**
	 * Método responsável por gerar uma string representando do cenário
	 * @return : Um String cok a representação textual do cenário
	 */
	@Override
	public String toString() {
		String retorno = String.format("%d - %s - ", this.id,this.descricao);
		
		if(this.finalizado) {
			if(this.ocorreu) {
				retorno += "Finalizado (ocorreu)";
			}else {
				retorno += "Finalizado (n ocorreu)";
			}
		}else {
			retorno += "Não finalizado";
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
	public void apostar(String apostador,int valor,boolean previsao) {
		Aposta aposta = new Aposta(apostador, valor, previsao);
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
		return (int) Math.floor(caixa*taxa);
	}

	/**
	 * Método acessível para pegar o caixa completo do cenário
	 * @return o valor do caixa do cenário
	 */
	public int getCaixa() {
		return this.caixa;
	}
	
	/**
	 * Método responsável por calcular o valor total das apostas do cenário
	 * @return um valor inteiro representando o valor total acumulado do cenário em centavos
	 */
	public int valorTotalDeApostas() {
		int resultado = 0;
		Iterator<Aposta> iterator = this.apostas.iterator();
		while (iterator.hasNext()) {
			resultado += iterator.next().getValor();
		}
		return resultado;
	}
	
	/**
	 * Método responsável por gerar um representação textual para todas as apostas do cenário
	 * @return um String representando todas as Strings do cenário
	 */
	public String exibiApostas() {
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
}
