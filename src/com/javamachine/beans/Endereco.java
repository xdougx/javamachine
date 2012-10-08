package com.javamachine.beans;

import java.io.Serializable;

public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	public Endereco() {
		
	}
	private int codigo;
	private String logradouro;
	private int cep;
	private Logradouro codigoLogradouro;
	private Bairro codigoBairro;
	

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public int getCep() {
		return cep;
	}
	public void setCep(int cep) {
		this.cep = cep;
	}
	public Logradouro getCodigoLogradouro() {
		return codigoLogradouro;
	}
	public void setCodigoLogradouro(Logradouro codigoLogradouro) {
		this.codigoLogradouro = codigoLogradouro;
	}
	public Bairro getCodigoBairro() {
		return codigoBairro;
	}
	public void setCodigoBairro(Bairro codigoBairro) {
		this.codigoBairro = codigoBairro;
	}
}
