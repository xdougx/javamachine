package com.javamachine.beans;

import java.io.Serializable;
import java.sql.Date;

public class Titulo implements Serializable {


	private static final long serialVersionUID = 1L;

	public Titulo() {
		
	}
	private int numero;
	private int numeroAgencia;
	private Date dataDocumento;
	private Date dataVencimento;
	private double valor;
	private Processo processo;

	public Processo getProcesso() {
		return processo;
	}
	public void setProcesso(Processo processo) {
		this.processo = processo;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getNumeroAgencia() {
		return numeroAgencia;
	}
	public void setNumeroAgencia(int numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}
	public Date getDataDocumento() {
		return dataDocumento;
	}
	public void setDataDocumento(Date dataDocumento) {
		this.dataDocumento = dataDocumento;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}

}
