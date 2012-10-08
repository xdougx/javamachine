package com.javamachine.beans;

import java.io.Serializable;
import java.sql.Date;

public class HistoricoBloqueio implements Serializable {

	private static final long serialVersionUID = 1L;

	private Bloqueio bloqueio;
	private Date data;
	private Processo processo;


	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Processo getProcesso() {
		return processo;
	}
	public void setProcesso(Processo processo) {
		this.processo = processo;
	}
	public void setBloqueio(Bloqueio bloqueio) {
		this.bloqueio = bloqueio;
	}
	public Bloqueio getBloqueio() {
		return bloqueio;
	}

	

}
