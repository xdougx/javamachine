package com.javamachine.beans;

import java.io.Serializable;

public class Telefone implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Telefone() {
		
	}
	private int codigo;
	private TipoTelefone tipoTelefone;
	private String numero;
	private int ddd;

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public TipoTelefone getTipoTelefone() {
		return tipoTelefone;
	}
	public void setTipoTelefone(TipoTelefone tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public int getDdd() {
		return ddd;
	}
	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

}
