package com.javamachine.beans;

import java.io.Serializable;

public class Cidade implements Serializable {

	private static final long serialVersionUID = 1L;

	public Cidade() {
		
	}
	private int codigo;
	private String descricao;
	private Estado codigoEstado;

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
	public Estado getCodigoEstado() {
		return codigoEstado;
	}
	public void setCodigoEstado(Estado codigoEstado) {
		this.codigoEstado = codigoEstado;
	}
	

}
