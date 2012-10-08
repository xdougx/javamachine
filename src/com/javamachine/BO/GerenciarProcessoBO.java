package com.javamachine.BO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javamachine.beans.AdvogadoProcesso;
import com.javamachine.beans.Processo;
import com.javamachine.persistencia.dao.AdvogadoProcessoDAO;
import com.javamachine.persistencia.dao.ProcessoDAO;

public class GerenciarProcessoBO {
	
	public List<Processo> listarProcessos(){
		ProcessoDAO dao = new ProcessoDAO();
		List<Processo> lista = new ArrayList<Processo>(); 
		
		try {
			lista = dao.listar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;		
	}
	
	public void cadastrar(Processo processo){
		ProcessoDAO processoDAO = new ProcessoDAO();
		try {
			processoDAO.inserir(processo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void consultar(int codigo){
		ProcessoDAO processoDAO = new ProcessoDAO();
		processoDAO.consultar(codigo);
	}
	
	
	public void removerProcesso(int codigo){
		ProcessoDAO dao = new ProcessoDAO();
		try {
			dao.remover(codigo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void alterarProcesso(Processo processo){
		ProcessoDAO processoDAO = new ProcessoDAO();
		try {
			processoDAO.alterar(processo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<AdvogadoProcesso>listarAdvogados(int codigoProcesso){
		AdvogadoProcessoDAO dao = new AdvogadoProcessoDAO();
		List<AdvogadoProcesso> advogados = null;
		advogados = dao.listar(codigoProcesso);
				
		return advogados;
	}
	
	public void cadastrarAdvogadoProcesso(AdvogadoProcesso advProc){
		AdvogadoProcessoDAO dao = new AdvogadoProcessoDAO();
		dao.cadastrar(advProc);
	}

	public void removerAdvogadoProcesso(AdvogadoProcesso advogadoProcesso) {
		AdvogadoProcessoDAO dao = new AdvogadoProcessoDAO();
		dao.remover(advogadoProcesso);	
	}
	
}
