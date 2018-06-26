package com.samuel.tests.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.samuel.lab.controller.ApostaController;
import com.samuel.lab.exception.ApostaNaoCadastradaException;
import com.samuel.lab.exception.CampoInvalidoException;
/**
 * Classe responsável por testar o controller de apostas
 * @author Samuel pereira de vasconcelos
 *
 */
public class ApostaControllerTest {
	
	/**
	 * Controlador de apostas utilizado como base para os testes
	 */
	private ApostaController apostaController;
	
	/**
	 * Testa o inicializador do controlador e inicializa o conrolador base
	 */
	@Before
	@Test
	public void testApostaController() {
		this.apostaController = new ApostaController();
	}

	/**
	 * Testa o cadastro de uma aposta com o apostador nulo
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testCadastrarNomeNull() {
		this.apostaController.cadastrar(null, 100, "VAI ACONTECER");
	}
	
	/**
	 * Testa o cadastro de uma aposta com apostador vazio
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testCadastrarNomeVazio() {
		this.apostaController.cadastrar(" ", 100, "VAI ACONTECER");
	}
	
	/**
	 * Testa o cadastro de uma aposta com o valor zerado
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testCadastrarValorZerado() {
		this.apostaController.cadastrar("Samuel", 0, "VAI ACONTECER");
	}
	
	/**
	 * Testa o cadastro de uma aposta com o valor negativo
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testCadastrarValorNegativo() {
		this.apostaController.cadastrar("Samuel", -10, "VAI ACONTECER");
	}
	
	/**
	 * Testa o cadastro de uma aposta com a previsao nula
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testCadastrarPrevisaoNull() {
		this.apostaController.cadastrar("Samuel", 10, null);
	}
	
	/**
	 * Testa o cadastro de uma aposta com a previsao vazia
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testCadastrarPrevisaoVazia() {
		this.apostaController.cadastrar("Samuel", 10, " ");
	}

	/**
	 * Testa o calculo das apostas perdedoras
	 */
	@Test
	public void testCalculaCaixaPerdedoras() {
		this.apostaController.cadastrar("Maria", 1000, "VAI ACONTECER");
		this.apostaController.cadastrar("Samuel", 2000, "VAI ACONTECER");
		assertEquals(300,this.apostaController.calculaCaixaPerdedoras(false, 0.1));
	}

	/**
	 * Testa o calculo do caixa das apostas
	 */
	@Test
	public void testCalculaCaixa() {
		this.apostaController.cadastrar("Maria", 1000, "VAI ACONTECER");
		this.apostaController.cadastrar("Samuel", 2000, "VAI ACONTECER");
		this.apostaController.cadastrar("Samuel", 100, "VAI ACONTECER", 0.4, 200);
		this.apostaController.cadastrar("Samuel", 100, "VAI ACONTECER", 500, 200);
		assertEquals(3200,this.apostaController.calculaCaixa(false));
	}

	/**
	 * Testa o metodo que recupera o vaor total das apostas
	 */
	@Test
	public void testGetValorTotal() {
		this.apostaController.cadastrar("Maria", 1000, "VAI ACONTECER");
		this.apostaController.cadastrar("Samuel", 2000, "VAI ACONTECER");
		this.apostaController.cadastrar("Samuel", 100, "VAI ACONTECER", 0.4, 200);
		this.apostaController.cadastrar("Samuel", 100, "VAI ACONTECER", 500, 200);
		this.apostaController.cadastrar("Samuel", 100, "N VAI ACONTECER", 500, 200);
		assertEquals(3300,this.apostaController.getValorTotal());
	}

