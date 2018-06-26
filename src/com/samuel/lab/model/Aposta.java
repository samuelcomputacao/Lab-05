package com.samuel.lab.model;

import com.samuel.lab.exception.CampoInvalidoException;

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
		if(apostador== null || apostador.trim().isEmpty()) throw new CampoInvalidoException("Erro no cadastro de aposta: Apostador nao pode ser vazio ou nulo");
		if(valor<=0 ) throw new CampoInvalidoException("Erro no cadastro de aposta: Valor nao pode ser menor ou igual a zero");
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
		return String.format("%s - R$%.2f - %s", this.apostador,(double)this.valor/100,(this.previsao?"VAI ACONTECER":"N VAI ACONTECER"));
	}

	/**
	 * Método responsável por gerar o hashCode de Aposta
	 * @return : Um inteiro representando o hash da aposta 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apostador == null) ? 0 : apostador.hashCode());
		result = prime * result + (previsao ? 1231 : 1237);
		result = prime * result + valor;
		return result;
	}

	/**
	 * Método responsável por verificar se duas contas sao iguais
	 * @return : Um bolleano representado se as contas são iguais ou não
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aposta other = (Aposta) obj;
		if (apostador == null) {
			if (other.apostador != null)
				return false;
		} else if (!apostador.equals(other.apostador))
			return false;
		if (previsao != other.previsao)
			return false;
		if (valor != other.valor)
			return false;
		return true;
	}
	
	/**
	 * Método resposável por recuperar o apostador da aposta
	 * @return : O nome do apostador
	 */
	public String getApostador() {
		return this.apostador;
	}
}
