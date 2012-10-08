package com.javamachine.beans;

import java.io.Serializable;
import java.sql.Date;

public class HistoricoTaxa implements Serializable{
	private static final long serialVersionUID = 1L;

	private int codigo;
	private Cobranca cobranca;
	private Date vigencia;
	private double valor;
	
	public int getCodigo() {
		return codigo;
	}
	public Cobranca getCobranca() {
		return cobranca;
	}
	public Date getVigencia() {
		return vigencia;
	}
	public double getValor() {
		return valor;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public void setCobranca(Cobranca cobranca) {
		this.cobranca = cobranca;
	}
	public void setVigencia(Date vigencia) {
		this.vigencia = vigencia;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
}
