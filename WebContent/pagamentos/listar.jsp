<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<script type="text/javascript">
  $(document).ready(function(){
  	$(".tabela-dados tr:odd").css("background-color", "#f0f0f0");
  	
  	$(".date").each(function(){
  		var valor = $(this).text();
  		$(this).text(format(valor));
  	});
  	
  	function format(valor){
  		valor = valor.split("-");
  		valor = valor[2]+"/"+valor[1]+"/"+valor[0];
  		return valor;
  	}
  });
</script>
<h3>Titulos em Atraso</h3>
<table cellpadding="5" cellspacing="0" class="tabela-dados">
  <tr class="title">
    <td># Numero Processo</td>
    <td>Cliente</td>
    <td>Data do Documento</td>
    <td>Data do Vencimento</td>
    <td>Valor do Documento</td>
  </tr>
  <s:iterator value="atrasos">
  <tr>
    <td>${processo.numero}</td>
    <td>${processo.cliente.razaoSocial}</td>
    <td class="date">${dataDocumento}</td>
    <td class="date">${dataVencimento}</td>
    <td>R$ ${valor}</td>
  </tr>
  </s:iterator>
</table>