package com.javamachine.action;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.javamachine.BO.GerenciarProcessoBO;
import com.javamachine.beans.Advogado;
import com.javamachine.beans.AdvogadoProcesso;
import com.javamachine.beans.Cliente;
import com.javamachine.beans.Cobranca;
import com.javamachine.beans.Forum;
import com.javamachine.beans.Processo;
import com.javamachine.beans.TipoCausa;
import com.javamachine.persistencia.dao.AdvogadoDAO;
import com.javamachine.persistencia.dao.AdvogadoProcessoDAO;
import com.javamachine.persistencia.dao.ClienteDAO;
import com.javamachine.persistencia.dao.ForumDAO;
import com.javamachine.persistencia.dao.ProcessoDAO;
import com.javamachine.persistencia.dao.TipoCausaDAO;
import com.opensymphony.xwork2.ActionSupport;

public class ProcessosController extends ActionSupport {
	
	/** @category attributes */
	private static final long serialVersionUID = 1L;
	/** @category attributes */
	private List<Processo> processos;
	/** @category attributes */
	private List<Advogado> advogados;
	/** @category attributes */
	private List<AdvogadoProcesso> advogadosProcesso;
	/** @category attributes */
	private AdvogadoProcesso advogadoProcesso;
	/** @category attributes */
	private List<TipoCausa> tipoCausas;
	/** @category attributes */
	private List<Cliente>clientes;
	/** @category attributes */
	private List<Forum>foruns;
	/** @category attributes */
	private Processo processo;
	/** @category attributes */
	private Advogado advogado;
	/** @category attributes */
	private TipoCausa tipoCausa;
	/** @category attributes */
	private Cobranca cobranca;
	/** @category attributes */
	private Cliente cliente;
	/** @category attributes */
	private Forum forum;
	/** @category attributes */
	private Integer codigo;
	/** @category attributes */
	private String query;
	/** @category attributes */
	private String diaVencimento;
	/** @category attributes */
	private String dataAbertura;
	
	@Action(value="gerenciar_processos", results={
			  @Result(location="/processos/gerenciarProcessos.jsp", name="gerenciar")	
			})
	public String listar() {
		GerenciarProcessoBO gerenciarBO = new GerenciarProcessoBO();
		this.processos = gerenciarBO.listarProcessos();
		this.processo = new Processo();
		
		return "gerenciar";
	}
	
	@Action(value="remover_processo", results={
			  @Result(location="/processos/gerenciarProcessos.jsp", name="remover")	
			})
	public String remover(){
		GerenciarProcessoBO gerenciarBO = new GerenciarProcessoBO();
		gerenciarBO.removerProcesso(this.processo.getNumero());
		return "remover";
		
	}
	
