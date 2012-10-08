package com.javamachine.beans;

import java.io.Serializable;

public class Logradouro implements Serializable {


	private static final long serialVersionUID = 1L;
	public Logradouro() {
		
	}
	private int codigo;
	private String descricao;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	

}
