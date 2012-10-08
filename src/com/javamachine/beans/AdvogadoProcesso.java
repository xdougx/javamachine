package com.javamachine.beans;

import java.io.Serializable;
import java.sql.Date;

public class AdvogadoProcesso implements Serializable {

	private static final long serialVersionUID = 1L;

	public AdvogadoProcesso() {
		// TODO Auto-generated constructor stub
	}

	private Advogado advogado;
	private java.sql.Date dataParticipacao;
	private Processo processo;

	public Advogado getAdvogado() {
		return advogado;
	}
	public void setAdvogado(Advogado advogado) {
		this.advogado = advogado;
	}
	public java.sql.Date getDataParticipacao() {
		return this.dataParticipacao;
	}
	public void setDataParticipacao(java.sql.Date dataPacipacao) {
		this.dataParticipacao = dataPacipacao;
	}
	public Processo getProcesso() {
		return processo;
	}
	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

}