package com.javamachine.interfaces;

import java.sql.SQLException;

import com.javamachine.beans.Processo;

public interface InterfaceDaoProcesso {
	public void inserir(Processo processo) throws SQLException;
	public void alterar(Processo processo) throws SQLException ;
	public void remover(int codigo) throws SQLException;
	public Processo consultar(int codigo) throws SQLException;
}
