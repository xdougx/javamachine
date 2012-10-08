package com.javamachine.BO;

import java.sql.SQLException;
import java.util.List;

import com.javamachine.beans.Despesa;
import com.javamachine.beans.Processo;
import com.javamachine.beans.TipoDespesa;
import com.javamachine.persistencia.dao.LancarDespesasDAO;
import com.javamachine.persistencia.dao.ProcessoDAO;

public class LancarDespesasBO {
	public Processo buscarProcesso(int codigo){
		ProcessoDAO dao = new ProcessoDAO();
		return dao.consultar(codigo);
	}
	
	public List<Despesa> despesasPorProcesso(int codigo){
		LancarDespesasDAO dao = new LancarDespesasDAO();
		return dao.consultarPorProcesso(codigo);
	}

	public List<TipoDespesa> listarTiposDespesa() {
		LancarDespesasDAO dao = new LancarDespesasDAO();
		return dao.listarTiposDespesa();
	}
	
	public TipoDespesa consultarTipoDespesa(int codigo) {
		LancarDespesasDAO dao = new LancarDespesasDAO();
		return dao.consultarTipoDespesa(codigo);
	}

	public Despesa inserir(Despesa despesa) {
		LancarDespesasDAO dao = new LancarDespesasDAO();
		despesa = dao.inserir(despesa);
		
		return despesa;
	}

	public void removerLancamento(int codigo) {
		LancarDespesasDAO dao = new LancarDespesasDAO();
		try {
			dao.removerDespesa(codigo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
