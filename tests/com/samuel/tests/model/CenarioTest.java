package com.samuel.tests.model;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import com.samuel.lab.exception.CampoInvalidoException;
import com.samuel.lab.exception.CenarioJaEncerradoException;
import com.samuel.lab.exception.CenarioSemApostasException;
import com.samuel.lab.model.Aposta;
import com.samuel.lab.model.Cenario;

import org.junit.Test;

public class CenarioTest {
	
	public Cenario cenario;

	@Before
	public void testCenario() {
		this.cenario = new Cenario(1, "Brasil Hexa");
	}
	
	@Test(expected = CampoInvalidoException.class)
	public void testCenarioIdInvalido() {
		this.cenario = new Cenario(0, "Brasil Hexa");
	}
	
	@Test(expected = CampoInvalidoException.class)
	public void testCenarioDescricaoNull() {
		this.cenario = new Cenario(1, null);
	}
	
	@Test(expected = CampoInvalidoException.class)
	public void testCenarioDescricaoVazia() {
		this.cenario = new Cenario(1, "");
	}

	@Test
	public void testEncerrar() {
		this.cenario.encerrar();
		assertTrue(this.cenario.isEncerrado());
	}
	
	@Test(expected = CenarioJaEncerradoException.class)
	public void testEncerrarEncerrado() {
		this.cenario.encerrar();
		this.cenario.encerrar();
	}

	@Test
	public void testIsEncerradoTrue() {
		this.cenario.encerrar();
		assertTrue(this.cenario.isEncerrado());
	}
	
	@Test
	public void testIsEncerradoFalse() {
		assertFalse(this.cenario.isEncerrado());
	}


	@Test
	public void testOcorrerTrue() {
		this.cenario.ocorrer(true);
		assertTrue(this.cenario.isOcorreu());
	}
	
	@Test
	public void testOcorrerFAlse() {
		this.cenario.ocorrer(false);
		assertFalse(this.cenario.isOcorreu());
	}
	
	@Test(expected = CenarioJaEncerradoException.class)
	public void testOcorrerOcorrido() {
		this.cenario.ocorrer(false);
		this.cenario.ocorrer(false);
	}

	@Test
	public void testToStringNaoFinalizado() {
		assertEquals("1 - Brasil Hexa - Não Finalizado", this.cenario.toString());
	}
	
	@Test
	public void testToStringFinalizadoOcorrido() {
		this.cenario.ocorrer(true);
		assertEquals("1 - Brasil Hexa - Finalizado (ocorreu)", this.cenario.toString());
	}
	
	@Test
	public void testToStringFinalizadoNaoOcorrido() {
		this.cenario.ocorrer(false);
		assertEquals("1 - Brasil Hexa - Finalizado (n ocorreu)", this.cenario.toString());
	}

	@Test
	public void testGetId() {
		assertEquals(1, this.cenario.getId());
	}

	@Test(expected = CampoInvalidoException.class)
	public void testApostarApostadorNull() {
		this.cenario.apostar(null, 1000, true);
	}
	
	@Test(expected = CampoInvalidoException.class)
	public void testApostarValorInvalido() {
		this.cenario.apostar("Samuel", 0, true);
	}	

	@Test
	public void testGetApostas() {
		List<Aposta> apostas = new ArrayList<Aposta>();
		
		Aposta aposta1 = new Aposta("Samuel", 10, true);
		Aposta aposta2 = new Aposta("Samuel", 10, false);
		
		apostas.add(aposta1);
		apostas.add(aposta2);
		
		this.cenario.apostar("Samuel", 10, true);
		this.cenario.apostar("Samuel", 10, false);
		
		assertEquals(apostas, this.cenario.getApostas());
	}

	@Test
	public void testOcorreuTrue() {
		this.cenario.ocorrer(true);
		assertTrue(this.cenario.isOcorreu());
	}
	
	@Test
	public void testOcorreuFalse() {
		this.cenario.ocorrer(false);
		assertFalse(this.cenario.isOcorreu());
	}
		

	@Test
	public void testCalculaCaixa() {
		this.cenario.apostar("Samuel", 100, true);
		assertEquals(10, this.cenario.calculaCaixa(0.10));
	}

	@Test
	public void testGetCaixa() {
		this.cenario.apostar("Samuel", 10, true);
		this.cenario.apostar("Maria", 100, false);
		assertEquals(110, this.cenario.getCaixa());
	}

	@Test
	public void testValorTotalDeApostas() {
		this.cenario.apostar("Samuel", 10, true);
		this.cenario.apostar("Maria", 100, false);
		assertEquals(110, this.cenario.valorTotalDeApostas());
	}

	@Test
	public void testExibiApostas() {
		this.cenario.apostar("Samuel", 10, true);
		this.cenario.apostar("Maria", 100, false);
		
		String str  = "Samuel - R$0,10 - VAI ACONTECER"+System.lineSeparator();
		str += "Maria - R$1,00 - N VAI ACONTECER";
		assertEquals(str, this.cenario.exibiApostas());		
	}
	
	@Test(expected = CenarioSemApostasException.class)
	public void testExibiApostasVazia() {
		this.cenario.exibiApostas();		
	}

}
