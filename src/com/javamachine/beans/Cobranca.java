package com.javamachine.beans;

import java.io.Serializable;

public class Cobranca implements Serializable{

	private static final long serialVersionUID = 1L;

	public Cobranca() {}
	
	private int codigo;
	private String descricao;
	private double valorMora;
	private double taxaJuros;

	
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
	public double getValorMora() {
		return valorMora;
	}
	public void setValorMora(double valorMora) {
		this.valorMora = valorMora;
	}
	public double getTaxaJuros() {
		return taxaJuros;
	}
	public void setTaxaJuros(double taxaJuros) {
		this.taxaJuros = taxaJuros;
	}


}
