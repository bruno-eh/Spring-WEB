package com.senai.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.model.Produto;

public interface Produtos extends JpaRepository<Produto,Long> {
	//para o JPA é preciso informar uma classe que tem
		//o modelo de dados que no caso é Produto.java
		
		//Importante: O Spring já faz a implementação interface
		//para nós.
	
}
