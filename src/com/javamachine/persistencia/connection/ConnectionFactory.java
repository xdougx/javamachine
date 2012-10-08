package com.javamachine.persistencia.connection; 

import java.sql.Connection;
import java.sql.DriverManager;
public class ConnectionFactory {
	private static Connection con = null;
	public static Connection obterConexaoOracle() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		try{
			con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL","OPS$RM65306","300983");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return con;
		
	}
	
}


