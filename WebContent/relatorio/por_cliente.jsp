<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
  <h3>Relatorio por Cliente</h3> 
  <table cellpadding="5" cellspacing="0" class="tabela-dados">
    <tr>
      <td>#</td>
      <td>Descricao</td>
      <td>Data Abertura</td>
      <td>Data Fechamento</td>
      <td>Causa</td>
      <td>Cobranca</td>
      <td>Dia Vencimento</td>
    </tr>
  <s:iterator  value="processos">
    <tr>
      <td>${numero}</td>
      <td>${descricao}</td>
      <td>${dataAbertura}</td>
      <td>${dataFechamento}</td>
      <td>${causa.descricao}</td>
      <td>${cobranca.descricao}</td>
      <td>${diaVencimento}</td>
    </tr>
  </s:iterator>
  </table>
  <script>
    $(".tabela-dados tr:odd").css("background-color", "#f0f0f0");
  </script>