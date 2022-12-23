package br.com.banco.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransferenciaNovaVO {
	
	private Float valor;
	private String tipo;
	private String nomeOperadorTransacao;
	private Long idConta;
	private String nomeResponsavel;
	
	public TransferenciaNovaVO(Float valor, String tipo, String nomeOperadorTransacao, Long idConta,
			String nomeResponsavel) {
		this.valor = valor;
		this.tipo = tipo;
		this.nomeOperadorTransacao = nomeOperadorTransacao;
		this.idConta = idConta;
		this.nomeResponsavel = nomeResponsavel;
	}
	
	
}
