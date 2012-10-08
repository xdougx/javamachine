<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<script type="text/javascript">
  $(document).ready(function(){
  	$("#cadastro").click(function(){
  		$("#form").load("cadastrar_processo");
  		return false;
  	});
  	$("a.remover").click(function(e){
  		e.stopPropagation();
  		var codigo_processo = $(this).attr("data-processo");
  		var data = {
  			"processo.numero": codigo_processo
  		};
  		$.ajax({
			url: "remover_processo",
			type: 'POST',
			data: data,
			success: function(){
				$(this).parent().parent().remove();
			}
		});
  	});
  	
  	$("a.atualizar").click(function(e){
  		e.stopPropagation();
  		var codigo_processo = $(this).attr("data-processo");
  		$("#content").load("atualizar_processo", { "processo.numero":codigo_processo });
  		return false;
  	});
  	
  	$(".date").each(function(){
  		var valor = $(this).text();
  		$(this).text(format(valor));
  	});
  	
  	$(".resultado").each(function(){
  		var valor = $(this).text();
  		if(valor == "0") valor = "Em Andamento";
  		if(valor == "1") valor = "Causa Ganha";
  		if(valor == "2") valor = "Causa Perdida";
  		$(this).text(valor);
  	});
  	
  	
  	function format(valor){
  		valor = valor.split("-");
  		valor = valor[2]+"/"+valor[1]+"/"+valor[0];
  		return valor;
  	}
  });
</script>
<div id="form">
	<h3>Processos</h3>
	<div align="right" id="link-conteiner"><a id="cadastro" href="#">Cadastrar</a></div>
	<table class="tabela-dados">
	  <tr class="title">
	    <td>Numero</td>
	    <td width="250">Descri&ccedil;&atilde;o</td>
	    <td>Cliente</td>
	    <td>Data da Abertura</td>
	    <td>Resultado</td>
	    <td></td>
	  </tr>
	  <s:iterator value="processos">
	  <tr>
	    <td>${numero}</td>
	    <td>${descricao}</td>
	    <td>${cliente.razaoSocial}</td>
	    <td class="date">${dataAbertura}</td>
	    <td class="resultado">${resultado}</td>
	    <td>
	      <a href="#" class="atualizar" data-processo="${numero}"><i class="icon-edit" style="font-size: 20px;"></i></a>
	      <a href="#" class="remover" data-processo="${numero}"> <i class="icon-trash" style="font-size: 20px;"></i></a>
	    </td>
	  </tr>
	  </s:iterator>
	</table>
		
</div>