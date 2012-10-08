package com.javamachine.mail;

import java.util.HashMap;
import java.util.Map;

import com.javamachine.beans.Advogado;
import com.javamachine.beans.Cliente;
import com.javamachine.beans.Processo;

public class MailTemplate {
	public static Map<String, String> htmlTemplateForAdvogado(Advogado advogado, Processo processo){
		Map<String, String> template = new HashMap<String, String>();
		
		String assunto = "Processo "+ processo.getNumero() +" bloqueado";
		template.put("assunto", assunto);
		
		String corpo = "<html>" +
					   "  <head>" +
					   "    <style> body {font-family: verdana; }</style>" +
					   "  </head>" +
					   "  <body>" +
					   "    <p>Prezado(a) "+ advogado.getNome() +",</p>" +
					   "    <p>O processo <strong>Nº "+ processo.getNumero() +"</strong> foi bloqueado. Não lance honorários, despesas ou agende audiências até que a situação esteja normalizada.</p>" +
					   "    <p>Saudações,</p>" +
					   "    <p>Departamento de Cobrança</p>" +
					   "  </body>" +
					   "</html>";
		template.put("corpo", corpo);
		
		return template;
		
	}
	
	public static Map<String, String> htmlTemplateForClient(Processo processo){
		Map<String, String> template = new HashMap<String, String>();
		
		String assunto = "Processo "+ processo.getNumero() +" pagamento atrasado";
		template.put("assunto", assunto);
		
		String corpo = "<html>" +
					   "  <head>" +
					   "    <style> body {font-family: verdana; }</style>" +
					   "  </head>" +
					   "  <body>" +
					   "    <p>Prezado(a) "+ processo.getCliente().getRazaoSocial() +",</p>" +
					   "    <p>Não consta em nosso sistema o pagamento do documento "+ processo.getNumero() +" vinculado ao processo <№ processo>. Caso o documento já tenha sido pago, desconsidere esta mensagem.</p>" +
					   "    <p>Saudações,</p>" +
					   "    <p>Departamento de Cobrança</p>" +
					   "  </body>" +
					   "</html>";
		template.put("corpo", corpo);
		
		return template;
		
	}
}
