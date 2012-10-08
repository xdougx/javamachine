package com.javamachine.beans;

import java.io.Serializable;
import java.util.Date;

public class TituloPago extends Titulo implements Serializable {


	private static final long serialVersionUID = 1L;

	public TituloPago() {
		
	}
	
	private Date dataPagamento;
	private double valorPago;

	
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public double getValorPago() {
		return valorPago;
	}
	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}

}
