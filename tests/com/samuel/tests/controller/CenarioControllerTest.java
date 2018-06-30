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
		this.cenarioController.cadastrarCenario(null);
	}
	
	/**
	 * Testa o cadastramento de um cenário com a desrição vazia
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testCadastrarVazio() {
		this.cenarioController.cadastrarCenario(" ");
	}
	
	/**
	 * Testa a exibição de um cenário não finalizado
	 */
	@Test
	public void testExibirCenario() {
		this.cenarioController.cadastrarCenario("Brasil Hexa");
		assertEquals("1 - Brasil Hexa - Nao finalizado", this.cenarioController.exibirCenario(1));
	}
	
	/**
	 * Testa a exibição de um cenário que foi finalizado e ocorreu
	 */
	@Test
	public void testExibirCenarioFilalizadoOcorrido() {
		this.cenarioController.cadastrarCenario("Brasil Hexa");
		this.cenarioController.fecharAposta(1, true);
		assertEquals("1 - Brasil Hexa - Finalizado (ocorreu)", this.cenarioController.exibirCenario(1));
	}
	
	/**
	 * Testa a exibição de um cenário que foi finalizado e não ocorreu
	 */
	@Test
	public void testExibirCenarioFilalizadoNaoOcorrido() {
		this.cenarioController.cadastrarCenario("Brasil Hexa");
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
	 * Testa a exibição de um cenário com id inválido
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testExibirCenarioInvalido() {
		this.cenarioController.exibirCenario(-1);
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
		this.cenarioController.cadastrarCenario("Brasil Hexa");
		this.cenarioController.cadastrarCenario("Palmeiras Sem Mundial");
		
		String str = "1 - Brasil Hexa - Nao finalizado" + System.lineSeparator();
		str += "2 - Palmeiras Sem Mundial - Nao finalizado";
		assertEquals(str, this.cenarioController.exibirCenarios());
	}

	/**
	 * Testa o processo de aposta com o apostador nulo
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostarApostadorNull() {
		this.cenarioController.cadastrarCenario("Brasil Hexa");
		this.cenarioController.cadastrarAposta(1, null, 1000, "VAI ACONTECER");
	}
	
	/**
	 * Testa o processo de aposta com o apostador vazio
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostarApostadorVazio() {
		this.cenarioController.cadastrarCenario("Brasil Hexa");
		this.cenarioController.cadastrarAposta(1, " ", 1000, "VAI ACONTECER");
	}
	
	/**
	 * Testa o processo de aposta com o valor da aposta negativo
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostarValorNegativo() {
		this.cenarioController.cadastrarCenario("Brasil Hexa");
		this.cenarioController.cadastrarAposta(1, "Samuel", -1000, "VAI ACONTECER");
	}
	
	/**
	 * Testa o processo de aposta com o valor da aposta zerado
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostarValorZerado() {
		this.cenarioController.cadastrarCenario("Brasil Hexa");
		this.cenarioController.cadastrarAposta(1, "Samuel", 0, "VAI ACONTECER");
	}
	
	/**
	 * Testa o processo de aposta com o valor da aposta zerado
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostarPrevisaoNula() {
		this.cenarioController.cadastrarCenario("Brasil Hexa");
		this.cenarioController.cadastrarAposta(1, "Samuel", 0, null);
	}
	
	/**
	 * Testa o processo de aposta com a previsão vazia
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testApostarPrevisaoVazia() {
		this.cenarioController.cadastrarCenario("Brasil Hexa");
		this.cenarioController.cadastrarAposta(1, "Samuel", 0, " ");
	}
	
	/**
	 * Testa o carregamento do valor total de um cenário
	 */
	@Test
	public void testValorTotal() {
		this.cenarioController.cadastrarCenario("Brasil Hexa");
		this.cenarioController.cadastrarAposta(1, "Samuel", 100,"N VAI ACONTECER");
		assertEquals(100, this.cenarioController.valorTotalDeApostas(1));
	}
	
	/**
	 * Testa o carregamento do valor total de um cenário sem que ele esteja cadastrado
	 */
	@Test(expected = CenarioNaoCadastradoException.class)
	public void testValorTotalCenarioNaoCadastrado() {
		this.cenarioController.valorTotalDeApostas(1);
	}
	
	/**
	 * Testa o carregamento do valor total de um cenário inválido
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testValorTotalCenarioInvalido() {
		this.cenarioController.valorTotalDeApostas(-1);
	}

	/**
	 * Testa o carregamento do total de apostas cadastrada em um cenário
	 */
	@Test
	public void testTotalApostas() {
		this.cenarioController.cadastrarCenario("Brasil Hexa");
		this.cenarioController.cadastrarAposta(1, "Samuel", 100,"N VAI ACONTECER");
		this.cenarioController.cadastrarAposta(1, "Samuel", 100,"VAI ACONTECER");
		this.cenarioController.cadastrarAposta(1, "Samuel", 100, "VAI ACONTECER", 0.3, 100);
		this.cenarioController.cadastrarAposta(1, "Samuel", 100, "VAI ACONTECER", 30, 100);
		assertEquals(4, this.cenarioController.totalDeApostas(1));
	}
	
	/**
	 * Testa o carregamento do total de apostas cadastradas de um cenário não cadastrado 
	 */
	@Test(expected = CenarioNaoCadastradoException.class)
	public void testTotalApostasCenarioNaoCadastrado() {
		this.cenarioController.totalDeApostas(1);
	}
	
	/**
	 * Testa o carregamento do total de apostas cadastradas de um cenário inválido 
	 */
	@Test(expected = CampoInvalidoException.class)
	public void testTotalApostasCenarioInvalido() {
		this.cenarioController.totalDeApostas(-1);
	}

	/**
	 * Testa a exibição de todas as apostas de um cenário
	 */
	@Test
	public void testExibirApostas() {
		String str = "Samuel - R$1,00 - N VAI ACONTECER" + System.lineSeparator();
		str += "Samuel - R$1,00 - VAI ACONTECER - ASSEGURADA (TAXA) - R$ 30,00";
		
		this.cenarioController.cadastrarCenario("Brasil Hexa");
		this.cenarioController.cadastrarAposta(1, "Samuel", 100, "N VAI ACONTECER");
		this.cenarioController.cadastrarAposta(1, "Samuel", 100, "VAI ACONTECER", 0.3, 100);
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
		this.cenarioController.cadastrarCenario("Brasil Hexa");
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
		this.cenarioController.cadastrarCenario("Brasil Hexa");
		this.cenarioController.fecharAposta(1, true);
		this.cenarioController.fecharAposta(1, false);
	}

	/**
	 * Testa o carregamento do caixa  de um cenário que será destinado ao cofre do sistema
	 */
	@Test
	public void testGetCaixaInt() {
		this.cenarioController.cadastrarCenario("Brasil Hexa");
		this.cenarioController.cadastrarAposta(1, "Samuel", 2000, "VAI ACONTECER");
		this.cenarioController.cadastrarAposta(1, "Samuel", 2000, "N VAI ACONTECER");
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
	 * Testa o carregamento do rateio de um cenário
	 */
	@Test
	public void testGetTotalRateio() {
		this.cenarioController.cadastrarCenario("Brasil Hexa");
		this.cenarioController.cadastrarAposta(1, "Samuel", 2000, "VAI ACONTECER");
		this.cenarioController.cadastrarAposta(1, "Samuel", 2000, "N VAI ACONTECER");
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
	 * Testa o carregamento do rateio de um cenário não encerrado
	 */
	@Test(expected = CenarioNaoEncerradoException.class)
	public void testGetTotalRateioCenarioNaoEncerrado() {
		this.cenarioController.cadastrarCenario("Brasil Hexa");
		this.cenarioController.cadastrarAposta(1, "Samuel", 1000, "VAI ACONTECER");
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
