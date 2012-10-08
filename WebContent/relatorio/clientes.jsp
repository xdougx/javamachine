<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<ul id="lista-clientes">
  <s:iterator  value="clientes">
  <li><a href="#" data="${codigo}">${nome}</a></li>
  </s:iterator>
</ul>
<script type="text/javascript">
	$(document).ready(function(){
		$("#lista-clientes a").click(function(event){
			var id = $(this).attr("data");
			var cliente = $(this).text();
			$("[name='cliente-hidden']").val(id);
			$("[name='cliente']").val(cliente);
			event.stopPropagation();
			event.preventDefault();
			return false;
		});
	});
</script>