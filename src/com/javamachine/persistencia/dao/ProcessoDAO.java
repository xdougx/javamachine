
package com.javamachine.persistencia.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.javamachine.persistencia.connection.ConnectionFactory;

import com.javamachine.beans.TipoCausa;
import com.javamachine.beans.Cliente;
import com.javamachine.beans.Cobranca;
import com.javamachine.beans.Forum;
import com.javamachine.beans.Processo;
import com.javamachine.interfaces.InterfaceDaoProcesso;

public class ProcessoDAO implements InterfaceDaoProcesso {
	public void inserir(Processo processo) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement("INSERT INTO AM_PROCESSO (NR_PROCESSO, CD_PESSOA_FORUM, CD_PESSOA_CLIENTE, CD_CAUSA, CD_COBRANCA, DS_PROCESSO, DT_ABERTURA, DD_DIA_VENCIMENTO, DS_OBSERVACAO, CD_RESULTADO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setInt(1, processo.getNumero());
			stmt.setInt(2, processo.getForum().getCodigo());
			stmt.setInt(3, processo.getCliente().getCodigo());
			stmt.setInt(4, processo.getCausa().getCodigo());
			stmt.setInt(5, processo.getCobranca().getCodigo());
			stmt.setString(6, processo.getDescricao());
			stmt.setDate(7, processo.getDataAbertura());
			stmt.setInt(8, processo.getDiaVencimento());
			stmt.setString(9, processo.getObservacao());
			stmt.setInt(10, processo.getResultado());
			
			stmt.execute();
			conn.commit();
			conn.setAutoCommit(true);
			
		}catch(SQLException e){
			e.printStackTrace();
			conn.rollback();
		}catch(Exception e ){
			e.printStackTrace();
			if(conn != null) conn.rollback();
		}finally{
			if(conn != null){
				conn.close();
			}
			if(stmt != null){
				stmt.close();
			}
		}
		
		
	}

	
	public void alterar(Processo processo)throws SQLException   {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement("UPDATE AM_PROCESSO SET CD_PESSOA_FORUM = ?, CD_PESSOA_CLIENTE = ?, CD_CAUSA = ?, CD_COBRANCA = ?, DS_PROCESSO = ?, DT_ABERTURA = ?, DD_DIA_VENCIMENTO = ?, DS_OBSERVACAO = ?, CD_RESULTADO = ? WHERE NR_PROCESSO = ?");
			stmt.setInt(1, processo.getForum().getCodigo());
			stmt.setInt(2, processo.getCliente().getCodigo());
			stmt.setInt(3, processo.getCausa().getCodigo());
			stmt.setInt(4, processo.getCobranca().getCodigo());
			stmt.setString(5, processo.getDescricao());
			stmt.setDate(6, processo.getDataAbertura());
			stmt.setInt(7, processo.getDiaVencimento());
			stmt.setString(8, processo.getObservacao());
			stmt.setInt(9, processo.getResultado());
			stmt.setInt(10, processo.getNumero());
			
			
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


	public void remover(int codigo) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement("DELETE FROM AM_PROCESSO WHERE NR_PROCESSO = ?");
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


	public Processo consultar(int codigo) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultado = null;
		Processo processo = null;
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			
			stmt = conn.prepareStatement("SELECT pr.nr_processo, pr.ds_processo, pr.dt_abertura, pr.dt_fechamento, pr.ds_observacao, pr.cd_resultado, ca.ds_causa, ca.cd_causa, co.*, pr.dd_dia_vencimento, cl.nm_razao_social, cl.ds_email, pr.cd_pessoa_forum, fo.ds_forum FROM AM_PROCESSO pr INNER JOIN AM_TIPO_CAUSA ca ON ca.cd_causa = pr.cd_causa INNER JOIN AM_TIPO_COBRANCA co ON pr.CD_COBRANCA = co.CD_COBRANCA INNER JOIN AM_CLIENTE cl ON cl.cd_pessoa_cliente = pr.cd_pessoa_cliente INNER JOIN AM_FORUM fo ON fo.cd_pessoa_forum = pr.cd_pessoa_forum WHERE pr.nr_processo = ?");
			stmt.setInt(1, codigo);
			
			resultado = stmt.executeQuery();
			if(resultado.next()){
				processo = new Processo();
				processo.setDescricao(resultado.getString("DS_PROCESSO"));
				processo.setDataAbertura(resultado.getDate("DT_ABERTURA"));
				processo.setDiaVencimento(resultado.getInt("DD_DIA_VENCIMENTO"));
				processo.setObservacao(resultado.getString("DS_OBSERVACAO"));
				processo.setResultado(resultado.getInt("CD_RESULTADO"));
				processo.setNumero(resultado.getInt("NR_PROCESSO"));
				processo.setDescricao(resultado.getString("DS_PROCESSO"));
				
				Forum forum = new Forum();
				forum.setCodigo(resultado.getInt("CD_PESSOA_FORUM"));
				forum.setDescricaoForum(resultado.getString("DS_FORUM"));
				processo.setForum(forum);
				Cliente cliente = new Cliente();
				cliente.setRazaoSocial(resultado.getString("NM_RAZAO_SOCIAL"));
				cliente.setEmail(resultado.getString("DS_EMAIL"));
				processo.setCliente(cliente);
				
				TipoCausa causa = new TipoCausa();
				causa.setCodigo(resultado.getInt("CD_CAUSA"));
				causa.setDescricao(resultado.getString("DS_CAUSA"));
				processo.setCausa(causa);
				
				Cobranca cobranca = new Cobranca();
				cobranca.setCodigo(resultado.getInt("CD_COBRANCA"));
				cobranca.setDescricao(resultado.getString("DS_COBRANCA"));
				cobranca.setTaxaJuros(resultado.getDouble("TX_JUROS"));
				cobranca.setValorMora(resultado.getDouble("VL_MORA_DIARIA"));
				processo.setCobranca(cobranca);
				
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e ){
			e.printStackTrace();
		}finally{
			try {
			conn.close();
			stmt.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return processo;
	}
	
	public List <Processo> listar() throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultado = null;
		List<Processo> lista = null;
		Processo processo = null;
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			
			stmt = conn.prepareStatement("SELECT * FROM AM_PROCESSO pr INNER JOIN AM_CLIENTE cl ON cl.CD_PESSOA_CLIENTE = pr.CD_PESSOA_CLIENTE INNER JOIN AM_FORUM fo ON fo.CD_PESSOA_FORUM = pr.cd_pessoa_forum INNER JOIN AM_TIPO_CAUSA tp ON tp.CD_CAUSA = pr.CD_CAUSA INNER JOIN AM_TIPO_COBRANCA co ON co.CD_COBRANCA = pr.CD_COBRANCA WHERE ROWNUM < 10 ORDER BY DT_ABERTURA DESC");
			resultado = stmt.executeQuery();
			
			lista = new ArrayList<Processo>();
			while(resultado.next()){
				processo = new Processo();
				processo.setDescricao(resultado.getString("DS_PROCESSO"));
				processo.setDataAbertura(resultado.getDate("DT_ABERTURA"));
				processo.setDiaVencimento(resultado.getInt("DD_DIA_VENCIMENTO"));
				processo.setObservacao(resultado.getString("DS_OBSERVACAO"));
				processo.setResultado(resultado.getInt("CD_RESULTADO"));
				processo.setNumero(resultado.getInt("NR_PROCESSO"));
				
				Forum forum = new Forum();
				forum.setCodigo(resultado.getInt("CD_PESSOA_FORUM"));
				processo.setForum(forum);
				
				Cliente cliente = new Cliente();
				cliente.setCodigo(resultado.getInt("CD_PESSOA_CLIENTE"));
				cliente.setRazaoSocial(resultado.getString("NM_RAZAO_SOCIAL"));
				processo.setCliente(cliente);
				
				TipoCausa causa = new TipoCausa();
				causa.setCodigo(resultado.getInt("CD_CAUSA"));
				causa.setDescricao(resultado.getString("DS_CAUSA"));
				processo.setCausa(causa);
				
				Cobranca cobranca = new Cobranca();
				cobranca.setCodigo(resultado.getInt("CD_COBRANCA"));
				cobranca.setDescricao(resultado.getString("DS_COBRANCA"));
				processo.setCobranca(cobranca);
				
				lista.add(processo);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e ){
			e.printStackTrace();
		}finally{
			conn.close();
			stmt.close();
		}
		
		return lista;
	}
	
	public List<Processo> buscarPorCliente(int codigo){
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultado = null;
		List<Processo> lista = null;
		
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			stmt = conn.prepareStatement("SELECT pr.nr_processo, pr.ds_processo, pr.dt_abertura, ca.ds_causa, cl.NM_RAZAO_SOCIAL FROM AM_PROCESSO pr INNER JOIN AM_TIPO_CAUSA ca ON ca.cd_causa = pr.CD_CAUSA INNER JOIN AM_CLIENTE cl ON cl.CD_PESSOA_CLIENTE = pr.CD_PESSOA_CLIENTE WHERE pr.CD_PESSOA_CLIENTE = ?");
			stmt.setInt(1, codigo);
			resultado = stmt.executeQuery();
			
			lista = new ArrayList<Processo>();
			while(resultado.next()){
				Processo processo = new Processo();
				Cliente cli = new Cliente();
				TipoCausa tipo = new TipoCausa();
				
				processo.setCausa(tipo);
				processo.setCliente(cli);
				processo.setNumero(resultado.getInt("NR_PROCESSO"));
				processo.setDescricao(resultado.getString("DS_PROCESSO"));
				processo.setDataAbertura(resultado.getDate("DT_ABERTURA"));
			    processo.setDataFechamento(resultado.getDate("DT_FECHAMENTO"));
			    tipo.setDescricao(resultado.getString("DS_CAUSA"));
			    processo.setCausa(tipo);
			    cli.setRazaoSocial(resultado.getString("NM_RAZAO_SOCIAL"));
			    processo.setCliente(cli);
			    lista.add(processo);
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
