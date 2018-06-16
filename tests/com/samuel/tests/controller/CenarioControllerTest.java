package com.samuel.tests.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.samuel.lab.controller.CenarioController;
import com.samuel.lab.exception.CampoInvalidoException;
import com.samuel.lab.exception.CenarioJaEncerradoException;
import com.samuel.lab.exception.CenarioNaoCadastradoException;
import com.samuel.lab.exception.CenarioNaoEncerradoException;
import com.samuel.lab.exception.CenarioSemApostasException;

/**
 * Classe responsável por testar a classe CenarioController 
 * @author Samuel Pereira de Vasconcelos
 *
 */
public class CenarioControllerTest {
	
	/**
	 * Cenario controller utilizado como base para os testes
	 */
	private CenarioController cenarioController;

	/**
	 * Inicializa o cenarioControle e testa o construtor
	 */
	@Before
	public void testCenarioController() {
		this.cenarioController = new CenarioController(100, 0.10);
	}
	
	/**
	 * Testa o cadastramento de um cenário com a descrição nula
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testCadastrarNull() {
		this.cenarioController.cadastrar(null);
	}
	
	/**
	 * Testa o cadastramento de um cenário com a desrição vazia
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testCadastrarVazio() {
		this.cenarioController.cadastrar("");
	}
	
	/**
	 * Testa a exibição de um cenário não finalizado
	 */
	@Test
	public void testExibirCenario() {
		this.cenarioController.cadastrar("Brasil Hexa");
		assertEquals("1 - Brasil Hexa - Não Finalizado", this.cenarioController.exibirCenario(1));
	}
	
	/**
	 * Testa a exibição de um cenário que foi finalizado e ocorreu
	 */
	@Test
	public void testExibirCenarioFilalizadoOcorrido() {
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.fecharAposta(1, true);
		assertEquals("1 - Brasil Hexa - Finalizado (ocorreu)", this.cenarioController.exibirCenario(1));
	}
	
	/**
	 * Testa a exibição de um cenário que foi finalizado e não ocorreu
	 */
	@Test
	public void testExibirCenarioFilalizadoNaoOcorrido() {
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.fecharAposta(1, false);
		assertEquals("1 - Brasil Hexa - Finalizado (n ocorreu)", this.cenarioController.exibirCenario(1));
	}
	
	/**
	 * Testa a exibição de um cenário que não foi cadastrado 
	 */
	@Test(expected = CenarioNaoCadastradoException.class)
	public void testExibirCenarioNaoCadastrado() {
		this.cenarioController.exibirCenario(1);
	}
	
	/**
	 * Testa a exibição de um cenário passando o id = 0
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testExibirCenarioIdInvalido() {
		this.cenarioController.exibirCenario(0);
	}
	
	/**
	 * Testa a exibição de um cenário passando o id negativo
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testExibirCenarioIdNegativo() {
		this.cenarioController.exibirCenario(-1);
	}
	
	/**
	 * Testa a exibição de todos os cenários
	 */
	@Test
	public void testExibirCenarios() {
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.cadastrar("Palmeiras Sem Mundial");
		
		String str = "1 - Brasil Hexa - Não Finalizado" + System.lineSeparator();
		str += "2 - Palmeiras Sem Mundial - Não Finalizado";
		assertEquals(str, this.cenarioController.exibirCenarios());
	}

	/**
	 * Testa o processo de aposta com o apostador nulo
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostarApostadorNull() {
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.apostar(1, null, 1000, true);
	}
	
	/**
	 * Testa o processo de aposta com o apostador vazio
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostarApostadorVazio() {
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.apostar(1, "", 1000, true);
	}
	
	/**
	 * Testa o processo de aposta com o valor da aposta negativo
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostarValorNegativo() {
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.apostar(1, "Samuel", -1000, true);
	}
	
	/**
	 * Testa o vprocesso de aposta com o valor da aposta zerado
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostarValorZerado() {
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.apostar(1, "Samuel", 0, true);
	}
	
	
	/**
	 * Testa o carregamento do valor total de um cenário
	 */
	@Test
	public void testValorTotal() {
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.apostar(1, "Samuel", 100, false);
		assertEquals(100, this.cenarioController.valorTotal(1));
	}
	
	/**
	 * Testa o carregamento do valor total de um cenário sem que ele esteja cadastrado
	 */
	@Test(expected = CenarioNaoCadastradoException.class)
	public void testValorTotalCenarioNaoCadastrado() {
		this.cenarioController.valorTotal(1);
	}
	
	/**
	 * Testa o carregamento do valor total de um cenário sem que ele tenha apostas cadastradas
	 */
	@Test(expected = CenarioSemApostasException.class)
	public void testValorTotalCenarioSemApostas() {
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.valorTotal(1);
	}

