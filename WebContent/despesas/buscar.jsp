<script type="text/javascript">
  $(document).ready(function(){
  	$("input[type='button']").click(function(){
  		var busca = $("#busca").val();
  		$("#resultado").load("processos_por_cliente", {busca:busca});
  	});
  });
</script>
 <h3>Lan&ccedil;ar Despesas por Processo</h3>
 <div>
   <input type="text" size="40" name="busca" id="busca" placeholder="insira o código do processo"/><input type="button" value="Buscar">
 </div>
 <div id="resultado"></div>