<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<tr class="title">
  <td>Nome</td>
  <td width="250">OAB</td>
  <td>E-mail</td>
  <td>Inicio Participacao</td>
</tr>
<s:iterator value="advogadosProcesso">
<tr>
  <td data="${advogado.codigo}">${advogado.nome}</td>
  <td>${advogado.oab}</td>
  <td>${advogado.email}</td>
  <td>${dataParticipacao}</td>
</tr>
</s:iterator>