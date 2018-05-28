package br.com.itau.testeitau.controler;



import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.itau.testeitau.exception.JogadorInvalidoException;
import br.com.itau.testeitau.model.Jogador;
import br.com.itau.testeitau.model.ListaOrigemEnum;
import br.com.itau.testeitau.service.JogadorService;

@Controller
public class JogadorControler {

	private static final Logger log = LoggerFactory.getLogger(JogadorControler.class);
	
	@Autowired
	JogadorService service;

	
	@RequestMapping("/")
	public String lista(Model model){
		log.debug("lista - Model: {}",model);
		model.addAttribute("jogadores", service.listaJogadores());
		return "lista";
	}
	
	
	@RequestMapping("/jogadorForm")
	public String jogadorForm(Jogador jogador, Model model){
		log.debug("jogadorForm - Model: {}, jogador: {}", model, jogador);
		
		model.addAttribute("jogador", jogador);
		model.addAttribute("listaOrigem", ListaOrigemEnum.values());
		return "jogadorForm";
	}
	
	@RequestMapping("/salvar")
	public String salvar(@Valid Jogador jogador, BindingResult bindingResult, Model model){
		log.debug("salvar - Model: {}, jogador: {}, bindingResult: {}", model, jogador, bindingResult);
       
		try{
			service.salvar(jogador, bindingResult);
	       	return jogadorForm(jogador, model);
		} catch (JogadorInvalidoException e) {
			return "redirect:/";
		}
    }

}
