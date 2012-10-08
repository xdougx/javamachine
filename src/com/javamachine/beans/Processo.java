package com.javamachine.beans;

import java.io.Serializable;
import java.sql.Date;

public class Processo implements Serializable{

	private static final long serialVersionUID = 1L;
	public Processo(){}
	

	private Forum forum;
	private Cliente cliente;
	private TipoCausa causa;
	private Cobranca cobranca;
	private int numero;
	private String descricao;
	private Date dataAbertura;
	private Date dataFechamento;
	private int diaVencimento;
	private String observacao;
	private int resultado;
	
	public Forum getForum() {
		return forum;
	}
	public void setForum(Forum forum) {
		this.forum = forum;
	}
	public Cliente getCliente() {
		return this.cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;;
	}
	public TipoCausa getCausa() {
		return causa;
	}
	public void setCausa(TipoCausa causa) {
		this.causa = causa;
	}
	public Cobranca getCobranca() {
		return cobranca;
	}
	public void setCobranca(Cobranca cobranca) {
		this.cobranca = cobranca;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public Date getDataFechamento() {
		return dataFechamento;
	}
	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}
	public int getDiaVencimento() {
		return diaVencimento;
	}
	public void setDiaVencimento(int diaVencimento) {
		this.diaVencimento = diaVencimento;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public int getResultado() {
		return resultado;
	}
	public void setResultado(int resultado) {
		this.resultado = resultado;
	}
}