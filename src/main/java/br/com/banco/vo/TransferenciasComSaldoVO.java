package br.com.banco.vo;

import org.springframework.data.domain.Page;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransferenciasComSaldoVO {
	
	private Page<TransferenciaVO> transferencias;
	private Float saldoTotal;
	private Float saldoPeriodo;
	
	public TransferenciasComSaldoVO(Page<TransferenciaVO> voPage, Float saldoTotal, Float saldoPeriodo) {
		this.transferencias = voPage;
		this.saldoTotal = saldoTotal;
		this.saldoPeriodo = saldoPeriodo;
	}
}
