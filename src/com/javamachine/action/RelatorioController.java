package com.javamachine.action;

import java.util.List;

import org.apache.catalina.connector.Request;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.javamachine.BO.RelatorioProcessoBO;
import com.javamachine.beans.Advogado;
import com.javamachine.beans.AdvogadoProcesso;
import com.javamachine.beans.Cliente;
import com.javamachine.beans.Processo;
import com.opensymphony.xwork2.ActionSupport;

public class RelatorioController  extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private List<Processo> processos;
	private List<Cliente> clientes;
	private List<Advogado> advogados;
	private String advogado;
	private String cliente;
	private String inicio;
	private String fim;
	private List<AdvogadoProcesso> advogadoProcessos;
	
	@Action(value="relatorios", results={
			@Result(location="/relatorio/relatorios.jsp", name="relatorios")
	})
	public String home(){	
		return "relatorios";
	}
	
	@Action(value="relatorio_por_cliente", results={
			@Result(location="/relatorio/por_cliente.jsp", name="relatorio_por_cliente")
	})
	public String porCliente(){
		RelatorioProcessoBO bo = new RelatorioProcessoBO();
		this.processos = bo.buscarPorCliente(this.cliente);
		return "relatorio_por_cliente";
	}
	
	@Action(value="relatorio_por_advogado", results={
			@Result(location="/relatorio/por_advogado.jsp", name="relatorio_por_advogado")
	})
	public String porAdvogado(){
		RelatorioProcessoBO bo = new RelatorioProcessoBO();
		this.setAdvogadoProcessos(bo.buscarPorAdvogado(Integer.parseInt(this.advogado)));
		return "relatorio_por_advogado";
	}
	
	@Action(value="listar_clientes", results={
			@Result(location="/relatorio/clientes.jsp", name="listar_clientes")
	})
	public String listarClientes(){
		RelatorioProcessoBO bo = new RelatorioProcessoBO();
		this.clientes = bo.buscarCliente(this.cliente);
		return "listar_clientes";
	}
	
	@Action(value="listar_advogados_relatorio", results={
			@Result(location="/relatorio/advogados.jsp", name="listar_advogados_relatorio")
	})
	public String listarAdvogados(){
		RelatorioProcessoBO bo = new RelatorioProcessoBO();
		this.advogados = bo.buscarAdvogado(this.advogado);
		return "listar_advogados_relatorio";
	}
	
	@Action(value="relatorio_por_periodo", results={
			@Result(location="/relatorio/por_periodo.jsp", name="relatorio_por_periodo")
	})
	public String porPeriodo(){
		RelatorioProcessoBO bo = new RelatorioProcessoBO();
		this.processos = bo.buscarPorPeriodo(inicio, fim);
		return "relatorio_por_periodo";
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public String getAdvogado() {
		return advogado;
	}

	public void setAdvogado(String advogado) {
		this.advogado = advogado;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getFim() {
		return fim;
	}

	public void setFim(String fim) {
		this.fim = fim;
	}

	public List<Processo> getProcessos() {
		return processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}

	public List<Advogado> getAdvogados() {
		return advogados;
	}

	public void setAdvogados(List<Advogado> advogados) {
		this.advogados = advogados;
	}

	public List<AdvogadoProcesso> getAdvogadoProcessos() {
		return advogadoProcessos;
	}

	public void setAdvogadoProcessos(List<AdvogadoProcesso> advogadoProcessos) {
		this.advogadoProcessos = advogadoProcessos;
	}


}
