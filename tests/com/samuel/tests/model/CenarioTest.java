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

/**
 * Classe responsável por testar a classe Cenario
 * @author Samuel Pereira de Vasconcelos
 *
 */
public class CenarioTest {
	
	/**
	 * Cenário usado como base para os testes
	 */
	public Cenario cenario;

	/**
	 * Testa o contrutor e inicializa o cenário base
	 */
	@Before
	public void testCenario() {
		this.cenario = new Cenario(1, "Brasil Hexa");
	}
	
	/**
	 * Testa o contrutor com um id zerado
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testCenarioIdZerado() {
		this.cenario = new Cenario(0, "Brasil Hexa");
	}
	
	/**
	 * Testa o contrutor com um id negativo
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testCenarioIdNegativo() {
		this.cenario = new Cenario(-1, "Brasil Hexa");
	}
	
	/**
	 * Testa o contrutor com a descrição nula
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testCenarioDescricaoNull() {
		this.cenario = new Cenario(1, null);
	}
	
	/**
	 * Testa o contrutor com a descrição vazia
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testCenarioDescricaoVazia() {
		this.cenario = new Cenario(1, "");
	}

	/**
	 * Testa o encerramento de um cenário
	 */
	@Test
	public void testEncerrar() {
		this.cenario.encerrar();
		assertTrue(this.cenario.isEncerrado());
	}
	
	/**
	 * Testa o encerramento de um cenário já encerrado
	 */
	@Test(expected = CenarioJaEncerradoException.class)
	public void testEncerrarEncerrado() {
		this.cenario.encerrar();
		this.cenario.encerrar();
	}

	/**
	 * Testa o processo de verificar se um cenário está encerrado com um cenário não encerrado
	 */
	@Test
	public void testIsEncerradoFalse() {
		assertFalse(this.cenario.isEncerrado());
	}
	
	/**
	 * Testa o processo de verificar se um cenário está encerrado com um cenário encerrado
	 */
	@Test
	public void testIsEncerradoTrue() {
		this.cenario.encerrar();
		assertTrue(this.cenario.isEncerrado());
	}

	/**
	 * Testa o processo de verificar se um cenário ocorreu com um cenário que ocorreu
	 */
	@Test
	public void testOcorrerTrue() {
		this.cenario.ocorrer(true);
		assertTrue(this.cenario.isOcorreu());
	}
	
	/**
	 * Testa o processo de verificar se um cenário ocorreu com um cenário que não ocorreu
	 */
	@Test
	public void testOcorrerFalse() {
		this.cenario.ocorrer(false);
		assertFalse(this.cenario.isOcorreu());
	}
	
	/**
	 * Testa o processo de tornar um processo ocorrido ou não com um processo que já se encerrou
	 */
	@Test(expected = CenarioJaEncerradoException.class)
	public void testOcorrerOcorrido() {
		this.cenario.ocorrer(false);
		this.cenario.ocorrer(false);
	}

	/**
	 * Testa o toString quand o cenário está finalizado
	 */
	@Test
	public void testToStringNaoFinalizado() {
		assertEquals("1 - Brasil Hexa - Nao finalizado", this.cenario.toString());
	}
	
	@Test
	public void testToStringFinalizadoOcorrido() {
		this.cenario.ocorrer(true);
		assertEquals("1 - Brasil Hexa - finalizado (ocorreu)", this.cenario.toString());
	}
	
	/**
	 * Testa o toString quando o cenário está finalizado e não ocorreu
	 */
	@Test
	public void testToStringFinalizadoNaoOcorrido() {
		this.cenario.ocorrer(false);
		assertEquals("1 - Brasil Hexa - finalizado (n ocorreu)", this.cenario.toString());
	}

	/**
	 * Testa o toString quando o cenário está finalizado e ocorreu
	 */
	@Test
	public void testGetId() {
		assertEquals(1, this.cenario.getId());
	}
	
