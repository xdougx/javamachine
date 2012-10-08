package com.javamachine.persistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.javamachine.beans.EmailCobranca;
import com.javamachine.persistencia.connection.ConnectionFactory;

public class EmailCobrancaDAO {
	public void registrarEmail(EmailCobranca mail){

		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement("INSERT INTO AM_HISTORICO_EMAIL_COBRANCA (NR_PROCESSO, DT_GERACAO, DS_ASSUNTO, DS_CORPO_EMAIL, DT_ENVIO) VALUES (?, ?, ?, ?, ?)");
			stmt.setInt(1, mail.getProcesso().getNumero());
			stmt.setDate(2, mail.getDataGeracao());
			stmt.setString(3, mail.getDescricaoAssunto());
			stmt.setString(4, mail.getCorpoEmail());
			stmt.setDate(5, mail.getDataEnvio());
			
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
