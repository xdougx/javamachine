package com.javamachine.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

public class HomeController extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	@Action(value="index", results={
			  @Result(location="/index.jsp", name="home")	
			})
	public String home() {
		return "home";
	}

}
