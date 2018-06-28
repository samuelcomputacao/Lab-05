package com.samuel.lab.comparadores;

import java.util.Comparator;

import com.samuel.lab.model.Cenario;

public class ComparaNome implements Comparator<Cenario> {

	@Override
	public int compare(Cenario cenario1, Cenario cenario2) {
		int retorno = cenario1.getNome().compareTo(cenario2.getNome());
		if (retorno == 0) {
			retorno = cenario1.getId() - cenario2.getId();
		}
		return retorno;
	}

}
