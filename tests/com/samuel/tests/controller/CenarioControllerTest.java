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

public class CenarioControllerTest {
	
	private CenarioController cenarioController;

	@Before
	public void testCenarioController() {
		this.cenarioController = new CenarioController(100, 0.10);
	}

	@Test(expected = CampoInvalidoException.class)
	public void testCadastrarNull() {
		this.cenarioController.cadastrar(null);
	}
	
	@Test(expected = CampoInvalidoException.class)
	public void testCadastrarVazio() {
		this.cenarioController.cadastrar("");
	}

	@Test
	public void testExibirCenario() {
		this.cenarioController.cadastrar("Brasil Hexa");
		assertEquals("1 - Brasil Hexa - Não Finalizado", this.cenarioController.exibirCenario(1));
	}
	
	@Test
	public void testExibirCenarioFilalizadoOcorrido() {
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.fecharAposta(1, true);
		assertEquals("1 - Brasil Hexa - Finalizado (ocorreu)", this.cenarioController.exibirCenario(1));
	}
	
	@Test
	public void testExibirCenarioFilalizadoNaoOcorrido() {
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.fecharAposta(1, false);
		assertEquals("1 - Brasil Hexa - Finalizado (n ocorreu)", this.cenarioController.exibirCenario(1));
	}
	
	@Test(expected = CenarioNaoCadastradoException.class)
	public void testExibirCenarioNaoCadastrado() {
		this.cenarioController.fecharAposta(1, false);
	}
	
	@Test(expected = CampoInvalidoException.class)
	public void testExibirCenarioIdInvalido() {
		this.cenarioController.fecharAposta(0, false);
	}
	
	@Test(expected = CampoInvalidoException.class)
	public void testExibirCenarioIdNegativo() {
		this.cenarioController.fecharAposta(-1, false);
	}
	

	@Test
	public void testExibirCenarios() {
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.cadastrar("Palmeiras Sem Mundial");
		
		String str = "1 - Brasil Hexa - Não Finalizado" + System.lineSeparator();
		str += "2 - Palmeiras Sem Mundial - Não Finalizado";
		assertEquals(str, this.cenarioController.exibirCenarios());
	}

	@Test(expected = CampoInvalidoException.class)
	public void testApostarApostadorNull() {
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.apostar(1, null, 1000, true);
	}
	
	@Test(expected = CampoInvalidoException.class)
	public void testApostarApostadorVazio() {
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.apostar(1, "", 1000, true);
	}
	
	@Test(expected = CampoInvalidoException.class)
	public void testApostarValorNegativo() {
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.apostar(1, "Samuel", -1000, true);
	}
	
	@Test(expected = CampoInvalidoException.class)
	public void testApostarValorZerado() {
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.apostar(1, "Samuel", 0, true);
	}
	

	@Test
	public void testValorTotal() {
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.apostar(1, "Samuel", 100, false);
		assertEquals(100, this.cenarioController.valorTotal(1));
	}
	
	@Test(expected = CenarioNaoCadastradoException.class)
	public void testValorTotalCenarioNaoCadastrado() {
		this.cenarioController.valorTotal(1);
	}
	
	@Test(expected = CenarioSemApostasException.class)
	public void testValorTotalCenarioSemApostas() {
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.valorTotal(1);
	}

	@Test
	public void testTotalApostas() {
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.apostar(1, "Samuel", 100, false);
		this.cenarioController.apostar(1, "Samuel", 100, true);
		assertEquals(2, this.cenarioController.totalApostas(1));
	}
	
	@Test(expected = CenarioNaoCadastradoException.class)
	public void testTotalApostasCenarioNaoCadastrado() {
		this.cenarioController.totalApostas(1);
	}
	
	@Test(expected = CenarioSemApostasException.class)
	public void testTotalApostasCenarioSemApostas() {
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.totalApostas(1);
	}

	@Test
	public void testExibirApostas() {
		String str = "Samuel - R$1,00 - N VAI ACONTECER" + System.lineSeparator();
		str += "Maria - R$1,00 - VAI ACONTECER";
		
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.apostar(1, "Samuel", 100, false);
		this.cenarioController.apostar(1, "Maria", 100, true);
		
		assertEquals(str, this.cenarioController.exibirApostas(1));
		
	}
	
	@Test(expected = CenarioNaoCadastradoException.class)
	public void testExibirApostasCenarioNaoCadastrado() {
		this.cenarioController.exibirApostas(1);
	}
	
	@Test(expected = CenarioSemApostasException.class)
	public void testExibirApostasCenarioSemApostas() {	
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.exibirApostas(1);
	}

	@Test(expected = CenarioNaoCadastradoException.class)
	public void testFecharApostaCenarioNaoCadastrado() {
		this.cenarioController.fecharAposta(1, true);
	}
	
	@Test(expected = CenarioJaEncerradoException.class)
	public void testFecharApostaCenarioFinalizado() {
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.fecharAposta(1, true);
		this.cenarioController.fecharAposta(1, false);
	}

	@Test
	public void testGetCaixaInt() {
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.apostar(1, "Samuel", 2000, true);
		this.cenarioController.fecharAposta(1, true);
		assertEquals(200, this.cenarioController.getCaixa(1));
	}
	
	@Test(expected = CenarioNaoCadastradoException.class)
	public void testGetCaixaIntCenarioNaoCadastrado() {
		this.cenarioController.getCaixa(1);
	}
	
	@Test(expected = CenarioSemApostasException.class)
	public void testGetCaixaIntcenarioSemApostas() {
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.fecharAposta(1, true);
		this.cenarioController.getCaixa(1);
	}

	@Test
	public void testGetTotalRateio() {
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.apostar(1, "Samuel", 2000, true);
		this.cenarioController.fecharAposta(1, true);
		assertEquals(1800, this.cenarioController.getTotalRateio(1));
	}
	
	@Test(expected = CenarioNaoCadastradoException.class)
	public void testGetTotalRateioCenarioNaoCadastrado() {
		this.cenarioController.getTotalRateio(1);
	}
	
	@Test(expected = CenarioSemApostasException.class)
	public void testGetTotalRateioCenarioSemApostas() {
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.fecharAposta(1, true);
		this.cenarioController.getTotalRateio(1);
	}
	
	@Test(expected = CenarioNaoEncerradoException.class)
	public void testGetTotalRateioCenarioNaoEncerrado() {
		this.cenarioController.cadastrar("Brasil Hexa");
		this.cenarioController.apostar(1, "Samuel", 1000, true);
		this.cenarioController.getTotalRateio(1);
	}

	@Test
	public void testGetCaixa() {
		assertEquals(100, this.cenarioController.getCaixa());
	}

}
