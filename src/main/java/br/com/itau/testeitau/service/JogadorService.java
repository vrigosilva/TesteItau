package br.com.itau.testeitau.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import br.com.itau.testeitau.exception.JogadorInvalidoException;
import br.com.itau.testeitau.exception.JogadorNaoEncontradoException;
import br.com.itau.testeitau.model.Jogador;
import br.com.itau.testeitau.repository.JogadorRepository;

@Service
public class JogadorService {
	private static final Logger log = LoggerFactory.getLogger(JogadorService.class);

	
	@Autowired
	JogadorRepository repo;
	

	public List<Jogador> listaJogadores(){
		List<Jogador> jogadores = new ArrayList<>(); 
		
		Iterable<Jogador> ite = repo.findAll();
		ite.forEach(jogadores::add);
		
		log.debug("Listando jogadores. Encontrados: {}", jogadores.size());
		return  jogadores;
	}	

	public Jogador findJogadorById(Long id) throws JogadorNaoEncontradoException{
		log.debug("Consultando jogador com id: {}", id);
		Optional<Jogador> jogador = repo.findById(id);
		
	   	if (!jogador.isPresent())
    		throw new JogadorNaoEncontradoException("Jogador nao encontrado id: " + id);
	   	
		return  jogador.get();
	}	
	
	
	public Jogador salvar(Jogador jogador, BindingResult bindingResult) throws JogadorInvalidoException{
		log.debug("Salvando jogador: {}", jogador);

		if(jogador.getId() != null){
			bindingResult.addError(new ObjectError("id", "id invalido"));
		}
		
        if (bindingResult.hasErrors()) {
        	throw new JogadorInvalidoException(bindingResult.getAllErrors());
        }
        
		jogador.setCodinome("codnome");
		Jogador novoJogador = repo.save(jogador);
		
        return novoJogador;
    }
	
	
	
	public Jogador atualiza(Jogador jogador, BindingResult bindingResult) throws JogadorInvalidoException, JogadorNaoEncontradoException{
		log.debug("Atualizando jogador: {}", jogador);
		
		if(jogador.getId() != null){
			findJogadorById(jogador.getId());
		}else{
			bindingResult.addError(new ObjectError("id", "id invalido"));
		}
		
		if (bindingResult.hasErrors()) {
			throw new JogadorInvalidoException(bindingResult.getAllErrors());
		}
		
		jogador.setCodinome("codnome");
		Jogador novoJogador = repo.save(jogador);
		
		return novoJogador;
	}
	
	
	

	public void remove(Long id) throws JogadorNaoEncontradoException {
		log.debug("Removendo jogador com id: {}", id);
		Jogador jogador = findJogadorById(id);
		repo.delete(jogador);
	}


	
}
