package com.javamachine.BO;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.javamachine.beans.Advogado;
import com.javamachine.beans.AdvogadoProcesso;
import com.javamachine.beans.Cliente;
import com.javamachine.beans.Processo;
import com.javamachine.persistencia.dao.AdvogadoDAO;
import com.javamachine.persistencia.dao.ClienteDAO;
import com.javamachine.persistencia.dao.RelatorioProcessoDAO;

public class RelatorioProcessoBO {
	
	public List<AdvogadoProcesso> buscarPorAdvogado(int codigo){
		RelatorioProcessoDAO relatorio = new RelatorioProcessoDAO();
		return relatorio.buscarPorAdvogado(codigo);
	}
	
	public List<Processo> buscarPorCliente(String busca){
		RelatorioProcessoDAO relatorio = new RelatorioProcessoDAO();
		List<Processo> lista = relatorio.buscarPorCliente(Integer.parseInt(busca));
		return lista;
	}
	
	public List<Processo> buscarPorPeriodo(String inicio, String fim){
		RelatorioProcessoDAO relatorio = new RelatorioProcessoDAO();
		return relatorio.buscarPorPeriodo(inicio, fim);
	}
	
	public List<Cliente> buscarCliente(String busca){
		ClienteDAO clienteDao = new ClienteDAO();
		List<Cliente> lista = new ArrayList<Cliente>();
		if(this.validarCodigoCliente(busca)){
			Cliente cliente = clienteDao.carregar(Integer.parseInt(busca));
			lista.add(cliente);
		}else {
			lista = clienteDao.buscar(busca);
		}
		
		return lista;
	}
	
	public List<Advogado> buscarAdvogado(String busca){
		AdvogadoDAO advogadoDao = new AdvogadoDAO();
		List<Advogado> lista = new ArrayList<Advogado>();
		if(this.validarCodigoCliente(busca)){
			Advogado advogado = advogadoDao.carregar(Integer.parseInt(busca));
			lista.add(advogado);
		}else {
			lista = advogadoDao.buscar(busca);
		}
		
		return lista;
	}
	
	private boolean validarCodigoCliente(String codigo){ 
		Pattern codigoCliente = Pattern.compile("^\\d{1,8}$");
		Matcher match = codigoCliente.matcher(codigo);
		return match.find();
		
	}

}
