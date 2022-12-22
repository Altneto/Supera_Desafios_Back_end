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
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.models.Conta;
import br.com.banco.services.ContaService;
import br.com.banco.vo.ContaNovaVO;
import br.com.banco.vo.ContaVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/conta")
public class ContaController {
	
	@Autowired
	private ContaService contaService;
	
	@Operation(summary = "Cadastrar nova conta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Conta cadastrada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Conta.class)) })
             })
    @PostMapping()
	public ResponseEntity<ContaVO> save(@RequestBody ContaNovaVO vo) {
		return new ResponseEntity<ContaVO>(contaService.save(vo), HttpStatus.CREATED);
	}
	
	@Operation(summary = "Buscar conta por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conta encontrada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Conta.class)) }),
            @ApiResponse(responseCode = "404", description = "Conta não encontrada",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<ContaVO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(contaService.findById(id));
    }
	
	@Operation(summary = "Cancelar conta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conta cancelada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Conta.class)) }),
            @ApiResponse(responseCode = "404", description = "Conta não encontrada",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
		
        return ResponseEntity.ok(contaService.delete(id));
    }
}
