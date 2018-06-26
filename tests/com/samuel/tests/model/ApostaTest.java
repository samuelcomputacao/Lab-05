package com.samuel.tests.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.samuel.lab.exception.CampoInvalidoException;
import com.samuel.lab.model.Aposta;

/**
 * Classe responsável por testar a classe Aposta 
 * @author Samuel PEreira de Vasconcelos
 *
 */
public class ApostaTest {
	
	/**
	 * Aposta utilizada como base para os testes
	 */
	private Aposta aposta;
	
	/**
	 * Testa o construtor e inicializa a aposta base
	 */
	@Before
	public void testAposta() {
		this.aposta = new Aposta("Samuel", 1000, true);
	}
	
	/**
	 * Testa o construtor com o apostador nulo
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostaApostadorNull() {
		this.aposta = new Aposta(null, 1000, true);
	}
	
	/**
	 * Testa o construtor com o apostador vazio
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostaApostadorVazio() {
		this.aposta = new Aposta(" ", 1000, true);
	}
	
	/**
	 * Testa o construtor de uma aposta com valor negativo
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostaValorNegativo() {
		this.aposta = new Aposta("Samuel", -1000, true);
	}
	
	/**
	 * Testa o construtor de uma aposta com o valor zerado 
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostaValorZero() {
		this.aposta = new Aposta("Samuel", 0, true);
	}

	/**
	 * Testa o processo de recuperar o valor da aposta
	 */
	@Test
	public void testGetValor() {
		assertEquals(1000, this.aposta.getValor());
	}

	/**
	 * Testa o processo de verificar se a aposta está favorável
	 */
	@Test
	public void testIsAconteceTrue() {
		assertTrue(this.aposta.isAcontece());
	}
	
	/**
	 * Testa o processo de verificar se a aposta está contra
	 */
	@Test
	public void testIsAconteceFalse() {
		this.aposta = new Aposta("Samuel", 1000, false);
		assertFalse(this.aposta.isAcontece());
	}

	/**
	 * Testa o toString quando a aposta é favorável
	 */
	@Test
	public void testToStringTrue() {
		String str = "Samuel - R$10,00 - VAI ACONTECER";
		assertEquals(str, this.aposta.toString());
	}
	
	/**
	 * Testa o toString quado a aposta é contra
	 */
	@Test
	public void testToStringFalse() {
		String str = "Samuel - R$10,00 - N VAI ACONTECER";
		this.aposta = new Aposta("Samuel", 1000, false);
		assertEquals(str, this.aposta.toString());
	}
}
