package com.senai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.senai.model.Produto;
import com.senai.repository.Produtos;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired(required=true) //auto injetar os dados do spring
	private Produtos produtos;
	
	@RequestMapping("/novo") // /produto/novo
	public ModelAndView produto(){
		 //Retorna a view que deve ser chamada, no caso home (home.jsp) aqui o .jsp é omitido
		ModelAndView mv = new ModelAndView("cadastroProdutos.html");
		mv.addObject(new Produto());
		return mv;
	}
	
	//método para salvar os dados no banco
	@RequestMapping(method= RequestMethod.POST) // /produto/novo
	public String salvar(Produto produto) {
		produtos.save(produto);
		return "redirect:/produto/novo";
	}
	
	@RequestMapping
	public ModelAndView pesquisar() {
		//cria uma lista para receber os produtos do banco
		List<Produto> todosProdutos = produtos.findAll();
		ModelAndView mv = new ModelAndView("PesquisaProdutos.html");
		//envia um rótulo produtos e também a lista todosProdutos
		mv.addObject("produtos",todosProdutos);
		return mv;
	}
	
	//editar produto
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Produto produto) {
		ModelAndView mv = new ModelAndView("cadastroProdutos.html");
		mv.addObject(produto);
		return mv;
	}
	
	//Deletar produto
	@RequestMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Produto produto) {
		produtos.deleteById(produto.getId());
		return "redirect:/produto";
	}
	
}
