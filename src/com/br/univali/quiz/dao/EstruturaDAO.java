package com.br.univali.quiz.dao;

import java.io.Serializable;
/**
 * Classe que traz os dados da base de dados da tabela ESTRUTURA.
 * 
 * @author Jean Carlos da Silva Ferreira (jeansferreira@gmail.com)
 * @version 1.0
 * @exception Caso a base esteja sem dados, objeto ficará vazio.
 *
 */
public class EstruturaDAO implements Serializable{
	
	private String id;
	private String nome;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

}
