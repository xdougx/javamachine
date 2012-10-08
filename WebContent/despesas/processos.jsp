<%@page import="com.javamachine.persistencia.dao.RelatorioProcessoDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
  <h3>Informações do Processo</h3> 
  <table cellpadding="5" cellspacing="0" class="tabela-dados">
    <tr>
      <td>#</td>
      <td>Descricao</td>
      <td>Data Abertura</td>
      <td>Causa</td>
      <td>Cliente</td>
    </tr>
    <tr>
      <td id="processo">${processo.numero}</td>
      <td>${processo.descricao}</td>
      <td>${processo.dataAbertura}</td>
      <td>${processo.causa.descricao}</td>
      <td>${processo.cliente.razaoSocial}</td>
    </tr>
  </table>
  <div id="lancamento" style="display:none;">
  	<h3>Realizar novo Lançamento</h3>
  	<form id="form-lancar-despesa" method="post">
	  	<table cellpadding="5" cellspacing="0" class="tabela-dados">
	  	  <tr>
	  	    <td>Tipo de Despesa</td>
	  	    <td><s:select list="tiposDespesa" listKey="codigo" theme="simple" name="codigoTipoDespesa" listValue="descricao"></s:select></td>
	  	  </tr>
	  	  <tr>
	  	    <td>Data</td>
	  	    <td><input type="text" name="despesa.data" /></td>
	  	  </tr>
	  	  <tr>
	  	    <td>Valor</td>
	  	    <td><input type="text" name="despesa.valor" /></td>
	  	  </tr>
	  	  <tr>
	  	    <td>Descrição</td>
	  	    <td><textarea name="despesa.descricao"></textarea></td>
	  	  </tr>
	  	  <tr>
	  	    <td colspan="2">
	  	    	<input type="submit" id="submit" name="submit" value="Gravar">
	  	    	<input type="hidden" name="despesa.processo.numero">
	  	    </td>
	  	  </tr>
	  	</table>
  	</form>
  </div>
  <h3>Despesas</h3>
  <p align="right"><a href="#" id="add-despesa">+ Adicionar Despesa</a></p>
  <table cellpadding="5" cellspacing="0" class="tabela-dados" id="tabela-lancamentos">
    <tr>
      <td>#</td>
      <td>Tipo de Despesa</td>
      <td>Data Lançamento</td>
      <td>Valor</td>
      <td>Descrição</td>
      <td> - </td>
    </tr>
    <s:iterator value="despesas">
    <tr>
      <td>${codigo}</td>
      <td>${tipoDespesa.descricao}</td>
      <td>${data}</td>
      <td>${valor}</td>
      <td>${descricao}</td>
      <td><a href="#" class="remover">remover</a></td>
    </tr>
    </s:iterator>
  </table>
  <script>
    $(".tabela-dados tr:odd").css("background-color", "#f0f0f0");
    $("#add-despesa").click(function(){
    	$("#lancamento").fadeIn();
    	$("[name='despesa.processo.numero']").val($("#processo").text());
    });
    
    $(".remover").click(function(e){
    	e.preventDefault();
    	
    	var nr_lancamento = $(this).parent().parent().find("td:first-child").html();    	
    	$(this).load("remover_lancamento",{busca:nr_lancamento});
    	$(this).parent().parent().fadeOut(200, function(){
    		$(this).remove();
    	});
    });
    
    $("#submit").click(function(){
   	  	var options = {
   	    	url: "gravar_despesa",
   	    	success: function(data){
   	    		var nr_processo = $("#processo").text();
   	    		$("#tabela-lancamentos").fadeOut(200).load("recarregar_lancamentos", {busca:nr_processo}).fadeIn(200);
   	    	}
   	    };
   		$("#form-lancar-despesa").ajaxForm(options);
    });
  </script>