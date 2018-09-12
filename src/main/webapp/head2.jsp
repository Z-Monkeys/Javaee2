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
			<li><a href="/news/jsp/manager/manage.jsp">管理员管理</a></li>
		<c:choose>
			<c:when test="${!empty user }">
				<li><a href="/news/servlet/UserServlet?type1=logout">注销</a></li>
			</c:when>
		</c:choose>
		</ul>

	</div>
</body>
</html>
