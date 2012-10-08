package com.javamachine.persistencia.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jni.Time;
import com.javamachine.beans.Despesa;
import com.javamachine.beans.Processo;
import com.javamachine.beans.TipoDespesa;
import com.javamachine.persistencia.connection.ConnectionFactory;

public class LancarDespesasDAO {
	
	public void lancarDespesa(Despesa despesa) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement("INSERT INTO AM_DESPESA (DT_DESPESA, VL_DESPESA, DS_OBSERVACAO, NR_PROCESSO, CD_DESPESA) VALUES( ?, ?, ?, ?, ?);");
			
			//Insiro a data de agora
			//TODO verificar as datas - String to time 20/10/2012
			java.sql.Date dataDespesa = new java.sql.Date(Time.now());
			stmt.setDate(1,  dataDespesa);
			stmt.setDouble(2, despesa.getValor());
			stmt.setString(3, despesa.getDescricao());
			stmt.setInt(4, despesa.getProcesso().getNumero());
			stmt.setInt(5, despesa.getTipoDespesa().getCodigo());
			
			stmt.execute();
			conn.commit();
			conn.setAutoCommit(true);
			
		}catch(SQLException e){
			e.printStackTrace();
			conn.rollback();
		}catch(Exception e ){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
			stmt.close();
		}
		
	}
	
	public void removerDespesa(int codigo) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement("DELETE FROM AM_DESPESA WHERE CD_LANCAMENTO = ?");				
			stmt.setInt(1, codigo);
			stmt.execute();
			conn.commit();
			conn.setAutoCommit(true);
			
		}catch(SQLException e){
			e.printStackTrace();
			conn.rollback();
		}catch(Exception e ){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
			stmt.close();
		}
	}
	
	public List<Despesa> consultarPorProcesso(int codigo){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultado = null;
		List<Despesa> lista = null;
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			
			stmt = conn.prepareStatement("SELECT dp.CD_LANCAMENTO, dp.CD_DESPESA, dp.DT_DESPESA, dp.VL_DESPESA, dp.DS_OBSERVACAO, tp.DS_DESPESA FROM AM_DESPESA dp INNER JOIN AM_TIPO_DESPESA tp ON tp.CD_DESPESA = dp.CD_DESPESA  WHERE dp.NR_PROCESSO = ?");
			stmt.setInt(1, codigo);
			resultado = stmt.executeQuery();
			lista = new ArrayList<Despesa>();
			while(resultado.next()){
				Despesa despesa = new Despesa();
				despesa.setCodigo(resultado.getInt("CD_LANCAMENTO"));
				despesa.setData(resultado.getDate("DT_DESPESA"));
				despesa.setDescricao(resultado.getString("DS_OBSERVACAO"));
				despesa.setProcesso(new Processo());
				despesa.setValor(resultado.getDouble("VL_DESPESA"));
				
				TipoDespesa tipo = new TipoDespesa();
				tipo.setDescricao(resultado.getString("DS_DESPESA"));
				despesa.setTipoDespesa(tipo);
				
				lista.add(despesa);
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
	
	public void alterarValor(Despesa despesa) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement("UPDATE AM_DESPESA SET VL_DESPESA = ? WHERE CD_LANCAMENTO = ?");			
			stmt.setDouble(1, despesa.getValor());
			stmt.setInt(2, despesa.getCodigo());
			stmt.execute();
			conn.commit();
			conn.setAutoCommit(true);
			
		}catch(SQLException e){
			e.printStackTrace();
			conn.rollback();
		}catch(Exception e ){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
			stmt.close();
		}
	}
	
	public void alterarDescricao(Despesa despesa) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement("UPDATE AM_DESPESA SET DS_OBSERVACAO = ? WHERE CD_LANCAMENTO = ?");			
			stmt.setString(1, despesa.getDescricao());
			stmt.setInt(2, despesa.getCodigo());
			stmt.execute();
			conn.commit();
			conn.setAutoCommit(true);
			
		}catch(SQLException e){
			e.printStackTrace();
			conn.rollback();
		}catch(Exception e ){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
			stmt.close();
		}

	}
	
	public void alterarData(Despesa despesa) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement("UPDATE AM_DESPESA SET DS_OBSERVACAO = ? WHERE CD_LANCAMENTO = ?");			
			stmt.setDate(1, (Date) despesa.getData());
			stmt.setInt(2, despesa.getCodigo());
			stmt.execute();
			conn.commit();
			conn.setAutoCommit(true);
			
		}catch(SQLException e){
			e.printStackTrace();
			conn.rollback();
		}catch(Exception e ){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
			stmt.close();
		}

	}
	
	public List<TipoDespesa> listarTiposDespesa(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultado = null;
		List<TipoDespesa> lista = null;
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			stmt = conn.prepareStatement("SELECT t.DS_DESPESA, t.CD_DESPESA FROM AM_TIPO_DESPESA t ORDER BY DS_DESPESA");
			resultado = stmt.executeQuery();
			lista = new ArrayList<TipoDespesa>();
			while(resultado.next()){				
				TipoDespesa tipo = new TipoDespesa();
				tipo.setCodigo(resultado.getInt("CD_DESPESA"));
				tipo.setDescricao(resultado.getString("DS_DESPESA"));
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
	
	public TipoDespesa consultarTipoDespesa(int codigo){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultado = null;
		TipoDespesa tipo = null; 
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			stmt = conn.prepareStatement("SELECT t.DS_DESPESA, t.CD_DESPESA FROM AM_TIPO_DESPESA t WHERE t.CD_DESPESA = ? ORDER BY DS_DESPESA");
			System.out.println(codigo);
			stmt.setInt(1, codigo);
			resultado = stmt.executeQuery();
			if(resultado.next()){				
				tipo = new TipoDespesa();
				tipo.setCodigo(resultado.getInt("CD_DESPESA"));
				tipo.setDescricao(resultado.getString("DS_DESPESA"));
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
		return tipo;
	}

	public Despesa inserir(Despesa despesa) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement("INSERT INTO AM_DESPESA (CD_DESPESA, NR_PROCESSO, DT_DESPESA, VL_DESPESA, DS_OBSERVACAO) VALUES(?, ?, cast(? as date), ?, ?)", Statement.RETURN_GENERATED_KEYS);			
			stmt.setInt(1, despesa.getTipoDespesa().getCodigo());
			stmt.setInt(2, despesa.getProcesso().getNumero());
			stmt.setDate(3, despesa.getData());
			stmt.setDouble(4, despesa.getValor());
			stmt.setString(5, despesa.getDescricao());
			stmt.executeQuery();			
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
		return despesa;
	}
	


}
