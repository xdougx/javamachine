package com.javamachine.persistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.javamachine.beans.Advogado;
import com.javamachine.beans.AdvogadoProcesso;
import com.javamachine.beans.Cliente;
import com.javamachine.beans.Cobranca;
import com.javamachine.beans.Processo;
import com.javamachine.beans.TipoCausa;
import com.javamachine.persistencia.connection.ConnectionFactory;

public class RelatorioProcessoDAO {
	public static String recuperarDataParticipacao(int codAdvogado, int codProcesso){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultado = null;
		String data = "";
		
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			stmt = conn.prepareStatement("SELECT DT_INICIO_PARTICIPACAO FROM AM_ADVOGADO_PROCESSO WHERE NR_PROCESSO = ? AND CD_PESSOA_ADV = ?");
			stmt.setInt(1, codProcesso);
			stmt.setInt(2, codAdvogado);
			resultado = stmt.executeQuery();
			
			if(resultado.next()){
				data = resultado.getDate("DT_INICIO_PARTICIPACAO").toString();
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
		
		return data;
	}
	public List<AdvogadoProcesso> buscarPorAdvogado(int codigo){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultado = null;
		List<AdvogadoProcesso> lista = null;
		
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			stmt = conn.prepareStatement("SELECT pr.nr_processo, pe.nm_pessoa, pr.ds_processo, pr.dt_abertura, pr.dt_fechamento, pr.cd_resultado, ca.ds_causa, pr.dd_dia_vencimento, adv_pr.dt_inicio_participacao FROM AM_PROCESSO pr INNER JOIN AM_ADVOGADO_PROCESSO adv_pr ON adv_pr.nr_processo = pr.nr_processo INNER JOIN AM_ADVOGADO adv ON adv.CD_PESSOA_ADV = adv_pr.cd_pessoa_adv INNER JOIN AM_PESSOA pe ON pe.CD_PESSOA = adv.CD_PESSOA_ADV INNER JOIN AM_TIPO_CAUSA ca ON ca.cd_causa = pr.CD_CAUSA WHERE adv.CD_PESSOA_ADV = ?");
			stmt.setInt(1, codigo);
			resultado = stmt.executeQuery();
			
			lista = new ArrayList<AdvogadoProcesso>();
			while(resultado.next()){
				Processo processo = new Processo();
				Advogado advogado = new Advogado();
				TipoCausa tipo = new TipoCausa();
				AdvogadoProcesso advProcesso = new AdvogadoProcesso();
		
				processo.setNumero(resultado.getInt("NR_PROCESSO"));
				advogado.setNome(resultado.getString("NM_PESSOA"));
				processo.setDescricao(resultado.getString("DS_PROCESSO"));
				processo.setDataAbertura(resultado.getDate("DT_ABERTURA"));
			    processo.setDataFechamento(resultado.getDate("DT_FECHAMENTO"));
			    processo.setResultado(resultado.getInt("CD_RESULTADO"));
			    tipo.setDescricao(resultado.getString("DS_CAUSA"));
			    processo.setCausa(tipo);
			    processo.setDiaVencimento(resultado.getInt("DD_DIA_VENCIMENTO"));
			    advProcesso.setAdvogado(advogado);
			    advProcesso.setProcesso(processo);
			    advProcesso.setDataParticipacao(resultado.getDate("DT_INICIO_PARTICIPACAO"));
			    lista.add(advProcesso);

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

	
	public List<Processo> buscarPorPeriodo(String inicio, String fim){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultado = null;
		List<Processo> lista = null;
		
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			stmt = conn.prepareStatement("SELECT pr.nr_processo, pr.ds_processo, pr.dt_abertura, ca.ds_causa, cli.nm_razao_social  FROM AM_PROCESSO pr INNER JOIN AM_CLIENTE cli ON cli.cd_pessoa_cliente = pr.cd_pessoa_cliente INNER JOIN AM_PESSOA pe ON pe.cd_pessoa = cli.cd_pessoa_cliente INNER JOIN AM_TIPO_CAUSA ca ON ca.cd_causa = pr.CD_CAUSA WHERE pr.dt_abertura BETWEEN cast(? AS date) AND cast(? AS date)");
			stmt.setString(1, inicio);
			stmt.setString(2, fim);
			resultado = stmt.executeQuery();
			
			lista = new ArrayList<Processo>();
			while(resultado.next()){
				Processo processo = new Processo();
				TipoCausa tipo = new TipoCausa();
				Cliente cli = new Cliente();
				processo.setNumero(resultado.getInt("NR_PROCESSO"));
				processo.setDescricao(resultado.getString("DS_PROCESSO"));
				processo.setDataAbertura(resultado.getDate("DT_ABERTURA"));
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
	
	public List<Processo> buscarPorCliente(int codigo){
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultado = null;
		List<Processo> lista = null;
		
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			stmt = conn.prepareStatement("SELECT pr.nr_processo, pr.ds_processo, pr.dt_abertura, pr.dt_fechamento, ca.ds_causa, co.ds_cobranca, pr.dd_dia_vencimento FROM AM_PROCESSO pr INNER JOIN AM_TIPO_CAUSA ca ON ca.cd_causa = pr.CD_CAUSA INNER JOIN AM_TIPO_COBRANCA co ON pr.CD_COBRANCA = co.CD_COBRANCA WHERE pr.CD_PESSOA_CLIENTE = ?");
			stmt.setInt(1, codigo);
			resultado = stmt.executeQuery();
			
			lista = new ArrayList<Processo>();
			while(resultado.next()){
				Processo processo = new Processo();
				Cobranca cobranca = new Cobranca();
				TipoCausa tipo = new TipoCausa();
				
				processo.setCausa(tipo);
				processo.setCobranca(cobranca);
				processo.setNumero(resultado.getInt("NR_PROCESSO"));
				processo.setDescricao(resultado.getString("DS_PROCESSO"));
				processo.setDataAbertura(resultado.getDate("DT_ABERTURA"));
			    processo.setDataFechamento(resultado.getDate("DT_FECHAMENTO"));
			    tipo.setDescricao(resultado.getString("DS_CAUSA"));
			    cobranca.setDescricao(resultado.getString("DS_COBRANCA"));
			    processo.setDiaVencimento(resultado.getInt("DD_DIA_VENCIMENTO"));
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


