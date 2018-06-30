package com.samuel.lab.model;

import com.samuel.lab.exception.CampoInvalidoException;

/**
 * A Classe representa uma aposta assegurada
 * @author Samuel Pereira de Vasconcelos
 *
 */
public abstract class ApostaAssegurada extends Aposta {
	
	/**
	 * Representa o custo de uma aposta assegurada
	 */
	private int custo;

	/**
	 * Método para inicializar uma aposta assegurada
	 * @param apostador : Nome do apostador
	 * @param valor : Valor da aposta
	 * @param previsao : Previsão da aposta
	 * @param custo : Custo da aposta
	 */
	public ApostaAssegurada(String apostador, int valor, boolean previsao,int custo) {
		super(apostador, valor, previsao);
		if(custo <= 0) throw new CampoInvalidoException("Erro no cadastro de aposta: Custo inválido");
		this.custo = custo;
	}

	/**
	 * Método responsável por recuperar o valor do custo da aposta
	 * @return
	 */
	public int getCusto() {
		return this.custo;
	}
	
	public abstract double getSeguro();
	
}
