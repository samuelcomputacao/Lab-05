package com.samuel.tests.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.samuel.lab.exception.CampoInvalidoException;
import com.samuel.lab.model.ApostaSeguroValor;

public class ApostaSeguroValorTest {
	
	/**
	 * Aposta utilizada como base para os testes
	 */
	private ApostaSeguroValor aposta;
	
	/**
	 * Testa o contrutor quando ele deve acontecer e inicializa a aposta base
	 */
	@Before
	public void testAposta() {
		this.aposta =new ApostaSeguroValor("Samuel", 1000, true, 200, 200);
	}
	
	/**
	 * Testa o construtor quando recebe um apostador nulo
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostaApostadorNull() {
		this.aposta = new ApostaSeguroValor(null, 100, true, 200, 100);
	}
	
	/**
	 * Testa o construtor quando recebe um apostador vazio
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostaApostadorVazio() {
		this.aposta = new ApostaSeguroValor(" ", 100, true,100, 100);
	}
	
	/**
	 * Testa o construtor quando recebe um valor inválido
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostaValorInvalido() {
		this.aposta = new ApostaSeguroValor("Samuel", 0, true,100, 100);
	}

	/**
	 * Testa o construtor quando recebe um valor negativo
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostaValorNegativo() {
		this.aposta = new ApostaSeguroValor("Samuel", -1, true,100, 100);
	}
	
	/**
	 * Testa o construtor quando recebe um seguro inválido
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostaSeguroInvalido() {
		this.aposta = new ApostaSeguroValor("Samuel", 10, false, 0, 100);
	}
	
	/**
	 * Testa o construtor quando recebe um seguro negativo
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostaSeguroNegativo() {
		this.aposta = new ApostaSeguroValor("Samuel", 10, false, -90, 100);
	}
	
	/**
	 * Testa o construtor quando recebe um custo inválido
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostaCustoInvalido() {
		this.aposta = new ApostaSeguroValor("Samuel",11, false, 100, 0);
	}
	
	/**
	 * Testa o construtor quando recebe um custo negativo
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostaCustoNegativo() {
		this.aposta = new ApostaSeguroValor("Samuel", 0, false, 100, -10);
	}
	
	/**
	 * Testa a representação textual da aposta quando ela é favoravel ao cenário correspondente 
	 */
	@Test
	public void testToStringVaiAcontecer() {
		String str = "Samuel - R$10,00 - VAI ACONTECER - ASSEGURADA (VALOR) - R$ 200,00";
		assertEquals(str, this.aposta.toString());
	}
	
	/**
	 * Testa a representação textual da aposta quando ela é contra ao cenário correspondente
	 */
	@Test
	public void testToStringNaoVaiAcontecer() {
		this.aposta = new ApostaSeguroValor("Samuel", 1000, false,200, 100);
		String str = "Samuel - R$10,00 - N VAI ACONTECER - ASSEGURADA (VALOR) - R$ 200,00";
		assertEquals(str, this.aposta.toString());
	}
	
	/**
	 * Testa o modificador do seguro da aposta quando ele deve acontecer
	 */
	@Test
	public void testSetSeguro() {
		this.aposta.setSeguro(300);
		assertEquals(300, this.aposta.getSeguro());
	}
	
	/**
	 * Testa o modificador do seguro da aposta quando ele recebe uma seguro inválido
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testSetSeguroInvalido() {
		this.aposta.setSeguro(0);

	}
	
	/**
	 * Testa o modificador do seguro da aposta quando ele recebe uma seguro negativo
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testSetSeguroNegativo() {
		this.aposta.setSeguro(-300);

	}

	/**
	 * Testa a recuperação do valor do seguro da aposta
	 */
	@Test
	public void testGetSeguro() {
		assertEquals(200, this.aposta.getSeguro());
	}
	
	/**
	 * Testa o comparador de apostas
	 */
	@Test
	public void testEquals() {
		ApostaSeguroValor aposta = new ApostaSeguroValor("Samuel", 1000, true, 200, 200);
		assertTrue(this.aposta.equals(aposta));
	}
	
	/**
	 * Testa o ferador do hashcode
	 */
	@Test
	public void testHashCode() {
		assertEquals(-1039059565, this.aposta.hashCode());
	}

}
