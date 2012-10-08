<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="assets/css/bootstrap.css" type="text/css"/>
<link rel="stylesheet" href="assets/css/font-awesome.css" type="text/css"/>
<link rel="stylesheet" href="assets/css/buttons.css" type="text/css"/>
<link rel="stylesheet" href="assets/css/base.css" type="text/css"/>
<link href='http://fonts.googleapis.com/css?family=Ubuntu+Mono:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
<script type="text/javascript" src="assets/js/jquery.min.js"></script>
<script type="text/javascript" src="assets/js/jquery.form.js"></script>
<script type="text/javascript" src="assets/js/jquery.masked.min.js"></script>
<script type="text/javascript">
  $(document).ready(function(){
  	$("#menu a").click(function(){
  		if($(this).attr("href") == "index"){
  			window.location.reload(true);
  			return true;
  		}else {
	  		var link = $(this).attr("href");
	  		$("#content").load(link);
	  		return false;
  		}
  	});
  	
  });
  
</script>

<title>Gerenciamento de Processos - javamachine</title> 
</head>
<body>
<div id="corpo">
  <div id="top">
    <h1>Gerenciamento de Processos</h1>
  	<div id="logo"><img src="assets/img/logo.png" width="50"></div>
  </div>
  <div class="content">
	  <div id="menu">
	    <ul>
	      <li class="button white"><a href="index"><i class="icon-home"></i>Home</a></li>
	      <li class="button white"><a href="gerenciar_processos"> <i class="icon-legal"></i> Processos</a></li>
	      <li class="button white"><a href="buscar_processo_despesa"> <i class="icon-pushpin"></i> Despesas</a></li>
	      <li class="button white"><a href="controlar_pagamentos"> <i class="icon-money"></i>Pagamentos</a></li>
	      <li class="button white"><a href="relatorios"> <i class="icon-bar-chart"></i> Relat&oacute;rios</a></li>
	    </ul>
	  </div>
	  <div id="content"></div>
  </div>
</div>
</body>
</html>