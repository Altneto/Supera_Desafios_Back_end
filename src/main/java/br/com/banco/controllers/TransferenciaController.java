package br.com.banco.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.models.Transferencia;
import br.com.banco.services.TransferenciaService;
import br.com.banco.vo.TransferenciaNovaVO;
import br.com.banco.vo.TransferenciaVO;
import br.com.banco.vo.TransferenciasComSaldoVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {
	
	@Autowired
	private TransferenciaService transferenciaService;
	
	@Operation(summary = "Cadastrar nova transferencia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transferencia cadastrada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Transferencia.class)) })
             })
    @PostMapping()
	public ResponseEntity<TransferenciaVO> save(@RequestBody TransferenciaNovaVO vo) {
		return new ResponseEntity<TransferenciaVO>(transferenciaService.save(vo), HttpStatus.CREATED);
	}
	
	@Operation(summary = "Buscar transferencias por filtro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping()
    public ResponseEntity<TransferenciasComSaldoVO> search(
            @RequestParam(
                    value = "dataInicio",
                    required = false ) String dataInicio,
            @RequestParam(
                    value = "dataFim",
                    required = false ) String dataFim,
            @RequestParam(
                    value = "nomeDoOperador",
                    required = false ) String nomeDoOperador,
            @RequestParam(
                    value = "page",
                    required = false,
                    defaultValue = "0") int page,
            @RequestParam(
                    value = "size",
                    required = false,
                    defaultValue = "10") int size
    ) {
        return ResponseEntity.ok().body(transferenciaService.search(dataInicio, dataFim, nomeDoOperador, page, size));
    }
	
	@Operation(summary = "Cancelar transferencia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transferencia cancelada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Transferencia.class)) }),
            @ApiResponse(responseCode = "404", description = "Transferencia não encontrada",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
		
        return ResponseEntity.ok(transferenciaService.delete(id));
    }
}
