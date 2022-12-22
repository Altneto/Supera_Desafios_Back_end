package br.com.banco.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.exceptions.DataNotFoundException;
import br.com.banco.models.Conta;
import br.com.banco.repositories.ContaRepository;
import br.com.banco.vo.ContaNovaVO;
import br.com.banco.vo.ContaVO;

@Service
public class ContaService {
	
	@Autowired
	private ContaRepository contaRepository;
	
	public ContaVO save(ContaNovaVO vo) {
		
		Conta conta = new Conta(vo.getNomeResponsavel());
		
		contaRepository.save(conta);
		
		return new ContaVO(conta.getIdConta(), conta.getNomeResponsavel());
	}
	
	public ContaVO findById(Long id) {
		Optional<Conta> conta = Optional.ofNullable(contaRepository.findById(id).orElseThrow(() -> new DataNotFoundException()));
		
		return contaToVo(conta.get());
	}
	
	public String delete(Long id) {
		Optional.ofNullable(contaRepository.findById(id).orElseThrow(() -> new DataNotFoundException()));
		
		contaRepository.deleteById(id);
		
		return "Deletado";
	}
	
	private ContaVO contaToVo(Conta conta) {
		
		return new ContaVO(conta.getIdConta(), conta.getNomeResponsavel());
	}
}
