package com.javamachine.persistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.javamachine.beans.Advogado;
import com.javamachine.beans.AdvogadoProcesso;
import com.javamachine.beans.Processo;
import com.javamachine.persistencia.connection.ConnectionFactory;

public class AdvogadoProcessoDAO{
	
	public void cadastrar(AdvogadoProcesso relacao){
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement("INSERT INTO AM_ADVOGADO_PROCESSO (CD_PESSOA_ADV, DT_INICIO_PARTICIPACAO, NR_PROCESSO) VALUES (?, ?, ?)");
			stmt.setInt(1, relacao.getAdvogado().getCodigo());
			stmt.setDate(2, relacao.getDataParticipacao());
			stmt.setInt(3, relacao.getProcesso().getNumero());

			
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
	
	public void remover(AdvogadoProcesso relacao){
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement("DELETE FROM AM_ADVOGADO_PROCESSO WHERE CD_PESSOA_ADV = ? AND NR_PROCESSO = ?");
			stmt.setInt(1, relacao.getAdvogado().getCodigo());
			stmt.setInt(2, relacao.getProcesso().getNumero());
			
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
	
	public List<AdvogadoProcesso> listar(int codigoProcesso) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultado = null;
		List<AdvogadoProcesso> lista = null;
		try {
			conn = ConnectionFactory.obterConexaoOracle();			
			stmt = conn.prepareStatement("SELECT ad.nr_oab, pe.nm_pessoa, ad.ds_email, pe.cd_pessoa, pr.dt_inicio_participacao FROM AM_ADVOGADO ad  INNER JOIN AM_PESSOA pe ON ad.CD_PESSOA_ADV = pe.CD_PESSOA  INNER JOIN AM_ADVOGADO_PROCESSO pr ON ad.cd_pessoa_adv = pr.cd_pessoa_adv  WHERE pr.NR_PROCESSO = ?");
			stmt.setInt(1, codigoProcesso);
			resultado = stmt.executeQuery();
			
			lista = new ArrayList<AdvogadoProcesso>();
			while(resultado.next()){
				AdvogadoProcesso advProc = new AdvogadoProcesso(); 
				Advogado adv = new Advogado();
				adv.setCodigo(resultado.getInt("CD_PESSOA"));
				adv.setOab(resultado.getInt("NR_OAB"));
				adv.setEmail(resultado.getString("DS_EMAIL"));
				adv.setNome(resultado.getString("NM_PESSOA"));
				
				ProcessoDAO dao = new ProcessoDAO();
				Processo processo = dao.consultar(codigoProcesso);
				
				advProc.setProcesso(processo);
				advProc.setAdvogado(adv);
				advProc.setDataParticipacao(resultado.getDate("DT_INICIO_PARTICIPACAO"));
				lista.add(advProc);
			}

			
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
		
		return lista;
	}
}
