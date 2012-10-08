package com.javamachine.action;


import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.javamachine.BO.LancarDespesasBO;
import com.javamachine.beans.Despesa;
import com.javamachine.beans.Processo;
import com.javamachine.beans.TipoDespesa;
import com.opensymphony.xwork2.ActionSupport;

public class LancarDespesasController extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String busca;
	private Processo processo;
	private List<Despesa> despesas;
	private Despesa despesa;
	private List<TipoDespesa> tiposDespesa;
	private TipoDespesa tipoDespesa;
	private int codigoTipoDespesa;
	
	@Action(value="buscar_processo_despesa", results={
			  @Result(location="/despesas/buscar.jsp", name="buscar_processo_despesa")	
			})
	public String home() {
		return "buscar_processo_despesa";
	}
	
	@Action(value="processos_por_cliente", results={
			  @Result(location="/despesas/processos.jsp", name="processos_por_cliente")	
			})
	public String buscar() {
		LancarDespesasBO bo = new LancarDespesasBO();
		this.processo = bo.buscarProcesso(Integer.parseInt(this.busca));
		this.despesas = bo.despesasPorProcesso(Integer.parseInt(this.busca));
		this.tiposDespesa = bo.listarTiposDespesa();
		return "processos_por_cliente";
	}
	
	@Action(value="gravar_despesa", results={
			  @Result(location="/despesas/_lancamento.jsp", name="gravar_despesa")	
			})
	public String gravarDespesa() {
		LancarDespesasBO bo = new LancarDespesasBO();
		this.tipoDespesa = bo.consultarTipoDespesa(this.codigoTipoDespesa);
		this.despesa.setTipoDespesa(this.tipoDespesa);
		this.despesa = bo.inserir(this.despesa);
		
		return "gravar_despesa";
	}
	@Action(value="recarregar_lancamentos", results={
			  @Result(location="/despesas/_recarregar_lancamentos.jsp", name="recarregar_lancamentos")	
			})
	public String recarregarLancamentos(){
		LancarDespesasBO bo = new LancarDespesasBO();
		this.despesas = bo.despesasPorProcesso(Integer.parseInt(this.busca));
		return "recarregar_lancamentos";
	}
	
	@Action(value="remover_lancamento", results={
			  @Result(location="/despesas/remover.jsp", name="remover_lancamento")	
			})
	public String removerLancamento(){
		LancarDespesasBO bo = new LancarDespesasBO();
		bo.removerLancamento(Integer.parseInt(this.busca));
		return "remover_lancamento";
	}
	

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public List<Despesa> getDespesas() {
		return despesas;
	}

	public void setDespesas(List<Despesa> despesas) {
		this.despesas = despesas;
	}

	public List<TipoDespesa> getTiposDespesa() {
		return tiposDespesa;
	}

	public void setTiposDespesa(List<TipoDespesa> tiposDespesa) {
		this.tiposDespesa = tiposDespesa;
	}

	public TipoDespesa getTipoDespesa() {
		return tipoDespesa;
	}

	public void setTipoDespesa(TipoDespesa tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}

	public Despesa getDespesa() {
		return despesa;
	}

	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}

	public void setCodigoTipoDespesa(int codigoTipoDespesa) {
		this.codigoTipoDespesa = codigoTipoDespesa;
	}

	public int getCodigoTipoDespesa() {
		return codigoTipoDespesa;
	}

}
