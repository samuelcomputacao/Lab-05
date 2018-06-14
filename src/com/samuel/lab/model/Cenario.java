package com.samuel.lab.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.samuel.lab.exception.CampoVazioException;

/**
 * 
 * @author paulomsj
 *
 */
public class Cenario {
	/**
	 * id do cenário
	 */
	private int id;
	/**
	 * okikikikik
	 */
	private boolean finalizado;
	private boolean ocorreu;
	private String descricao;
	private List<Aposta> apostas;
	private int caixa;
	
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
		this.caixa += aposta.getValor();
		this.apostas.add(aposta);
		
	}

	public List<Aposta> getApostas() {
		return this.apostas;
	}

	public boolean ocorreu() {
		return this.ocorreu;
	}

	public int calculaCaixa(double taxa) {
		return (int) Math.floor(caixa*taxa);
	}

	public int getCaixa() {
		
		return this.caixa;
	}

	public int valorTotalDeApostas() {
		int resultado = 0;
		Iterator<Aposta> iterator = this.apostas.iterator();
		while (iterator.hasNext()) {
			resultado += iterator.next().getValor();
		}
		return resultado;
	}

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
