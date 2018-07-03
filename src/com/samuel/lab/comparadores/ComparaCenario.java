package com.samuel.lab.comparadores;

import java.util.Comparator;

import com.samuel.lab.exception.CampoInvalidoException;
import com.samuel.lab.model.Cenario;

/**
 * Classe responsável por comparar dois cenários
 * @author Samuel Pereira de Vasconcelos
 *
 */
public class ComparaCenario implements Comparator<Cenario>{
	
	/**
	 * Ordem utilizada para a ordenação dos cenários
	 */
	private String ordem;

	/**
	 * Método responsável por comparar dois cenários de acordo com uma ordem específica.
	 */
	@Override
	public int compare(Cenario cenario1, Cenario cenario2) {
		int retorno = 0;
		if(ordem.equals("nome")) {
			 retorno = cenario1.getNome().compareTo(cenario2.getNome());
		}else if(ordem.equals("apostas")) {
			retorno = cenario2.totalApostas() - cenario1.totalApostas();
		}
		
		if (retorno == 0) {
			retorno = cenario1.getId() - cenario2.getId();
		}
		return retorno;
	}
	
	/**
	 * Método responsável por alterar a ordem de ordenação dos cenários
	 * @param ordem : Uma String representando a nova ordem
	 */
	public void setOrdem(String ordem) {
		if(!(ordem.equals("nome")||ordem.equals("apostas")||ordem.equals("cadastro"))) {
			throw new CampoInvalidoException("Erro ao alterar ordem: Ordem invalida");
		}
		this.ordem = ordem;
	}

}
