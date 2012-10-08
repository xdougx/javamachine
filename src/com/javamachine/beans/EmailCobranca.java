package com.javamachine.beans;

import java.io.Serializable;
import java.sql.Date;

public class EmailCobranca implements Serializable {

	private static final long serialVersionUID = 1L;

	public EmailCobranca() {}
	
	private Processo processo;
	private Date dataGeracao;
	private String descricaoAssunto;
	private String corpoEmail;
	private Date dataEnvio;

	public Date getDataGeracao() {
		return dataGeracao;
	}
	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}
	public String getDescricaoAssunto() {
		return descricaoAssunto;
	}
	public void setDescricaoAssunto(String descricaoAssunto) {
		this.descricaoAssunto = descricaoAssunto;
	}
	public String getCorpoEmail() {
		return corpoEmail;
	}
	public void setCorpoEmail(String corpoEmail) {
		this.corpoEmail = corpoEmail;
	}
	public Date getDataEnvio() {
		return dataEnvio;
	}
	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}
	public Processo getProcesso() {
		return processo;
	}
	public void setProcesso(Processo processo) {
		this.processo = processo;
	}


}
