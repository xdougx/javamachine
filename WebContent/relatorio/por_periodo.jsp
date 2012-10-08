<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
  <h3>Relatorio por Cliente</h3> 
  <table cellpadding="5" cellspacing="0" class="tabela-dados">
    <tr>
      <td>#</td>
      <td>Descricao</td>
      <td>Data Abertura</td>
      <td>Cliente</td>
      <td>Causa</td>
      <td>Advogados</td>
    </tr>
  <s:iterator  value="processos">
    <tr>
      <td>${numero}</td>
      <td>${descricao}</td>
      <td>${dataAbertura}</td>
      <td>${cliente.razaoSocial}</td>
      <td>${causa.descricao}</td>
      <td> - </td>
    </tr>
  </s:iterator>
  </table>
  <script>
    $(".tabela-dados tr:odd").css("background-color", "#f0f0f0");
  </script>