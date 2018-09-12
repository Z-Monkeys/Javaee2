<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
  </head>
  
  <body>
    	<jsp:include page="/head.jsp"></jsp:include> 
    	<table width="1000" border="1" align="center" cellspacing="0" cellpadding="0">
    		<tr>
    			<td>个人信息</td>
    			<td></td>
    		</tr>
    		<tr>
    			<td>用户名:</td>
    			<td>${user.getName()}</td>
    		</tr>
    		<tr>
    			<td>用户类型:</td>
    			<td>${user.getType()}</td>
    		</tr>
    		<tr>
    			<td>用户头像:</td>
    			<td>
    				<img src="${user.getHeadIconUrl()}" height="100"/>
    			</td>
    		</tr>
    		<tr>
    			<td>注册日期:</td>
    			<td>${user.getRegisterDate()}</td>
    		</tr>
    		<c:if test="${user.getType()=='user'}">
    			<tr>
    				<td>性别:</td>
    				<td>${userInformation.getSex()}</td>
    			</tr>
    			<tr>
    				<td>爱好:</td>
    				<td>${userInformation.getHobby()}</td>
    			</tr>
    		</c:if>
    	</table>
  </body>
</html>