	/**
	 * Testa o carregamento do total de apostas cadastrada em um cenário
	 */
	@Test
	public void testTotalApostas() {
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.apostar(1, "Samuel", 100, false);
		this.cenarioController.apostar(1, "Samuel", 100, true);
		assertEquals(2, this.cenarioController.totalApostas(1));
	}
	
	/**
	 * Testa o carregamento do total de apostas cadastradas de um cenário não cadastrado 
	 */
	@Test(expected = CenarioNaoCadastradoException.class)
	public void testTotalApostasCenarioNaoCadastrado() {
		this.cenarioController.totalApostas(1);
	}
	
	/**
	 * Testa o carregamento do total de apostas de um cenario não cadastrado
	 */
	@Test(expected = CenarioSemApostasException.class)
	public void testTotalApostasCenarioSemApostas() {
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.totalApostas(1);
	}

	/**
	 * Testa a exibição de todas as apostas de um cenário
	 */
	@Test
	public void testExibirApostas() {
		String str = "Samuel - R$1,00 - N VAI ACONTECER" + System.lineSeparator();
		str += "Maria - R$1,00 - VAI ACONTECER";
		
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.apostar(1, "Samuel", 100, false);
		this.cenarioController.apostar(1, "Maria", 100, true);
		
		assertEquals(str, this.cenarioController.exibirApostas(1));
		
	}
	
	/**
	 * Testa a exibição das apostas de um cenário não cadastrados
	 */
	@Test(expected = CenarioNaoCadastradoException.class)
	public void testExibirApostasCenarioNaoCadastrado() {
		this.cenarioController.exibirApostas(1);
	}
	
	/**
	 * Testa a exibição de todas as apostas de um cenário que não possui apostas cadastradas
	 */
	@Test(expected = CenarioSemApostasException.class)
	public void testExibirApostasCenarioSemApostas() {	
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.exibirApostas(1);
	}

	/**
	 * Testa o fechamento de um cenário que não foi cadastrado
	 */
	@Test(expected = CenarioNaoCadastradoException.class)
	public void testFecharApostaCenarioNaoCadastrado() {
		this.cenarioController.fecharAposta(1, true);
	}
	
	/**
	 * Testa o fechamento de um cenário já encerrado
	 */
	@Test(expected = CenarioJaEncerradoException.class)
	public void testFecharApostaCenarioFinalizado() {
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.fecharAposta(1, true);
		this.cenarioController.fecharAposta(1, false);
	}

	/**
	 * Testa o carregamento do caixa  de um cenário que será destinado ao cofre do sistema
	 */
	@Test
	public void testGetCaixaInt() {
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.apostar(1, "Samuel", 2000, true);
		this.cenarioController.fecharAposta(1, true);
		assertEquals(200, this.cenarioController.getCaixaCenario(1));
	}

	/**
	 * Testa o carregamento do caixa  de um cenário que será destinado ao cofre do sistema com o cenário não cadastrado
	 */
	@Test(expected = CenarioNaoCadastradoException.class)
	public void testGetCaixaIntCenarioNaoCadastrado() {
		this.cenarioController.getCaixaCenario(1);
	}
	

	/**
	 * Testa o carregamento do caixa  de um cenário que será destinado ao cofre do sistema com o cenário sem apostas
	 */
	@Test(expected = CenarioSemApostasException.class)
	public void testGetCaixaIntcenarioSemApostas() {
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.fecharAposta(1, true);
		this.cenarioController.getCaixaCenario(1);
	}


	/**
	 * Testa o carregamento do rateio de um cenário
	 */
	@Test
	public void testGetTotalRateio() {
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.apostar(1, "Samuel", 2000, true);
		this.cenarioController.fecharAposta(1, true);
		assertEquals(1800, this.cenarioController.getTotalRateio(1));
	}
	

	/**
	 * Testa o carregamento do rateio de um cenário não cadastrado
	 */
	@Test(expected = CenarioNaoCadastradoException.class)
	public void testGetTotalRateioCenarioNaoCadastrado() {
		this.cenarioController.getTotalRateio(1);
	}
	

	/**
	 * Testa o carregamento do rateiro de um cenário sem apostas 
	 */
	@Test(expected = CenarioSemApostasException.class)
	public void testGetTotalRateioCenarioSemApostas() {
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.fecharAposta(1, true);
		this.cenarioController.getTotalRateio(1);
	}
	

	/**
	 * Testa o carregamento do rateio de um cenário não encerrado
	 */
	@Test(expected = CenarioNaoEncerradoException.class)
	public void testGetTotalRateioCenarioNaoEncerrado() {
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.apostar(1, "Samuel", 1000, true);
		this.cenarioController.getTotalRateio(1);
	}


	/**
	 * Testa o carregamento do caixa  do sistema
	 */
	@Test
	public void testGetCaixa() {
		assertEquals(100, this.cenarioController.getCaixa());
	}

}
