<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<link href="/news/css/2.css" rel="stylesheet" type="text/css">
</head>

<body>
	<div id="nav">

		<ul>

			<li><a href="/news/index.jsp">首页</a></li>

			<li><a href="/news/servlet/NewsServlet?type1=showNews&page=1&pageSize=2">新闻资讯</a></li>

		<c:choose>
			<c:when test="${empty user}">
				<li><a href="/news/jsp/login.jsp">登陆</a></li>
			</c:when>
			<c:when test="${!empty user }">
				<li><a href="/news/jsp/user/manage.jsp">个人管理</a></li>
				<li><a href="/news/servlet/UserServlet?type1=logout">注销</a></li>
			</c:when>
		</c:choose>
			<li><a href="/news/jsp/register.jsp">注册</a></li>

		</ul>

	</div>
</body>
</html>
