package com.javamachine.beans;

import java.io.Serializable;
import java.util.List;

public class Advogado extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	public Advogado() {
		
	}
	private int oab;
	private long cpf;
	private String rg;
	private String email;
	private String passaword;
	private List<HoraAdvogado> horaAdvogado;

	public int getOab() {
		return oab;
	}
	public void setOab(int oab) {
		this.oab = oab;
	}
	public long getCpf() {
		return cpf;
	}
	public void setCpf(long cpf) {
		this.cpf = cpf;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
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
	public List<HoraAdvogado> getHoraAdvogado() {
		return horaAdvogado;
	}
	public void setHoraAdvogado(List<HoraAdvogado> horaAdvogado) {
		this.horaAdvogado = horaAdvogado;
	}

}
