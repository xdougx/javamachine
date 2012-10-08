package com.javamachine.persistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javamachine.beans.Cliente;
import com.javamachine.beans.Cobranca;
import com.javamachine.persistencia.connection.ConnectionFactory;

public class CobrancaDAO {
	public List <Cobranca> lista(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultado = null;
		List<Cobranca> lista = null;
		Cobranca cobranca = null;
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			
			stmt = conn.prepareStatement("SELECT CD_COBRANCA,DS_COBRANCA,TX_JUROS,VL_MORA_DIARIA FROM AM_TIPO_COBRANCA");
			
			resultado = stmt.executeQuery();
			lista = new ArrayList<Cobranca>();
			while(resultado.next()){
				cobranca = new Cobranca();
				cobranca.setCodigo(resultado.getInt("CD_cobranca"));
				cobranca.setDescricao(resultado.getString("DS_DESCRICAO"));
				cobranca.setValorMora(resultado.getInt("VL_MORA_DIARIA"));
				
				lista.add(cobranca);
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
