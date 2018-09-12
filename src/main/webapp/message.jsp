<%@ page language="java" import="java.util.*,tools.Message" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
	Message message=(Message)request.getAttribute("message");
	response.setHeader("refresh", message.getRedirectTime()+";URL="+message.getRedirectUrl());
 %>

<!DOCTYPE html>
<html>
  <head>
    
  </head>
  
  <body>
    	<table width="1000" align="center">
    		<tr>
    			<td>${message.message}</td>
    		</tr>
    		<tr>
    			<c:if test="${message.getRedirectTime()<10}">
    				<td>三秒后将跳转页面</td>
    				<td>如果没有跳转，
    				<a href="${message.redirectUrl}">点击跳转</a>
    			</td>
    			</c:if>
    			<c:if test="${message.getRedirectTime()>=10}">
    					<a href="javascript:void(0);" onclick="history.go(-1);">返回上一页</a>
    			</c:if>
    		</tr>
    	</table>
  </body>
</html>
