package com.javamachine.beans;

import java.io.Serializable;
import java.util.Date;

public class Audiencia implements Serializable {


	private static final long serialVersionUID = 1L;

	public Audiencia() {
		// TODO Auto-generated constructor stub
	}
	private int codigo;
	private  Date dataAudiencia;
	private String salaForum;

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Date getDataAudiencia() {
		return dataAudiencia;
	}
	public void setDataAudiencia(Date dataAudiencia) {
		this.dataAudiencia = dataAudiencia;
	}
	public String getSalaForum() {
		return salaForum;
	}
	public void setSalaForum(String salaForum) {
		this.salaForum = salaForum;
	}

}
