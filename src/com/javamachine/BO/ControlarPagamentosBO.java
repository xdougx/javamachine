package com.javamachine.BO;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.mail.EmailException;

import com.javamachine.beans.AdvogadoProcesso;
import com.javamachine.beans.Bloqueio;
import com.javamachine.beans.EmailCobranca;
import com.javamachine.beans.HistoricoBloqueio;
import com.javamachine.beans.HistoricoTaxa;
import com.javamachine.beans.Processo;
import com.javamachine.beans.Titulo;

import com.javamachine.mail.MailTemplate;
import com.javamachine.mail.Mailer;

import com.javamachine.persistencia.dao.AdvogadoProcessoDAO;
import com.javamachine.persistencia.dao.BloqueioDAO;
import com.javamachine.persistencia.dao.EmailCobrancaDAO;
import com.javamachine.persistencia.dao.HistoricoTaxaDAO;
import com.javamachine.persistencia.dao.TituloDAO;

public class ControlarPagamentosBO {
	public static final long MILLISEGUNDOS_POR_DIA = 24 * 60 * 60 * 1000;
	public void verificarAtrasos(){
		TituloDAO dao = new TituloDAO();
		List<Titulo> titulos = dao.buscarTitulosAtrasados();
		
		for(Titulo titulo : titulos){
			long atraso = this.diasAtrasados(titulo.getDataVencimento());
			boolean enviarEmail = atraso % 7 == 0;
			boolean bloquear = atraso >= 60;
			
			this.enviarEmailAtraso(titulo.getProcesso(), enviarEmail);
			this.bloquearProcesso(titulo.getProcesso(), bloquear);
			this.calcularJuros(titulo, (int)atraso, enviarEmail);
		}
	}
	
	public List<Titulo> listarBloqueios(){
		TituloDAO dao = new TituloDAO();
		return dao.buscarTitulosAtrasados();

	}
	
	private void calcularJuros(Titulo titulo, int diasAtrasados, boolean enviarEmail) {
		if(enviarEmail){ //Verifica se o atraso é de 7 dias
			//Recupera o valor da Taxa
			double taxa = titulo.getProcesso().getCobranca().getTaxaJuros();
			
			//Cria o histórico
			HistoricoTaxa historico = new HistoricoTaxa();
			historico.setCobranca(titulo.getProcesso().getCobranca());
			historico.setVigencia(new Date(this.getCurrentTime()));
			
			//Calcula o valor dos Juros
			historico.setValor(taxa * ((int)(diasAtrasados / 30))  + diasAtrasados);
			
			//Registra
			HistoricoTaxaDAO dao = new HistoricoTaxaDAO();
			dao.registrarHistoricoTaxa(historico);
		}
	}
	
	private void bloquearProcesso(Processo processo, boolean bloquear) {
		if(bloquear){
			BloqueioDAO dao = new BloqueioDAO();
			
			//Cria o bloqueio
			Bloqueio bloqueio = new Bloqueio();
			bloqueio.setDescricao("Atraso de pagamento");
			bloqueio.setCodigo(dao.criarBloqueio(bloqueio));
			
			//Cria o Histórico
			HistoricoBloqueio historico = new HistoricoBloqueio();
			historico.setBloqueio(bloqueio);
			historico.setData(new Date(this.getCurrentTime()));
			historico.setProcesso(processo);
			
			//Registra o Histórico
			dao.criarHistorico(historico);
			
			//Recupera os Advogados Participantes
			AdvogadoProcessoDAO daoAdv = new AdvogadoProcessoDAO();
			List<AdvogadoProcesso> advogados = daoAdv.listar(processo.getNumero());
			
			//Envia Email
			this.enviarEmailBloqueioAdvoados(advogados);
		}	
	}
	
	private void enviarEmailBloqueioAdvoados(List<AdvogadoProcesso> advogados) {
		for(AdvogadoProcesso advProc : advogados){
			//Recupera o template
			Map<String, String> template = MailTemplate.htmlTemplateForAdvogado(advProc.getAdvogado(), advProc.getProcesso());
			
			//Envia o email
			Mailer mail = new Mailer();
			try {
				mail.config()
					.desinatario(advProc.getAdvogado().getEmail(), advProc.getAdvogado().getNome())
					.assunto(template.get("assunto"))
					.corpo(template.get("corpo"))
					.enviar();
			} catch (EmailException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void enviarEmailAtraso(Processo processo, boolean enviar) {
		if(enviar){
			//Recupera o template
			Map<String, String> template = MailTemplate.htmlTemplateForClient(processo);
			
			//Envia o email
			Mailer mail = new Mailer();
			try {
				mail.config()
					.desinatario(processo.getCliente().getEmail(), processo.getCliente().getRazaoSocial())
					.assunto(template.get("assunto"))
					.corpo(template.get("corpo"))
					.enviar();
			} catch (EmailException e) {
				e.printStackTrace();
			}
			
			//Registra a cobrança no Banco
			EmailCobranca mailCobranca  = new EmailCobranca();
			mailCobranca.setProcesso(processo);
			mailCobranca.setDataGeracao(new Date(this.getCurrentTime()));
			mailCobranca.setDataEnvio(new Date(this.getCurrentTime()));
			mailCobranca.setDescricaoAssunto((template.get("assunto")));
			mailCobranca.setCorpoEmail(template.get("corpo"));
			EmailCobrancaDAO dao = new EmailCobrancaDAO();
			dao.registrarEmail(mailCobranca);
		}
	}
	
	private long diasAtrasados(Date data){
		return ((this.getCurrentTime() - data.getTime()) / ControlarPagamentosBO.MILLISEGUNDOS_POR_DIA);
	}
	
	private Long getCurrentTime(){
		java.util.Date utilDate = new java.util.Date();
		Date now = new Date(utilDate.getTime());
		return now.getTime();
	}	

}
