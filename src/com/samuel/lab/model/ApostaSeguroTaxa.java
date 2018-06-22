package com.samuel.lab.model;

/**
 * Classe que representa uma aposta assegurada por uma taxa
 * @author Samuel Pereira de Vasconcelos
 *
 */
public class ApostaSeguroTaxa extends ApostaAssegurada{
	
	/**
	 * Representa a taxa que assegura a aposta
	 */
	private double taxa;

	/**
	 * Método responsável por inicializar uma aposta assegurada por uma taxa no sistema
	 * @param apostador : Nome do apostador
	 * @param valor : Valor da aposta
	 * @param previsao : Previsão da aposta 
	 * @param taxa : Taxa que assegura a aposta
	 * @param custo : Custo da aposta
	 */
	public ApostaSeguroTaxa(String apostador, int valor, boolean previsao,double taxa,int custo) {
		super(apostador, valor, previsao,custo);
		this.taxa = taxa;
		
	}
	
	/**
	 * Método responsável por criar uma representação textual para uma aposta
	 * @return : uma representação textual para a aposta
	 */
	@Override
	public String toString() {
		String str = super.toString() + String.format(" - ASSEGURADA (TAXA) - R$ %.2f", taxa*100);
		return str;
	}

	/**
	 * Método responsável por criar uma valor inteiro que identifique de maneira única uma aposta no sistema
	 * @return O inteiro gerado
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(taxa);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/**
	 * Método responsável por comparar duas apostas
	 * @return Um valor bolleano indicando sse elas são iguais ou não
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApostaSeguroTaxa other = (ApostaSeguroTaxa) obj;
		if (Double.doubleToLongBits(taxa) != Double.doubleToLongBits(other.taxa))
			return false;
		return true;
	}
	
	/**
	 * Método responsável por alterar o valor da taxa da aposta
	 * @param taxa : Representa a nova taxa
	 */
	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}

	/**
	 * Método responsável por recuperar o valor da taxa da aposta
	 * @return o valor da taxa
	 */
	public double getTaxa() {
		return this.taxa;
	}
	
}
