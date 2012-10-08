package com.javamachine.persistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javamachine.beans.Advogado;
import com.javamachine.persistencia.connection.ConnectionFactory;

public class AdvogadoDAO {
	public static void main(String[] args) {
		AdvogadoDAO dao = new AdvogadoDAO();
		for(Advogado a : dao.lista()){
			System.out.println(a.getNome());
		}
	}
	//private List<Advogado> lista;
	
	public List <Advogado> lista(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultado = null;
		List<Advogado> lista = null;
		Advogado advogado = null;
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			
			stmt = conn.prepareStatement("SELECT CD_PESSOA_ADV,NR_OAB,NR_CPF,NR_RG,DS_EMAIL,NM_PESSOA FROM AM_ADVOGADO ad INNER JOIN AM_PESSOA pe ON pe.CD_PESSOA = ad.CD_PESSOA_ADV");
			
			resultado = stmt.executeQuery();
			lista = new ArrayList<Advogado>();
			while(resultado.next()){
				advogado = new Advogado();
				advogado.setCodigo(resultado.getInt("CD_PESSOA_ADV"));
				advogado.setOab(resultado.getInt("NR_OAB"));
				advogado.setCpf(resultado.getLong("NR_CPF"));
				advogado.setRg(resultado.getString("NR_RG"));
				advogado.setEmail(resultado.getString("DS_EMAIL"));
				advogado.setNome(resultado.getString("NM_PESSOA"));
				
				lista.add(advogado);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e ){
			e.printStackTrace();
		}finally{
			try {
				conn.close();
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return lista;
	}
	
	public Advogado carregar(int codigo){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultado = null;
		Advogado advogado = null;
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			
			stmt = conn.prepareStatement("SELECT CD_PESSOA_ADV,NR_OAB,NR_CPF,NR_RG,DS_EMAIL,NM_PESSOA FROM AM_ADVOGADO ad INNER JOIN AM_PESSOA pe ON pe.CD_PESSOA = ad.CD_PESSOA_ADV WHERE ad.CD_PESSOA_ADV = ?");
			stmt.setInt(1, codigo);
			resultado = stmt.executeQuery();
			if(resultado.next()){
				advogado = new Advogado();
				advogado.setCodigo(resultado.getInt("CD_PESSOA_ADV"));
				advogado.setOab(resultado.getInt("NR_OAB"));
				advogado.setCpf(resultado.getLong("NR_CPF"));
				advogado.setRg(resultado.getString("NR_RG"));
				advogado.setEmail(resultado.getString("DS_EMAIL"));
				advogado.setNome(resultado.getString("NM_PESSOA"));
				
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e ){
			e.printStackTrace();
		}finally{
			try {
				conn.close();
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return advogado;
	}
	
	public List<Advogado> listarPorTipoDeCausa(int codigo){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultado = null;
		List<Advogado> lista = null;
		Advogado advogado = null;
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			String sql = "SELECT adv.*, p.NM_PESSOA FROM AM_ADVOGADO adv INNER JOIN AM_ADVOGADO_TIPO_CAUSA t ON adv.CD_PESSOA_ADV = t.CD_PESSOA_ADV INNER JOIN AM_PESSOA p ON p.CD_PESSOA = adv.CD_PESSOA_ADV WHERE t.CD_CAUSA = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, codigo);
			
			resultado = stmt.executeQuery();
			lista = new ArrayList<Advogado>();
			while(resultado.next()){
				advogado = new Advogado();
				advogado.setCodigo(resultado.getInt("CD_PESSOA_ADV"));
				advogado.setOab(resultado.getInt("NR_OAB"));
				advogado.setCpf(resultado.getLong("NR_CPF"));
				advogado.setRg(resultado.getString("NR_RG"));
				advogado.setEmail(resultado.getString("DS_EMAIL"));
				advogado.setNome(resultado.getString("NM_PESSOA"));
				
				lista.add(advogado);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e ){
			e.printStackTrace();
		}finally{
			try {
				conn.close();
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return lista;
	}
	
	public List<Advogado> buscar(String busca){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultado = null;
		List<Advogado> lista = null;
		Advogado advogado = null;
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			
			stmt = conn.prepareStatement("SELECT CD_PESSOA_ADV,NR_OAB,NR_CPF,NR_RG,DS_EMAIL,NM_PESSOA FROM AM_ADVOGADO ad INNER JOIN AM_PESSOA pe ON pe.CD_PESSOA = ad.CD_PESSOA_ADV WHERE pe.NM_PESSOA LIKE ?");
			stmt.setString(1, "%"+busca+"%");
			resultado = stmt.executeQuery();
			lista = new ArrayList<Advogado>();
			while(resultado.next()){
				advogado = new Advogado();
				advogado.setCodigo(resultado.getInt("CD_PESSOA_ADV"));
				advogado.setOab(resultado.getInt("NR_OAB"));
				advogado.setCpf(resultado.getLong("NR_CPF"));
				advogado.setRg(resultado.getString("NR_RG"));
				advogado.setEmail(resultado.getString("DS_EMAIL"));
				advogado.setNome(resultado.getString("NM_PESSOA"));
				
				lista.add(advogado);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e ){
			e.printStackTrace();
		}finally{
			try {
				conn.close();
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return lista;
	}
}
