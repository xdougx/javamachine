package com.javamachine.beans;

import java.io.Serializable;
import java.sql.Date;

public class Despesa implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private int codigo;
	private String descricao;
	private double valor;
	private Date data;
	private TipoDespesa tipoDespesa;
	private Processo processo;

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
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public TipoDespesa getTipoDespesa() {
		return tipoDespesa;
	}
	public void setTipoDespesa(TipoDespesa tipo) {
		this.tipoDespesa = tipo;
	}
	public Processo getProcesso() {
		return processo;
	}
	public void setProcesso(Processo processo) {
		this.processo = processo;
	}


}
