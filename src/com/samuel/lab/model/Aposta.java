package com.samuel.lab.model;

import com.samuel.lab.exception.CampoVazioException;

/**
 * Classe que repsesenta uima aposta no sistema
 * @author Samuel Pereira de Vasconcelos
 *
 */
public class Aposta {
	
	/**
	 * Representa o nome do apostador 
	 */
	private String apostador;
	
	/**
	 * Representa o valor em centavos da aposta
	 */
	private int valor;
	
	/**
	 * Representa a previsão da aposta segundo o apostador
	 */
	private boolean previsao;
	
	/**
	 * Método responsável por inicializar uma aposta no sistema
	 * @param apostador : Nome do apostador
	 * @param valor : Valor da aposta
	 * @param previsao : Previsão para a aposta segundo o apostador
	 */
	public Aposta(String apostador, int valor, boolean previsao) {
		if(apostador== null || apostador.isEmpty()) throw new CampoVazioException("Campo apostador vazio");
		this.apostador = apostador;
		this.valor = valor;
		this.previsao = previsao;
	}

	
	/**
	 * Método acessível para o valor da aposta
	 * @return Um valor inteiro representando o valor da aposta em centavos
	 */
	public int getValor() {
		return this.valor;
	}

	/**
	 * Método acessível para a previsão do apostador
	 * @return o valor da previsão
	 */
	public boolean isAcontece() {
		return this.previsao;
	}
	
	/**
	 * Método responsável por gerar uma representação textual de um aposta
	 * @return : Uma String representando uma aposta 
	 */
	@Override
	public String toString() {
		return String.format("%s - R$%.2f - %s", this.apostador,(this.valor/100),this.previsao?"VAI ACONTECER":"N VAI ACONTECER");
	}
}
