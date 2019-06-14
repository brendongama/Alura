package br.com.casadocodigo.loja.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.models.Produto;

public class produtoValidation implements Validator{

	

	//Caso a validação suportada ele passa para a classe
	@Override
	public boolean supports(Class<?> clazz) {
		return Produto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		//ValidationUtils.rejectIfEmpty verifica se o model é requirido e guarda o erro
			ValidationUtils.rejectIfEmpty(errors, "titulo", "field.required");
			ValidationUtils.rejectIfEmpty(errors, "descricao", "field.required");
			
			Produto produto = (Produto) target;
			if(produto.getPaginas() <= 0) {
				errors.rejectValue("paginas", "field.required");
			}
		}
		
	
}