	/**
	 * Testa o processo de apostar com o apostador nulo
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostarApostadorNull() {
		this.cenario.apostar(null, 1000, "VAI ACONTECER");
	}
	
	/**
	 * Testa o processo de apostar com o apostador vazio
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostarApostadorVazio() {
		this.cenario.apostar("", 1000, "VAI ACONTECER");
	}
	
	/**
	 * Testa o processo de apostar com o valor zerado
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostarValorZerado() {
		this.cenario.apostar("Samuel", 0, "VAI ACONTECER");
	}
	
	/**
	 * Testa o processo de apostar com o valor negativo
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostarValorNegativo() {
		this.cenario.apostar("Samuel", -100 , "VAI ACONTECER");
	}
	
	/**
	 * Testa o processo de apostar com a previsão nula
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostarPrevisaoNula() {
		this.cenario.apostar("Samuel", -100 , null);
	}
	
	/**
	 * Testa o processo de apostar com a previsão vazia
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostarPrevisaoVazia() {
		this.cenario.apostar("Samuel", -100 , "");
	}

	/**
	 * Testa o processo de capiturar as apostas de um ceário
	 */
	@Test
	public void testGetApostas() {
		List<Aposta> apostas = new ArrayList<Aposta>();
		
		Aposta aposta1 = new Aposta("Samuel", 10, true);
		Aposta aposta2 = new Aposta("Samuel", 10, false);
		
		apostas.add(aposta1);
		apostas.add(aposta2);
		
		this.cenario.apostar("Samuel", 10, "VAI ACONTECER");
		this.cenario.apostar("Samuel", 10, "N VAI ACONTECER");
		
		assertEquals(apostas, this.cenario.getApostas());
	}

	/**
	 * Testa o processo de modificar um cenário para ocorrido
	 */
	@Test
	public void testOcorreuTrue() {
		this.cenario.ocorrer(true);
		assertTrue(this.cenario.isOcorreu());
	}
	
	/**
	 * Testa o processo de modificar o cenário para não ocorrido
	 */
	@Test
	public void testOcorreuFalse() {
		this.cenario.ocorrer(false);
		assertFalse(this.cenario.isOcorreu());
	}
		
	/**
	 * Testa o calculo do caixa de um cenário
	 */
	@Test
	public void testCalculaCaixa() {
		this.cenario.apostar("Samuel", 100, "VAI ACONTECER");
		assertEquals(10, this.cenario.calculaCaixa(0.10));
	}

	/**
	 * Testa o processo que recupera o caia do sistema
	 */
	@Test
	public void testGetCaixaTotal() {
		this.cenario.apostar("Samuel", 10, "VAI ACONTECER");
		this.cenario.apostar("Maria", 100, "N VAI ACONTECER");
		assertEquals(110, this.cenario.getCaixaTotal());
	}

	/**
	 * Testa o processo que recupera o valor total acumulado com as apostas do cenário
	 */
	@Test
	public void testValorTotalDeApostas() {
		this.cenario.apostar("Samuel", 10, "VAI ACONTECER");
		this.cenario.apostar("Maria", 100, "N VAI ACONTECER");
		assertEquals(110, this.cenario.valorTotalDeApostas());
	}
	
	/**
	 * Testa o processo de exibir as apostas de um cenário
	 */
	@Test
	public void testExibiApostas() {
		this.cenario.apostar("Samuel", 10, "VAI ACONTECER");
		this.cenario.apostar("Maria", 100, "N VAI ACONTECER");
		
		String str  = "Samuel - R$0,10 - VAI ACONTECER"+System.lineSeparator();
		str += "Maria - R$1,00 - N VAI ACONTECER";
		assertEquals(str, this.cenario.exibiApostas());		
	}
	
	/**
	 * Testa o processo de exibir asapostas de um cenário quando ele não tem apostas cadastradas
	 */
	@Test(expected = CenarioSemApostasException.class)
	public void testExibiApostasVazia() {
		this.cenario.exibiApostas();		
	}

}
