package com.javamachine.beans;

import java.io.Serializable;

public class Cliente extends Pessoa {

	private static final long serialVersionUID = 1L;



	public Cliente() {	}
	
	
	private String razaoSocial;
	private long cnpj;
	private long inscricaoEstadual;
	private String email;
	private String passaword;
	
	

	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
		
	}
	public long getCnpj() {
		return cnpj;
	}
	public void setCnpj(long cnpj) {
		this.cnpj = cnpj;
	}
	public long getInscricaoEstadual() {
		return inscricaoEstadual;
	}
	public void setInscricaoEstadual(long inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassaword() {
		return passaword;
	}
	public void setPassaword(String passaword) {
		this.passaword = passaword;
	}
	
	
}

