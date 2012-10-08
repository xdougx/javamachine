package com.javamachine.beans;

import java.io.Serializable;
import java.util.Date;

public class Taxa implements Serializable {

	private static final long serialVersionUID = 1L;

	public Taxa() {}
	
	private int codigo;
	private Date datavigencia;
	private int valor;
	private Cobranca cobranca;

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Date getDatavigencia() {
		return datavigencia;
	}
	public void setDatavigencia(Date datavigencia) {
		this.datavigencia = datavigencia;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public Cobranca getCobranca() {
		return cobranca;
	}
	public void setCobranca(Cobranca cobranca) {
		this.cobranca = cobranca;
	}

}
