package com.javamachine.persistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.javamachine.beans.Bloqueio;
import com.javamachine.beans.HistoricoBloqueio;
import com.javamachine.persistencia.connection.ConnectionFactory;

public class BloqueioDAO {
	public int criarBloqueio(Bloqueio bloqueio){
		Connection conn = null;
		PreparedStatement stmt = null;
		int id = 0;
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement("INSERT INTO AM_BLOQUEIO_DESBLOQ(DS_BLOQUEIO_DESBLOQ) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, bloqueio.getDescricao());
			stmt.execute();
			conn.commit();
			conn.setAutoCommit(true);
			
			PreparedStatement stmtForLastKey = conn.prepareStatement("SELECT CD_BLOQUEIO_DESBLOQ FROM AM_BLOQUEIO_DESBLOQ  WHERE ROWNUM = 1 ORDER BY CD_BLOQUEIO_DESBLOQ DESC");
			ResultSet key = stmtForLastKey.executeQuery();
			key.next();
			id = key.getInt(1);
			
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
		
		return id;
	}
	
	public void criarHistorico(HistoricoBloqueio historico) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement("INSERT INTO AM_HIST_PROC_BLOQ_DESBLOQ(NR_PROCESSO, CD_BLOQUEIO_DESBLOQ, DT_BLOQUEIO_DESBLOQ) VALUES (?, ?, ?)");
			stmt.setInt(1, historico.getProcesso().getNumero());
			stmt.setInt(2, historico.getBloqueio().getCodigo());
			stmt.setDate(3, historico.getData());
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
