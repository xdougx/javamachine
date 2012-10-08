package com.javamachine.beans;

import java.io.Serializable;

public class PessoaEndereco extends Endereco implements Serializable  {

	private static final long serialVersionUID = 1L;
	public PessoaEndereco() {
	
	}
	private int numero;
	private String complemento;
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

}
