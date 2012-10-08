package com.javamachine.persistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javamachine.beans.Processo;
import com.javamachine.beans.Titulo;
import com.javamachine.persistencia.connection.ConnectionFactory;

public class TituloDAO {	
	public List<Titulo> buscarTitulosAtrasados() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultado = null;
		List<Titulo> lista = null;
		
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			
			stmt = conn.prepareStatement("SELECT ti.NR_TITULO, ti.DT_VENCIMENTO, ti.NR_PROCESSO, ti.VL_DOCUMENTO, ti.DT_DOCUMENTO  FROM AM_TITULO ti LEFT JOIN AM_TITULO_PAGO tg ON tg.NR_TITULO = ti.NR_TITULO WHERE tg.NR_TITULO is null");	
			resultado = stmt.executeQuery();
			lista = new ArrayList<Titulo>();
			
			while(resultado.next()){
				Titulo titulo = new Titulo();
				titulo.setNumero(resultado.getInt("NR_TITULO"));
				titulo.setDataVencimento(resultado.getDate("DT_VENCIMENTO"));
				titulo.setDataDocumento(resultado.getDate("DT_DOCUMENTO"));
				titulo.setValor(resultado.getDouble("VL_DOCUMENTO"));
				ProcessoDAO dao = new ProcessoDAO();
				Processo processo = dao.consultar(resultado.getInt("NR_PROCESSO"));
				titulo.setProcesso(processo);
				
				lista.add(titulo);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e ){
			e.printStackTrace();
		}finally{
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return lista;
	}
	
}
