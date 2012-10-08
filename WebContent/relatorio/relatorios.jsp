<script type="text/javascript">
  $(document).ready(function(){
  	$("#cliente").click(function(){
  		var cliente = $("[name='cliente-hidden']").val();
  		var data = {cliente:cliente};
  		$("#resultado").load("relatorio_por_cliente", data);
  	});
  	
  	$("[name='cliente']").keydown(function(){
  		if($("#clientList").html() == null){
  			var box = $("<div>").attr("id", "clientList");
  			$(this).parent().append(box);
  		}
  		
  		if(this.value.length > 0){
  			var data = {cliente:this.value}
  			
  			$.ajax({
				url: "listar_clientes",
				type: 'POST',
				data: data,
				success: function(data){
				  $("#clientList").html("<h3>Clientes</h3><div>"+data+"</div>");
				}
			});
  		}
  	});
  	
  	$("#periodo").click(function(){  			
  		var inicio = $("[name='de']").val();
  		var fim = $("[name='ate']").val();
  		var data = {inicio:inicio, fim:fim};
  		$("#resultado").load("relatorio_periodo", data);
  	});
  	
  	$("#periodo").click(function(){
  		var inicio = $("[name='de']").val();
  		var fim = $("[name='ate']").val();
  		var data = {inicio:inicio, fim:fim};
  		$("#resultado").load("relatorio_por_periodo", data);
  	});
  	
  	$("#advogado").click(function(){
  		var advogado = $("[name='advogado-hidden']").val();
  		var data = {advogado:advogado};
  		$("#resultado").load("relatorio_por_advogado", data);
  	});
  	
  	
  	$("[name='advogado']").keydown(function(){
  		if($("#advogadoList").html() == null){
  			var box = $("<div>").attr("id", "advogadoList");
  			$(this).parent().append(box);
  		}
  		
  		if(this.value.length > 0){
  			var data = {advogado:this.value};
  			
  			$.ajax({
				url: "listar_advogados_relatorio",
				type: 'POST',
				data: data,
				success: function(data){
				  $("#advogadoList").html("<h3>Advogados</h3><div>"+data+"</div>");
				}
			});
  		}
  	});

  	$(".tabela-dados tr:odd").css("background-color", "#f0f0f0");
  	
  	$("[name='de']").mask("99/99/2099");
  	$("[name='ate']").mask("99/99/2099");

  });
</script>
 <h3>Relatório de Processo</h3>
 <div>
   <table  class="tabela-dados">
     <tr>
       <td>Buscar por Cliente</td>
     </tr>
     <tr>
       <td>
         <input type="text" placeholder="Insira o nome ou o codigo do cliente" size="50" name="cliente">
         <input type="button" id="cliente" value="Gerar Relat&oacute;rio"/>
         <input type="hidden" name="cliente-hidden">
       </td>
     </tr>
     <tr>
       <td>Bucar por Per&iacute;odo</td>

     </tr>
     <tr>
       <td>
         de: <input type="text"name="de" /> 
     	 ate: <input type="text" name="ate" />
     	 <input type="button" id="periodo" value="Gerar Relat&oacute;rio"/>
       </td>       
     </tr>
     <tr>
       <td>Buscar por Advogado</td>
     </tr>
     <tr>
       <td colspan="2">
         <input type="text" placeholder="Insira o nome ou o codigo do advogado" size="50" name="advogado">
         <input type="button" value="Gerar Relat&oacute;rio" id="advogado" />
         <input type="hidden" name="advogado-hidden">
       </td>
     </tr>
   </table>
 </div>
 <div id="resultado"></div>