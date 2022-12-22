package br.com.banco.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContaVO {
	
	private Long idConta;
	private String nomeResponsavel;
}