	@Action(value="gravar_processo", results={
			  @Result(location="/processos/gerenciarProcessos.jsp", name="inserir")	
			})
	public String adicionar(){
		this.processo.setCliente(this.cliente);
		this.processo.setForum(this.forum);
		this.processo.setCausa(this.tipoCausa);
		this.processo.setCobranca(this.cobranca);
		this.processo.setDiaVencimento(Integer.parseInt(this.diaVencimento));
		
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");  
		try {
			Date data = new Date(format.parse(this.dataAbertura).getTime());
			processo.setDataAbertura(data);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		GerenciarProcessoBO gerenciarBO = new GerenciarProcessoBO();
		gerenciarBO.cadastrar(processo);
		this.processos = gerenciarBO.listarProcessos();
		return "inserir"; 
	}
	
	@Action(value="gravar_atualizar_processo", results={
			  @Result(location="/processos/gerenciarProcessos.jsp", name="gravar_atualizacao")	
			})
	public String gravarAtualizacao(){
		this.processo.setCliente(this.cliente);
		this.processo.setForum(this.forum);
		this.processo.setCausa(this.tipoCausa);
		this.processo.setCobranca(this.cobranca);
		this.processo.setDiaVencimento(Integer.parseInt(this.diaVencimento));
		
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");  
		try {
			Date data = new Date(format.parse(this.dataAbertura).getTime());
			processo.setDataAbertura(data);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		GerenciarProcessoBO gerenciarBO = new GerenciarProcessoBO();
		gerenciarBO.alterarProcesso(processo);
		this.processos = gerenciarBO.listarProcessos();
		return "gravar_atualizacao"; 
	}
	
	@Action(value="listar_advogados", results={
			  @Result(location="/processos/listar_advogados.jsp", name="listar_advogados")	
			})
	public String listarAdvogados(){
		AdvogadoDAO advogadoDAO = new AdvogadoDAO();
		this.advogados = advogadoDAO.listarPorTipoDeCausa(this.getCodigo());
		return "listar_advogados"; 
	}
	
	@Action(value="cadastrar_processo", results={
			  @Result(location="/processos/formCadastrarProcesso.jsp", name="cadastrar")	
			})
	public String cadastrar(){
		AdvogadoDAO advogadoDAO = new AdvogadoDAO();
		this.advogados = advogadoDAO.lista();
		
		ClienteDAO clienteDao = new ClienteDAO();
		this.clientes = clienteDao.lista();
		
		TipoCausaDAO tipoCausaDao = new TipoCausaDAO();
		this.tipoCausas = tipoCausaDao.lista();
		
		ForumDAO forumDao = new ForumDAO();
		this.foruns = forumDao.lista();
		
		return "cadastrar";
		
	}
	
	@Action(value="atualizar_processo", results={
			  @Result(location="/processos/formAtualizarProcesso.jsp", name="atualizar")	
			})
	public String atualizar(){
		ProcessoDAO processoDAO = new ProcessoDAO(); 
		this.processo = processoDAO.consultar(this.processo.getNumero());
		
		AdvogadoDAO advogadoDAO = new AdvogadoDAO();
		this.advogados = advogadoDAO.listarPorTipoDeCausa(this.processo.getCausa().getCodigo());
		
		ClienteDAO clienteDao = new ClienteDAO();
		this.clientes = clienteDao.lista();
		
		TipoCausaDAO tipoCausaDao = new TipoCausaDAO();
		this.tipoCausas = tipoCausaDao.lista();
		
		ForumDAO forumDao = new ForumDAO();
		this.foruns = forumDao.lista();
		
		AdvogadoProcessoDAO advProcDao = new AdvogadoProcessoDAO();
		
		this.advogadosProcesso = advProcDao.listar(this.processo.getNumero());

		return "atualizar";
	}
	
	
	@Action(value="buscar_cliente", results={
			  @Result(location="/processos/buscarCliente.jsp", name="buscar_cliente")	
			})
	public String buscarCliente(){
		ClienteDAO dao = new ClienteDAO();
		this.clientes = dao.buscar(this.getQuery());
		return "buscar_cliente";
	}
	
	@Action(value="gravar_advogado_processo", results={
			  @Result(location="/processos/_gravar_advogado_processo.jsp", name="gravar_advogado_processo")	
			})
	public String gravarAdvogadoProcesso(){
		GerenciarProcessoBO bo = new GerenciarProcessoBO();
		this.advogadoProcesso.setAdvogado(this.advogado);
		this.advogadoProcesso.setProcesso(this.processo);
		bo.cadastrarAdvogadoProcesso(this.advogadoProcesso);
		
		AdvogadoProcessoDAO advProcDao = new AdvogadoProcessoDAO();
		this.advogadosProcesso = advProcDao.listar(this.advogadoProcesso.getProcesso().getNumero());
		
		return "gravar_advogado_processo";
	}
	
	@Action(value="remover_advogado_processo", results={
			  @Result(location="/processos/remover.jsp", name="remover_advogado_processo")	
			})
	public String removerAdvogadoProcesso(){
		GerenciarProcessoBO bo = new GerenciarProcessoBO();
		this.advogadoProcesso = new AdvogadoProcesso();
		this.advogadoProcesso.setAdvogado(this.advogado);
		this.advogadoProcesso.setProcesso(this.processo);
		bo.removerAdvogadoProcesso(this.advogadoProcesso);
		return "remover_advogado_processo";
	}
	
	/**
	 * @category getter setter
	 */
	public List<Processo> getProcessos() {
		return processos;
	}
	/**
	 * @category getter setter
	 */
	public List<Advogado> getAdvogados() {
		return advogados;
	}
	/**
	 * @category getter setter
	 */
	public List<TipoCausa> getTipoCausas() {
		return tipoCausas;
	}
	/**
	 * @category getter setter
	 */
	public List<Cliente> getClientes() {
		return clientes;
	}
	/**
	 * @category getter setter
	 */
	public List<Forum> getForuns() {
		return foruns;
	}
	/**
	 * @category getter setter
	 */
	public Processo getProcesso() {
		return processo;
	}
	/**
	 * @category getter setter
	 */
	public Advogado getAdvogado() {
		return advogado;
	}
	/**
	 * @category getter setter
	 */
	public TipoCausa getTipoCausa() {
		return tipoCausa;
	}
	/**
	 * @category getter setter
	 */
	public Cliente getCliente() {
		return cliente;
	}
	/**
	 * @category getter setter
	 */
	public Forum getForum() {
		return forum;
	}
	/**
	 * @category getter setter
	 */
	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}
	/**
	 * @category getter setter
	 */
	public void setAdvogados(List<Advogado> advogados) {
		this.advogados = advogados;
	}
	/**
	 * @category getter setter
	 */
	public void setTipoCausas(List<TipoCausa> tipoCausas) {
		this.tipoCausas = tipoCausas;
	}
	/**
	 * @category getter setter
	 */
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	/**
	 * @category getter setter
	 */
	public void setForuns(List<Forum> foruns) {
		this.foruns = foruns;
	}
	/**
	 * @category getter setter
	 */
	public void setProcesso(Processo processo) {
		this.processo = processo;
	}
	/**
	 * @category getter setter
	 */
	public void setAdvogado(Advogado advogado) {
		this.advogado = advogado;
	}
	/**
	 * @category getter setter
	 */
	public void setTipoCausa(TipoCausa tipoCausa) {
		this.tipoCausa = tipoCausa;
	}
	/**
	 * @category getter setter
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	/**
	 * @category getter setter
	 */
	public void setForum(Forum forum) {
		this.forum = forum;
	}
	/**
	 * @category getter setter
	 */
	public Integer getCodigo() {
		return codigo;
	}
	/**
	 * @category getter setter
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	/**
	 * @category getter setter
	 */
	public String getQuery() {
		return query;
	}
	/**
	 * @category getter setter
	 */
	public void setQuery(String query) {
		this.query = query;
	}
	/**
	 * @category getter setter
	 */
	public Cobranca getCobranca() {
		return cobranca;
	}
	/**
	 * @category getter setter
	 */
	public void setCobranca(Cobranca cobranca) {
		this.cobranca = cobranca;
	}
	/**
	 * @category getter setter
	 */
	public String getDiaVencimento() {
		return diaVencimento;
	}
	/**
	 * @category getter setter
	 */
	public void setDiaVencimento(String diaVencimento) {
		this.diaVencimento = diaVencimento;
	}
	/**
	 * @category getter setter
	 */
	public String getDataAbertura() {
		return dataAbertura;
	}
	/**
	 * @category getter setter
	 */
	public void setDataAbertura(String dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	/**
	 * @category getter setter
	 */
	public List<AdvogadoProcesso> getAdvogadosProcesso() {
		return advogadosProcesso;
	}
	/**
	 * @category getter setter
	 */
	public void setAdvogadosProcesso(List<AdvogadoProcesso> advogadosProcesso) {
		this.advogadosProcesso = advogadosProcesso;
	}
	/**
	 * @category getter setter
	 */
	public void setAdvogadoProcesso(AdvogadoProcesso advogadoProcesso) {
		this.advogadoProcesso = advogadoProcesso;
	}
	/**
	 * @category getter setter
	 */
	public AdvogadoProcesso getAdvogadoProcesso() {
		return advogadoProcesso;
	}
	
	
	
}
