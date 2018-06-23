package com.samuel.lab.model;

import com.samuel.lab.exception.CampoInvalidoException;

/**
 * Classe que representa uma aposta assegurada por um valor no sistema
 * @author Samuel Pereeira de Vasconcelos
 *
 */
public class ApostaSeguroValor extends ApostaAssegurada{
	
	/**
	 * Representa o valor determinado como seguro para a aposta
	 */
	private int seguro;

	/**
	 * Método responsável por inicializar uma aposta assegurada com um valor
	 * @param apostador : Nome do apostador
	 * @param valor : Valor da aposta
	 * @param previsao : Previsão da aposta
	 * @param seguro : Valor do seguro da aposta
	 * @param custo : custo da aposta
	 */
	public ApostaSeguroValor(String apostador, int valor, boolean previsao,int seguro, int custo) {
		super(apostador, valor, previsao,custo);
		if(seguro <= 0) throw new CampoInvalidoException("Erro no cadastro de aposta: Seguro inválido");
		this.seguro = seguro;
	}
	
	/**
	 * Método responsável por gerar uma representação textual para a aposta
	 * @return A representação textual gerada
	 */
	@Override
	public String toString() {
		String str = super.toString() + String.format(" - ASSEGURADA (VALOR) - R$ %.2f", Double.valueOf(seguro));
		return str;
	}

	/**
	 * Método responsável por gerar uma númeração inteira para a aposta
	 * @return o valor gerado
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + seguro;
		return result;
	}

	/**
	 * Método responsável por comparar duas apostas
	 * @return um valor bolleano que indica se são duas apostas iguais ou não
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApostaSeguroValor other = (ApostaSeguroValor) obj;
		if (seguro != other.seguro)
			return false;
		return true;
	}

	/**
	 * Método responsável por alterar o valor do seguro
	 * @param seguro : O novo seguro da aposta
	 */
	public void setSeguro(int seguro) {
		if(seguro <= 0) throw new CampoInvalidoException("Erro ao modificar o valor do seguro da aposta: Seguro inválido");
		this.seguro = seguro;
	}
	
	/**
	 * Método responsável por recuperar o valor do seguro da aposta
	 * @return o valor do seguro da aposta
	 */
	public int getSeguro() {
		return this.seguro;
	}
	
}
