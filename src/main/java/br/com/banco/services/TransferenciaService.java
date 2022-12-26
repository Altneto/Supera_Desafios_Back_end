package br.com.banco.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.banco.exceptions.DataNotFoundException;
import br.com.banco.exceptions.ParamNotFoundException;
import br.com.banco.models.Conta;
import br.com.banco.models.Transferencia;
import br.com.banco.repositories.ContaRepository;
import br.com.banco.repositories.TransferenciaRepository;
import br.com.banco.vo.TransferenciaNovaVO;
import br.com.banco.vo.TransferenciaVO;
import br.com.banco.vo.TransferenciasComSaldoVO;

@Service
public class TransferenciaService {
	
	@Autowired
	private TransferenciaRepository transferenciaRepository;
	
	@Autowired
	private ContaRepository contaRepository;
	
	public TransferenciaVO save(TransferenciaNovaVO vo) {
		
		Optional<Conta> conta = Optional.ofNullable(
				contaRepository.findById(vo.getIdConta()).orElseThrow(
						() -> new DataNotFoundException()));
		
		
		Transferencia transferencia = new Transferencia(
				LocalDateTime.now(), 
				vo.getValor(), 
				vo.getTipo(), 
				vo.getNomeOperadorTransacao(), 
				conta.get(), 
				vo.getNomeResponsavel());
		
		transferenciaRepository.save(transferencia);
		
		return new TransferenciaVO(transferencia.getId(), 
				transferencia.getDataTransferencia().toString(), 
				transferencia.getValor(), 
				transferencia.getTipo(), 
				transferencia.getNomeOperadorTransacao());
	}
	
	public TransferenciasComSaldoVO search(
            String dataInicio,
            String dataFim,
            String nomeDoOperador,
            int page,
            int size
    ) {
		LocalDateTime dataDeInicio = null;
		LocalDateTime dataDeFim = null;
		
		PageRequest pageRequest = PageRequest.of(page, size);
        int length = transferenciaRepository.findAll().size();
        List<Transferencia> transferencias = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		
		if (dataInicio != "" && dataInicio != null && nomeDoOperador != "" && nomeDoOperador != null) {
			
			dataDeInicio = LocalDateTime.parse(dataInicio, formatter);
			
			if (dataFim == "" && dataInicio != null) {
				throw new ParamNotFoundException("Data final não inserida");
			}
			
			dataDeFim = LocalDateTime.parse(dataFim, formatter);
			
			transferencias = transferenciaRepository.search(
	        		dataDeInicio, 
	        		dataDeFim, 
	        		nomeDoOperador);
			
		} else if (dataInicio != "" && dataInicio != null) {
			
			dataDeInicio = LocalDateTime.parse(dataInicio, formatter);
			
			if (dataFim == "" && dataInicio != null) {
				throw new ParamNotFoundException("Data final não inserida");
			}
			
			dataDeFim = LocalDateTime.parse(dataFim, formatter);
			
			transferencias = transferenciaRepository.search1(
					dataDeInicio, 
					dataDeFim);
			
		} else if (nomeDoOperador != "" && nomeDoOperador != null) {
			
			transferencias = transferenciaRepository.search2(
	        		nomeDoOperador);						
		} else {
			transferencias = transferenciaRepository.search3();
		}
		
        List<TransferenciaVO> vos = new ArrayList<>();
        transferencias.forEach((transferencia -> {
            vos.add(transferenciaToVO(transferencia));
        }));
        
        PagedListHolder<TransferenciaVO> paged = new PagedListHolder<>(vos);
        paged.setPageSize(size);
        paged.setPage(page);
        
        Page<TransferenciaVO> voPage = new PageImpl<>(paged.getPageList(), pageRequest, length);
        
        
        return new TransferenciasComSaldoVO(voPage, saldoTotal(), saldoNoPeriodo(transferencias));
    }
	
	public String delete(Long id) {
		Optional.ofNullable(
				transferenciaRepository.findById(id).orElseThrow(
						() -> new DataNotFoundException()));
		
		transferenciaRepository.deleteById(id);
		
		return "Deletado";
	}
	
	private TransferenciaVO transferenciaToVO (Transferencia transferencia) {
        return new TransferenciaVO(
        		transferencia.getId(),
        		transferencia.getDataTransferencia().toLocalDate().toString(),
        		transferencia.getValor(),
        		transferencia.getTipo(),
        		transferencia.getNomeOperadorTransacao());
    }
	
	private Float saldoTotal() {
		Float saldoTotal = 0.0f;
		List<Transferencia> list = transferenciaRepository.findAll();
		
		for (Transferencia transferencia : list) {
			saldoTotal += transferencia.getValor();
		}
		
		return saldoTotal;
	}
	
	private Float saldoNoPeriodo(List<Transferencia> transferencias) {
		Float saldoTotal = 0.0f;
		
		for (Transferencia transferencia : transferencias) {
			saldoTotal += transferencia.getValor();
		}
		
		return saldoTotal;
	}
}
