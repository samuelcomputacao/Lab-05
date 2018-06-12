package com.samuel.lab.model;

import java.util.ArrayList;
import java.util.List;

public class Cenario {
	
	private int id;
	private boolean finalizado;
	private boolean ocorreu;
	private String descricao;
	private List<Aposta> apostas;
	
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
		this.ocorreu = true;
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

}
