package br.com.banco.vo;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransferenciaVO {
	
	private Long id;
	private LocalDateTime dataTransferencia;
	private Float valor;
	private String tipo;
	private String nomeOperadorTransacao;
	
	public TransferenciaVO(Long id, LocalDateTime dataTransferencia, Float valor, String tipo,
			String nomeOperadorTransacao) {
		
		this.id = id;
		this.dataTransferencia = dataTransferencia;
		this.valor = valor;
		this.tipo = tipo;
		this.nomeOperadorTransacao = nomeOperadorTransacao;
	}
}
