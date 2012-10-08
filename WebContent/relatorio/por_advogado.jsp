<%@page import="com.javamachine.persistencia.dao.RelatorioProcessoDAO"%>
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
      <td>Data Participacao</td>
      <td>Resultado</td>
    </tr>
  <s:iterator value="advogadoProcessos">
    <tr>
      <td>${processo.numero}</td>
      <td>${processo.descricao}</td>
      <td>${processo.dataAbertura}</td>
      <td>${processo.dataFechamento}</td>
      <td>${causa.descricao}</td>
      <td>${dataParticipacao}</td>
      <td>${processo.resultado}</td>
    </tr>
  </s:iterator>
  </table>
  <script>
    $(".tabela-dados tr:odd").css("background-color", "#f0f0f0");
  </script>