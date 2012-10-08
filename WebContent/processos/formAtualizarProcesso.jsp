<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<script type="text/javascript">
  $(document).ready(function(){
  	$(".tabela-dados tr:odd").css("background-color", "#f0f0f0");
  	$(".title").css("background-color","#134197");
  	$("#submit").click(function(e){
  	  	var options = {
  	    	target: "#content",
  	    	url: "gravar_atualizar_processo",
  	    	success: function(){}
  	    };
  		$("#form-cadastrar-processo").ajaxForm(options);
  	});
  	
  	$("#submit-adv").click(function(e){
  	  	var options = {
  	    	url: "gravar_advogado_processo",
  	    	success: function(data){
  	    		$("#advogados-participantes").html(data);
  	    	}
  	    };
  		$("#form-cadastrar-advogado").ajaxForm(options);
  	});
  	
  	$(".remover").click(function(e){
  		e.preventDefault();
  		var nr_adv = $(this).parent().parent().find("td:first-child").attr("data");
  		var nr_proc = $("[name='processo.numero']").val();
  		var data = {
  			"advogado.codigo": nr_adv,
  			"processo.numero": nr_proc
  		};
  		
  		$(this).load("remover_advogado_processo", data, function(data){
  			$(this).parent().parent().fadeOut("200", function(){
  				$(this).remove();
  			});
  		});

  	});
  	
  	
  	$("#tipoCausa").change(function(){
  		$("#listaAdvogados").load("listar_advogados", {codigo: $("#tipoCausa option:selected").val()});
  	});
  	
  	$("#add").click(function(){
  		$("#add-adv").fadeIn("fast");
  	});
  	var data = $("[name='dataAbertura']").val();
  	data = data.split("-");
  	$("[name='dataAbertura']").val(data[2] + "/" + data[1] + "/" + data[0]);
  });
</script>
<h3 data-processo="${processo.cliente.codigo}">Atualizar Processo</h3>
<s:form action="gravar_atualizar_processo" method="post" theme="simple" id="form-cadastrar-processo">
  <table cellpadding="5" cellspacing="0" class="tabela-dados">
    <tr>
      <td>Cliente</td>
      <td><input type="hidden" value="${processo.cliente.codigo}" />${processo.cliente.razaoSocial}</td>
    </tr>
    <tr>
      <td>Forum</td>
      <td><input type="hidden" name="forum.codigo" value="${processo.forum.codigo}"/>${processo.forum.descricaoForum}</td>
    </tr>
    <tr>
      <td>Tipo de Causa</td>
      <td><input type="hidden" name="pricesso.tipoCausa.codigo"/>${processo.causa.descricao}</td>
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
      <td><textarea  name="processo.descricao" cols="60" rows="4">${processo.descricao}</textarea></td>
    </tr>
    <tr>
      <td>Data Abertura</td>
      <td><input type="hidden" name="dataAbertura" value="${processo.dataAbertura}"/>${processo.dataAbertura}</td>
    </tr>
    <tr>
      <td>Dia de Vencimento do Pagamento</td>
      <td><input type="hidden" name="diaVencimento" value="${processo.diaVencimento}"/>${processo.diaVencimento}</td>
    </tr>
    <tr>
      <td>Observa&ccedil;&otilde;es</td>
      <td><textarea  name="processo.observacao" cols="60" rows="4">${processo.observacao}</textarea></td>
    </tr>
    <tr>
      <td colspan="2">
      	<input id="submit" type="submit" />
      	<input type="hidden" name="processo.numero" value="${processo.numero}" />
      </td>
    </tr>
  </table>
</s:form>
  <div id="add-adv" style="display:none">
    <h3>Adicionar Novo Advogado</h3>
    <s:form action="gravar_advogado_processo" method="post" theme="simple" id="form-cadastrar-advogado">
	    <table cellpadding="5" cellspacing="0" class="tabela-dados">
		  <tr>
		    <td>Advogado</td>
			<td><s:select theme="simple" name="advogado.codigo" list="advogados" listKey="codigo" listValue="nome"></s:select></td>
		  </tr>
		  <tr>
		    <td>Data de Participação</td>
			<td><input type="text" name="advogadoProcesso.dataParticipacao"/></td>
		  </tr>
		  <tr>
		    <td colspan="2">
	      	  <input id="submit-adv" type="submit" value="Adcionar Advogado" />
	      	  <input type="hidden" name="processo.numero" value="${processo.numero}" />
	        </td>
	        </tr>
		</table>
	</s:form>
</div>
  <h3>Advogados Particiapantes</h3>
    <p align="right"><a href="#" id="add">+ Adicionar Advogado</a></p>
	<table cellpadding="5" cellspacing="0" class="tabela-dados" id="advogados-participantes">
	  <tr class="title">
	    <td>Nome</td>
	    <td width="250">OAB</td>
	    <td>E-mail</td>
	    <td>Inicio Participacao</td>
	    <td> - </td>
	  </tr>
	  <s:iterator value="advogadosProcesso">
	  <tr>
	    <td data="${advogado.codigo}">${advogado.nome}</td>
	    <td>${advogado.oab}</td>
	    <td>${advogado.email}</td>
	    <td>${dataParticipacao}</td>
	    <td> <a href="#" class="remover">remover</a> </td>
	  </tr>
	  </s:iterator>
	</table>