package com.javamachine.persistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javamachine.beans.Cliente;
import com.javamachine.persistencia.connection.ConnectionFactory;

public class ClienteDAO {
	public List <Cliente> lista(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultado = null;
		List<Cliente> lista = null;
		Cliente cliente = null;
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			
			stmt = conn.prepareStatement("SELECT CD_PESSOA_CLIENTE,NM_RAZAO_SOCIAL,NR_CNPJ,NR_INSC_ESTADUAL,DS_EMAIL FROM AM_CLIENTE");
			
			resultado = stmt.executeQuery();
			lista = new ArrayList<Cliente>();
			while(resultado.next()){
				cliente = new Cliente();
				cliente.setCodigo(resultado.getInt("CD_PESSOA_CLIENTE"));
				cliente.setRazaoSocial(resultado.getString("NM_RAZAO_SOCIAL"));
				cliente.setCnpj(resultado.getLong("NR_CNPJ"));
				cliente.setInscricaoEstadual(resultado.getLong("NR_INSC_ESTADUAL"));
				cliente.setEmail(resultado.getString("DS_EMAIL"));
				lista.add(cliente);
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
	public Cliente carregar(int codigo){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultado = null;
		Cliente cliente = null;
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			
			stmt = conn.prepareStatement("SELECT CD_PESSOA_CLIENTE,NM_RAZAO_SOCIAL,NR_CNPJ,NR_INSC_ESTADUAL,DS_EMAIL FROM AM_CLIENTE WHERE CD_PESSOA_CLIENTE = ? ");
			stmt.setInt(1, codigo);
			resultado = stmt.executeQuery();
			
			if(resultado.next()){
				cliente = new Cliente();
				cliente.setNome(resultado.getString("NM_PESSOA"));
				cliente.setCodigo(resultado.getInt("CD_PESSOA_CLIENTE"));
				cliente.setRazaoSocial(resultado.getString("NM_RAZAO_SOCIAL"));
				cliente.setCnpj(resultado.getLong("NR_CNPJ"));
				cliente.setInscricaoEstadual(resultado.getLong("NR_INSC_ESTADUAL"));
				cliente.setEmail(resultado.getString("DS_EMAIL"));
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
		return cliente;
	}
	public List<Cliente> buscar(String query){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultado = null;
		List<Cliente> lista = null;
		Cliente cliente = null;
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			String sql = "SELECT cli.CD_PESSOA_CLIENTE, cli.NM_RAZAO_SOCIAL, cli.NR_CNPJ, cli.NR_INSC_ESTADUAL, cli.DS_EMAIL, p.NM_PESSOA FROM AM_CLIENTE cli INNER JOIN AM_PESSOA p ON cli.cd_pessoa_cliente = p.cd_pessoa WHERE cli.NM_RAZAO_SOCIAL LIKE UPPER( ? )";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+query+"%");
			resultado = stmt.executeQuery();
			
			lista = new ArrayList<Cliente>();
			while(resultado.next()){
				cliente = new Cliente();
				cliente.setNome(resultado.getString("NM_PESSOA"));
				cliente.setCodigo(resultado.getInt("CD_PESSOA_CLIENTE"));
				cliente.setRazaoSocial(resultado.getString("NM_RAZAO_SOCIAL"));
				cliente.setCnpj(resultado.getLong("NR_CNPJ"));
				cliente.setInscricaoEstadual(resultado.getLong("NR_INSC_ESTADUAL"));
				cliente.setEmail(resultado.getString("DS_EMAIL"));
				lista.add(cliente);
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
