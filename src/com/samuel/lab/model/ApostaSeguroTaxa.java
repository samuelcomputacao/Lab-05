package com.samuel.lab.model;

public class ApostaSeguroTaxa extends Aposta{
	
	
	private double taxa;

	public ApostaSeguroTaxa(String apostador, int valor, boolean previsao,double taxa,int custo) {
		super(apostador, valor, previsao,custo);
		this.taxa = taxa;
		
	}
	
	@Override
	public String toString() {
		String str = super.toString() + String.format(" - ASSEGURADA (TAXA) - R$ %.2f", taxa*100);
		return str;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(taxa);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		ApostaSeguroTaxa other = (ApostaSeguroTaxa) obj;
		if (Double.doubleToLongBits(taxa) != Double.doubleToLongBits(other.taxa))
			return false;
		return true;
	}

	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}

	public double getTaxa() {
		return this.taxa;
	}
	
	

}
