<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<tr>
  <td>#</td>
  <td>Tipo de Despesa</td>
  <td>Data Lançamento</td>
  <td>Valor</td>
  <td>Descrição</td>
</tr>
<s:iterator value="despesas">
<tr>
  <td>${codigo}</td>
  <td>${tipoDespesa.descricao}</td>
  <td>${data}</td>
  <td>${valor}</td>
  <td>${descricao}</td>
</tr>
</s:iterator>