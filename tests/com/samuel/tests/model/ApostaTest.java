package com.samuel.tests.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.samuel.lab.exception.CampoInvalidoException;
import com.samuel.lab.model.Aposta;

public class ApostaTest {
	
	private Aposta aposta;
	
	@Before
	public void testAposta() {
		this.aposta = new Aposta("Samuel", 1000, true);
	}
	
	@Test(expected = CampoInvalidoException.class)
	public void testApostaApostadorNull() {
		this.aposta = new Aposta(null, 1000, true);
	}
	
	@Test(expected = CampoInvalidoException.class)
	public void testApostaApostadorVazio() {
		this.aposta = new Aposta("", 1000, true);
	}
	
	@Test(expected = CampoInvalidoException.class)
	public void testApostaValorNegativo() {
		this.aposta = new Aposta("Samuel", -1000, true);
	}
	
	@Test(expected = CampoInvalidoException.class)
	public void testApostaValorZero() {
		this.aposta = new Aposta("Samuel", 0, true);
	}

	@Test
	public void testGetValor() {
		assertEquals(1000, this.aposta.getValor());
	}

	@Test
	public void testIsAconteceTrue() {
		assertTrue(this.aposta.isAcontece());
	}
	
	@Test
	public void testIsAconteceFalse() {
		this.aposta = new Aposta("Samuel", 1000, false);
		assertFalse(this.aposta.isAcontece());
	}

	@Test
	public void testToStringTrue() {
		String str = "Samuel - R$10,00 - VAI ACONTECER";
		assertEquals(str, this.aposta.toString());
	}
	
	@Test
	public void testToStringFalse() {
		String str = "Samuel - R$10,00 - N VAI ACONTECER";
		this.aposta = new Aposta("Samuel", 1000, false);
		assertEquals(str, this.aposta.toString());
	}
	
	

}
