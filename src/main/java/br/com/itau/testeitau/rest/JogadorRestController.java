package br.com.itau.testeitau.rest;

import java.net.URI;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.itau.testeitau.exception.JogadorInvalidoException;
import br.com.itau.testeitau.exception.JogadorNaoEncontradoException;
import br.com.itau.testeitau.model.Jogador;
import br.com.itau.testeitau.service.JogadorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController()
@Api(description="Operações de manutenção do jogador")
public class JogadorRestController {

	private static final Logger log = LoggerFactory.getLogger(JogadorRestController.class);

	@Autowired
	private JogadorService service;
	
	
	@GetMapping(path="/jogadores")
	@ApiOperation("${jogador.operation.listaJogadores}")
    public Iterable<Jogador> listaJogadores() {
    	return service.listaJogadores();
    }
    
	
    @GetMapping(path="/jogadores/{id}")
    @ApiOperation("${jogador.operation.recuperaJogador}")
    public ResponseEntity<Object> recuperaJogador(@PathVariable("id") Long id) {
    	
    	try{
    		Jogador jogador = service.findJogadorById(id);
    		return ResponseEntity.ok().body(jogador);
    		
		} catch (JogadorNaoEncontradoException e) {
			log.error("jogadorById -  jogador nao encontrado: " + e.getMessage());
			return ResponseEntity.notFound().build();
		}
    }
    
    
	@PostMapping(path="/jogadores")
	@ApiOperation("${jogador.operation.salvaJogador}")
    public ResponseEntity<Object> salvaJogador(@Valid @RequestBody Jogador jogador, BindingResult bindingResult) {   
    	
		try{
			Jogador novoJogador = service.salvar(jogador, bindingResult);
	    	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoJogador.getId()).toUri();
	    	return ResponseEntity.created(location).build();
	    	
		} catch (JogadorInvalidoException e) {
			log.error("criarJogador -  jogador invalido: " + e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		} 
    }
	
	
	@PutMapping(path="/jogadores/{id}")
	@ApiOperation("${jogador.operation.atualizaJogador}")
	public ResponseEntity<Object> atualizaJogador(@Valid @RequestBody Jogador jogador, BindingResult bindingResult, @PathVariable long id) {

		try{		
			jogador.setId(id);
			service.atualiza(jogador, bindingResult);
	    	return ResponseEntity.noContent().build();

		} catch (JogadorNaoEncontradoException e) {
			log.error("atualizaJogador - Nao encontrado: " + e.getMessage());
			return ResponseEntity.notFound().build();
		} catch (JogadorInvalidoException e) {
			log.error("atualizaJogador -  jogador invalido: " + e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping(path="/jogadores/{id}")
	@ApiOperation("${jogador.operation.removeJogador}")
	public ResponseEntity<Object> removeJogador(@PathVariable long id) {

		try{		
			
			service.remove(id);
	    	return ResponseEntity.noContent().build();
	    	
		} catch (JogadorNaoEncontradoException e) {
			log.error("atualizaJogador - Nao encontrado: " + e.getMessage());
			return ResponseEntity.notFound().build();
		}
	}

}