package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;
import br.com.casadocodigo.loja.validation.produtoValidation;
/**
 * Faz o controller dos metodos
 * @author bsilva
 *
 */
@Controller
@RequestMapping("produtos")
public class ProdutosController {
	
	@Autowired
	private ProdutoDAO produtoDAO;
	
	@InitBinder
	//Faz a ligação com o Validation e inicia a classe do produtoValidation
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new produtoValidation());
	}
	
	
	@RequestMapping("/form")
	public ModelAndView form(){
		ModelAndView modelAndView =  new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());
		
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	//@Valid é uma anotação do Spring que faz dia que aquele model é validado
	//BindingResult pega o resultado da validação
	//RedirectAttributes redireciona dependendo do resultado da validação
	public ModelAndView gravar(@Valid Produto produto,BindingResult result,
			RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			return form();
		}
			produtoDAO.gravar(produto);
			//FlashAttribute é usado para redirecionar caso sucesso e mostrar a mensagem
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso");
		return new ModelAndView("redirect:produtos");
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listar() {
		List<Produto> produtos = produtoDAO.listar();
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		modelAndView.addObject("produtos", produtos);
		return modelAndView;
	}
}
