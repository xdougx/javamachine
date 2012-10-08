package com.javamachine.persistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.javamachine.beans.HistoricoTaxa;
import com.javamachine.persistencia.connection.ConnectionFactory;

public class HistoricoTaxaDAO {
	public void registrarHistoricoTaxa(HistoricoTaxa historico){

		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement("INSERT INTO AM_HISTORICO_TAXA (CD_COBRANCA, DT_VIGENCIA, VL_TAXA) VALUES (?, ?, ?)");
			stmt.setInt(1, historico.getCodigo());
			stmt.setDate(2, historico.getVigencia());
			stmt.setDouble(3, historico.getValor());
			
			stmt.execute();
			conn.commit();
			conn.setAutoCommit(true);
			
		}catch(SQLException e){
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}catch(Exception e ){
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
				
		}
	}
}
