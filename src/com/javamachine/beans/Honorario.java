package com.javamachine.beans;

import java.io.Serializable;
import java.util.Date;

public class Honorario implements Serializable {

	private static final long serialVersionUID = 1L;

	public Honorario() {
		
	}
	private int codigo;
	private Date dataHonorario;
	private int quantidadeHora;
	private String descricao;
    private Tarefa tarefa;

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Date getDataHonorario() {
		return dataHonorario;
	}
	public void setDataHonorario(Date dataHonorario) {
		this.dataHonorario = dataHonorario;
	}
	public int getQuantidadeHora() {
		return quantidadeHora;
	}
	public void setQuantidadeHora(int quantidadeHora) {
		this.quantidadeHora = quantidadeHora;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Tarefa getTarefa() {
		return tarefa;
	}
	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
}
