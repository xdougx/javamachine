package com.javamachine.beans;


public abstract class Pessoa {

	public Pessoa() {}
	
	private int codigo;
	private String nome;

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

}
