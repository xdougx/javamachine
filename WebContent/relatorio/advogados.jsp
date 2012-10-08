<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<ul id="lista-advogados">
  <s:iterator  value="advogados">
  <li><a href="#" data="${codigo}">${nome}</a></li>
  </s:iterator>
</ul>
<script type="text/javascript">
	$(document).ready(function(){
		$("#lista-advogados a").click(function(event){
			var id = $(this).attr("data");
			var advogado = $(this).text();
			$("[name='advogado-hidden']").val(id);
			$("[name='advogado']").val(advogado);
			event.stopPropagation();
			event.preventDefault();
			return false;
		});
	});
</script>