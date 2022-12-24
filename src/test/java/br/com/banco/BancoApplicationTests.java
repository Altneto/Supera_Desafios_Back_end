package br.com.banco;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.banco.services.ContaService;
import br.com.banco.services.TransferenciaService;
import br.com.banco.vo.ContaNovaVO;
import br.com.banco.vo.ContaVO;
import br.com.banco.vo.TransferenciaNovaVO;
import br.com.banco.vo.TransferenciaVO;

@SpringBootTest
class BancoApplicationTests {
	
	@Autowired
	private ContaService contaService;
	
	@Autowired
	private TransferenciaService transferenciaService;

	@Test
	public ContaVO testarCadastroContaComSucesso() {
		
		ContaNovaVO vo = new ContaNovaVO();
		vo.setNomeResponsavel("Responsavel");

		ContaVO contaVO = new ContaVO();
		contaVO = contaService.save(vo);

		assertThat(contaVO).isNotNull();
		assertThat(contaVO.getIdConta()).isNotNull();
		assertThat(contaVO.getNomeResponsavel()).isNotNull();
		
		return contaVO;
	}
	
	@Test
	public void testarCadastroTransferenciaComSucesso() {
		
		ContaVO conta = testarCadastroContaComSucesso();
		
		TransferenciaNovaVO vo = new TransferenciaNovaVO();
		vo.setValor(500.00f);
		vo.setTipo("Deposito");
		vo.setNomeOperadorTransacao("Responsavel");
		vo.setIdConta(conta.getIdConta());
		vo.setNomeResponsavel(conta.getNomeResponsavel());
		
		TransferenciaVO transferenciaVO = new TransferenciaVO();
		transferenciaVO = transferenciaService.save(vo);

		assertThat(transferenciaVO).isNotNull();
		assertThat(transferenciaVO.getId()).isNotNull();
		assertThat(transferenciaVO.getDataTransferencia()).isNotNull();
		assertThat(transferenciaVO.getValor()).isNotNull();
		assertThat(transferenciaVO.getTipo()).isNotNull();
		assertThat(transferenciaVO.getNomeOperadorTransacao()).isNotNull();
	}
}
