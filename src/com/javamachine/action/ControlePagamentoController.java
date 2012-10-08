package com.javamachine.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.javamachine.BO.ControlarPagamentosBO;
import com.opensymphony.xwork2.ActionSupport;
import com.javamachine.beans.Titulo;

public class ControlePagamentoController extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private List<Titulo> atrasos;
	
	@Action(value="controlar_pagamentos", results={
			  @Result(location="/pagamentos/listar.jsp", name="controlar_pagamentos")	
			})
	public String listar() {
		ControlarPagamentosBO bo = new ControlarPagamentosBO();
		//bo.verificarAtrasos();
		this.atrasos = bo.listarBloqueios();
		return "controlar_pagamentos";
	}
	
	@Action(value="verificar_atrasos", results={
			  @Result(location="/pagamentos/verificar.jsp", name="verificar_atrasos")	
			})
	public String verificarAtrasos() {
		ControlarPagamentosBO bo = new ControlarPagamentosBO();
		bo.verificarAtrasos();
		return "verificar_atrasos";
	}
	
	public void setAtrasos(List<Titulo> atrasos) {
		this.atrasos = atrasos;
	}

	public List<Titulo> getAtrasos() {
		return atrasos;
	}

}
