<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<s:iterator value="advogados">
<li><input type="checkbox" value="${codigo}" /> ${nome}</li>
</s:iterator>