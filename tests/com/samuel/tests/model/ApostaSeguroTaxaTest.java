package com.samuel.tests.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.samuel.lab.exception.CampoInvalidoException;
import com.samuel.lab.model.ApostaSeguroTaxa;

/**
 * Classe responsável por testar a classe de aposta assegurada por taxa
 * @author Samuel Pereira de Vasconcelos
 *
 */
public class ApostaSeguroTaxaTest {
	
	/**
	 * Aposta utilizada como base para os testes
	 */
	private ApostaSeguroTaxa aposta;
	
	/**
	 * Testa o contrutor quando ele deve acontecer e inicializa a aposta base
	 */
	@Before
	public void testAposta() {
		this.aposta = new ApostaSeguroTaxa("Samuel", 1000, true, 0.2, 100);
	}
	
	/**
	 * Testa o construtor quando recebe um apostador nulo
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostaApostadorNull() {
		this.aposta = new ApostaSeguroTaxa(null, 100, true, 0.2, 100);
	}
	
	/**
	 * Testa o construtor quando recebe um apostador vazio
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostaApostadorVazio() {
		this.aposta = new ApostaSeguroTaxa(" ", 100, true, 0.2, 100);
	}
	
	/**
	 * Testa o construtor quando recebe um valor inválido
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostaValorInvalido() {
		this.aposta = new ApostaSeguroTaxa("Samuel", 0, true, 0.2, 100);
	}
	
	/**
	 * Testa o construtor quando recebe um valor negativo
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostaValorNegativo() {
		this.aposta = new ApostaSeguroTaxa("Samuel", -1, true, 0.2, 100);
	}
	
	/**
	 * Testa o construtor quando recebe uma taxa inválida
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostaTaxaInvalida() {
		this.aposta = new ApostaSeguroTaxa("Samuel", 10, false, 0, 100);
	}
	
	/**
	 * Testa o construtor quando recebe uma taxa negativa
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostaTaxaNegativa() {
		this.aposta = new ApostaSeguroTaxa("Samuel", 10, false, -10.20, 100);
	}
	
	/**
	 * Testa o construtor quando recebe um custo inválido
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostaCustoInvalido() {
		this.aposta = new ApostaSeguroTaxa("Samuel",11, false, 0.2, 0);
	}
	
	/**
	 * Testa o construtor quando recebe um custo negativo
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostaCustoNegativo() {
		this.aposta = new ApostaSeguroTaxa("Samuel", 0, false, 0.2, -10);
	}

	/**
	 * TEsta a geração do hashcode da aposta
	 */
	@Test
	public void testHashCode() {
		assertEquals(1751200974, this.aposta.hashCode());
	}

	/**
	 * Testa a representação textual da aposta quando ela é favoravel ao cenário correspondente 
	 */
	@Test
	public void testToStringVaiAcontecer() {
		String str = "Samuel - R$10,00 - VAI ACONTECER - ASSEGURADA (TAXA) - R$ 20,00";
		assertEquals(str, this.aposta.toString());
	}
	
	/**
	 * Testa a representação textual da aposta quando ela é contra ao cenário correspondente
	 */
	@Test
	public void testToStringNaoVaiAcontecer() {
		this.aposta = new ApostaSeguroTaxa("Samuel", 1000, false, 0.2, 100);
		String str = "Samuel - R$10,00 - N VAI ACONTECER - ASSEGURADA (TAXA) - R$ 20,00";
		assertEquals(str, this.aposta.toString());
	}

	/**
	 * Testa o comparador de aposta quando ele deve acontecer 
	 */
	@Test
	public void testEqualsObject() {
		ApostaSeguroTaxa aposta = new ApostaSeguroTaxa("Samuel", 1000, true, 0.2, 100);
		assertTrue(this.aposta.equals(aposta));;
	}

	/**
	 * Testa o recuperador da taxa da aposta
	 */
	@Test
	public void testGetTaxa() {
		assertTrue(0.2 == this.aposta.getTaxa());
	}

}
