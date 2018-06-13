package com.samuel.lab.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cenario {
	
	private int id;
	private boolean finalizado;
	private boolean ocorreu;
	private String descricao;
	private List<Aposta> apostas;
	private int caixa;
	
	public Cenario(int id,String descricao) {
		this.id = id;
		this.descricao = descricao;
		this.apostas = new ArrayList<>();
		this.finalizado = false;
		this.ocorreu = false;
		
	} 
	
	public void encerrar() {
		this.finalizado = true;
	}
	
	public boolean isFinalizado() {
		return this.finalizado;
	}
	
	public void ocorrer(boolean ocorreu) {
		this.ocorreu = ocorreu;
		this.finalizado = true;
	}
	
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

	public int getId() {
		return this.id;
	}

	public void apostar(Aposta aposta) {
		this.apostas.add(aposta);
		
	}

	public List<Aposta> getApostas() {
		return this.apostas;
	}

	public boolean ocorreu() {
		return this.ocorreu;
	}

	public int calculaCaixa(double taxa) {
		
		Iterator<Aposta> iterator =this.apostas.iterator();
		Aposta aposta = null;
		while(iterator.hasNext()) {
			aposta = iterator.next();
			if(aposta.isAcontece()) {
				if(!this.ocorreu) {
					caixa += aposta.getValor();
				}
			}else {
				if(this.ocorreu) {
					caixa += aposta.getValor();
				}
			}
		}
		int valorParcial = (int) Math.floor(caixa*taxa);
		caixa -= valorParcial;
		return valorParcial;
	}

	public int getCaixa() {
		return this.caixa;
	}

}
