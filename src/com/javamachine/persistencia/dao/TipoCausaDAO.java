package com.javamachine.persistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javamachine.beans.TipoCausa;
import com.javamachine.persistencia.connection.ConnectionFactory;

public class TipoCausaDAO {
	public List <TipoCausa> lista() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultado = null;
		List<TipoCausa> lista = null;
        TipoCausa tipocausa = null;
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			
			stmt = conn.prepareStatement("SELECT CD_CAUSA,DS_CAUSA FROM AM_TIPO_CAUSA");
	
			resultado = stmt.executeQuery();
			lista = new ArrayList<TipoCausa>();
			while(resultado.next()){
				
				TipoCausa tipo = new TipoCausa();
			    tipo.setCodigo(resultado.getInt("CD_CAUSA"));
				tipo.setDescricao(resultado.getString("DS_CAUSA"));
				
				
				lista.add(tipo);
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






