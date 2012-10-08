package com.javamachine.beans;

import java.io.Serializable;
import java.util.Date;

public class HoraAdvogado implements Serializable {

	private static final long serialVersionUID = 1L;

	public HoraAdvogado() {
	
	}

	private int codigo;
	private Date dataVigencia;
	private double valor;

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Date getDataVigencia() {
		return dataVigencia;
	}
	public void setDataVigencia(Date dataVigencia) {
		this.dataVigencia = dataVigencia;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
}