	/**
	 * Testa a exibição de todas as apostas
	 */
	@Test
	public void testExibir() {
		this.apostaController.cadastrar("Maria", 1000, "VAI ACONTECER");
		this.apostaController.cadastrar("Samuel", 2000, "VAI ACONTECER");
		this.apostaController.cadastrar("Samuel", 100, "VAI ACONTECER", 0.4, 200);
		this.apostaController.cadastrar("Samuel", 100, "VAI ACONTECER", 500, 200);
		this.apostaController.cadastrar("Samuel", 100, "N VAI ACONTECER", 500, 200);
		
		String str = "Maria - R$10,00 - VAI ACONTECER" + System.lineSeparator()
			+ "Samuel - R$20,00 - VAI ACONTECER" + System.lineSeparator()
			+ "Samuel - R$1,00 - VAI ACONTECER - ASSEGURADA (VALOR) - R$ 500,00" + System.lineSeparator()
			+ "Samuel - R$1,00 - N VAI ACONTECER - ASSEGURADA (VALOR) - R$ 500,00" + System.lineSeparator()
			+ "Samuel - R$1,00 - VAI ACONTECER - ASSEGURADA (TAXA) - R$ 40,00";

		assertEquals(str,this.apostaController.exibir());
	}

	/**
	 * Testa o método que recupera a quantidade de apostas cadastradas
	 */
	@Test
	public void testGetQuantidade() {
		this.apostaController.cadastrar("Maria", 1000, "VAI ACONTECER");
		this.apostaController.cadastrar("Samuel", 2000, "VAI ACONTECER");
		this.apostaController.cadastrar("Samuel", 100, "VAI ACONTECER", 0.4, 200);
		this.apostaController.cadastrar("Samuel", 100, "VAI ACONTECER", 500, 200);
		this.apostaController.cadastrar("Samuel", 100, "N VAI ACONTECER", 500, 200);
		assertEquals(5, this.apostaController.getQuantidade());
	}

	/**
	 * Testa o método d alterar uma aposta quando ela ainda não foi cadastrada
	 */
	@Test(expected = ApostaNaoCadastradaException.class)
	public void testAlterarApostaNaoCadastrada() {
		this.apostaController.alterar(1, 0.1);
	}
	
	/**
	 * Testa a alteração de uma aposta com a taxa inválida
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testAlterarTaxaInvalida() {
		this.apostaController.cadastrar("Samuel", 100, "N VAI ACONTECER", 500, 200);
		this.apostaController.alterar(1, 0.0);
	}

	/**
	 * Testa a alteração de uma aposta com o seguro iválido
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testAlterarSeguroInvalido() {
		this.apostaController.cadastrar("Samuel", 100, "N VAI ACONTECER", 50.80, 200);
		this.apostaController.alterar(1, 0);
	}

	/**
	 * Testa o calculo do seguro das apostas perdedoras
	 */
	@Test
	public void testSeguroPerdedoras() {
		this.apostaController.cadastrar("Maria", 1000, "VAI ACONTECER");
		this.apostaController.cadastrar("Samuel", 2000, "N VAI ACONTECER");
		this.apostaController.cadastrar("Samuel", 100, "VAI ACONTECER", 0.4, 200);
		this.apostaController.cadastrar("Samuel", 100, "VAI ACONTECER", 500, 200);
		this.apostaController.cadastrar("Samuel", 100, "N VAI ACONTECER", 500, 200);
		assertEquals(540,this.apostaController.seguroPerdedoras(false));
	}

	/**
	 * Testa o método de recuperação dos custos de todas as apostas asseguradas
	 */
	@Test
	public void testGetCustos() {
		this.apostaController.cadastrar("Maria", 1000, "VAI ACONTECER");
		this.apostaController.cadastrar("Samuel", 2000, "N VAI ACONTECER");
		this.apostaController.cadastrar("Samuel", 100, "VAI ACONTECER", 0.4, 200);
		this.apostaController.cadastrar("Samuel", 100, "VAI ACONTECER", 500, 200);
		this.apostaController.cadastrar("Samuel", 100, "N VAI ACONTECER", 500, 200);
		assertEquals(600,this.apostaController.getCustos());
	}

}
