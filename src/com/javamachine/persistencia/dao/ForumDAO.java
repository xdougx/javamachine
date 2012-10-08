package com.javamachine.persistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javamachine.beans.Forum;
import com.javamachine.persistencia.connection.ConnectionFactory;

public class ForumDAO {
	public static void main(String[] args) {
		ForumDAO dao = new ForumDAO();
		List<Forum> lista = dao.lista();
		
		for(Forum f : lista){
			System.out.println(f.getCodigo() + " - " + f.getNome());
		}
	}
	public List <Forum> lista(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultado = null;
		List<Forum> lista = null;
		try {
			conn = ConnectionFactory.obterConexaoOracle();
			
			stmt = conn.prepareStatement("SELECT forum.CD_PESSOA_FORUM, forum.DS_FORUM, pessoa.NM_PESSOA FROM AM_FORUM forum INNER JOIN AM_PESSOA pessoa ON pessoa.CD_PESSOA = forum.CD_PESSOA_FORUM");
			
			resultado = stmt.executeQuery();
			lista = new ArrayList<Forum>();
			while(resultado.next()){
				
				Forum forum = new Forum();
			    forum.setCodigo(resultado.getInt("CD_PESSOA_FORUM"));
				forum.setDescricaoForum(resultado.getString("DS_FORUM"));
				forum.setNome(resultado.getString("NM_PESSOA"));
				
				
				lista.add(forum);
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



