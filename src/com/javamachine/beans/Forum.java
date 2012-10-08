package com.javamachine.beans;

import java.io.Serializable;

public class Forum extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	public Forum() {
		
	}
	
	private String descricaoForum;

	public String getDescricaoForum() {
		return descricaoForum;
	}
	public void setDescricaoForum(String descricaoForum) {
		this.descricaoForum = descricaoForum;
	}




}
