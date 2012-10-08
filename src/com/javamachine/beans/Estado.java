package com.javamachine.beans;

import java.io.Serializable;

public class Estado implements Serializable {

	private static final long serialVersionUID = 1L;

	public Estado() {

	}
	private int codigo;
	private String descricao;
	private String sigla;

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
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

}
