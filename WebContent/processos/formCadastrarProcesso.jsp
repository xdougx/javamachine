<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<script type="text/javascript">
  $(document).ready(function(){
  	$(".tabela-dados tr:odd").css("background-color", "#f0f0f0");
  	
  	$("#submit").click(function(){
  	  	var options = {
  	    	target: "#content",
  	    	url: "gravar_processo",
  	    	success: function(){}
  	    };
  		$("#form-cadastrar-processo").ajaxForm(options);
  		//return false;
  	});
  	
  	$("#tipoCausa").change(function(){
  		$("#listaAdvogados").load("listar_advogados", {codigo: $("#tipoCausa option:selected").val()});
  	});
  	
  });
</script>
<h3>Cadastrar Processo</h3>
<s:form action="gravar_processo" method="post" theme="simple" id="form-cadastrar-processo">
  <table cellpadding="5" cellspacing="0" class="tabela-dados">
    <tr>
      <td>Numero Processo</td>
      <td><input type="text" name="processo.numero" /></td>
    </tr>
    <tr>
      <td>Cliente</td>
      <td><s:select list="clientes" name="cliente.codigo" listKey="codigo" listValue="razaoSocial"></s:select></td>
    </tr>
    <tr>
      <td>Forum</td>
      <td><s:select list="foruns" name="forum.codigo" listKey="codigo" listValue="nome"></s:select></td>
    </tr>
    <tr>
      <td>Tipo de Causa</td>
      <td><s:select list="tipoCausas" name="tipoCausa.codigo" id="tipoCausa" listKey="codigo" listValue="descricao"></s:select></td>
    </tr>
    <tr>
      <td>Tipo de Cobranca</td>
      <td>
      	<select name="cobranca.codigo">
       	  <option value="1">Mensal</option>
       	  <option value="2">Trimestral</option>
       	  <option value="3">Semestral</option>
       	  <option value="4">Final de Processo</option>
      	</select>
     </td> 
    </tr>
    <tr>
      <td>Descri&ccedil;&atilde;o</td>
      <td><textarea  name="processo.descricao" cols="60" rows="4"></textarea></td>
    </tr>
    <tr>
      <td>Data Abertura</td>
      <td><input type="text" name="dataAbertura"/></td>
    </tr>
    <tr>
      <td>Dia de Vencimento do Pagamento</td>
      <td><input type="text" name="diaVencimento"/></td>
    </tr>
    <tr>
      <td>Observa&ccedil;&otilde;es</td>
      <td><textarea  name="processo.observacao" cols="60" rows="4"></textarea></td>
    </tr>
    <tr>
      <td colspan="2"><input id="submit" type="submit" /></td>
    </tr>
  </table>
</s:form>