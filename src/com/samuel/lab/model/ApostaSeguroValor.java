package com.samuel.lab.model;


public class ApostaSeguroValor extends Aposta{
	
	private int seguro;

	public ApostaSeguroValor(String apostador, int valor, boolean previsao,int seguro, int custo) {
		super(apostador, valor, previsao,custo);
		this.seguro = seguro;
	}
	
	@Override
	public String toString() {
		String str = super.toString() + String.format(" - ASSEGURADA (VALOR) - R$ %.2f", Double.valueOf(seguro));
		return str;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + seguro;
		return result;
	}

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


	public void setSeguro(int seguro) {
		this.seguro = seguro;
	}
	
	public int getSeguro() {
		return this.seguro;
	}
	
	
}
