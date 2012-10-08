<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<tr>
  <td>${codigo}</td>
  <td>${despesa.tipoDespesa.descricao}</td>
  <td>${despesa.data}</td>
  <td>${despesa.valor}</td>
  <td>${despesa.descricao}</td>
</tr>