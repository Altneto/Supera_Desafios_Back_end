package br.com.banco.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transferencia implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "DATA_TRANSFERENCIA", nullable = false)
	private LocalDateTime dataTransferencia;
	
	@Column(name = "VALOR", precision = 20, scale = 2, nullable = false)
	private Float valor;
	
	@Column(name = "TIPO", length = 15, nullable =  false)
	private String tipo;
	
	@Column(name = "NOME_OPERADOR_TRANSACAO", length = 50)
	private String nomeOperadorTransacao;
	
	@ManyToOne
	@JoinColumn(name = "CONTA_ID", nullable = false)
	private Conta conta;
	
	@Column(name = "NOME_RESPONSAVEL")
	private String nomeResponsavel;
}
