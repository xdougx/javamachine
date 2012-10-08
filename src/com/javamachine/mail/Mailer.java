package com.javamachine.mail;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class Mailer {
	private static HtmlEmail mail;
	
	public Mailer config(){
		mail = new HtmlEmail();
		mail.setAuthentication("fiap.java.machine@gmail.com", "dnstuff3361");
		mail.setHostName("smtp.gmail.com");
		mail.setSmtpPort(587);
		return this;
	}
	
	public Mailer desinatario(String to, String alias) throws EmailException{
		mail.addTo(to, alias);
		mail.setFrom("fiap.java.machine@gmail.com", "JavaMachine");
		return this;
	}
	
	public Mailer assunto(String subject){
		mail.setSubject(subject);
		return this;
	}
	
	public Mailer corpo(String html) throws EmailException{
		mail.setMsg(html);
		return this;
	}

	public void enviar() throws EmailException{
		mail.send();
	}
	
}
