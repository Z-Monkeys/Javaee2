<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
 <body style="background: url(/news/images/news.jpg) no-repeat;background-size: cover">
 	<jsp:include page="/head2.jsp"></jsp:include>
			<table width="300" height="300" border="1" align="center" bordercolor="white" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<p style="font-size: 30px; color: white;padding-left: 110px;">管理员管理</p>
					</td>
				</tr>
				<tr>
					<td>
						<a href="/news/servlet/UserServlet?type1=skinUser&page=1&pageSize=2" style="text-decoration: none;color: white">浏览用户</a>
					</td>
				</tr>
				<tr>
					<td>
						<a href="/news/jsp/manager/searchUser.jsp" style="text-decoration: none;color: white">查询用户</a>
					</td>
				</tr>
				<tr>
					<td>
						<a href="/news/servlet/UserServlet?type1=checkUser&page=1&pageSize=2" style="text-decoration: none;color: white">审查用户</a>
					</td>
				</tr>
				<tr>
					<td>
						<a href="/news/servlet/UserServlet?type1=deleteUser&page=1&pageSize=2" style="text-decoration: none;color: white">删除用户</a>
					</td>
				</tr>
			</table>
	</body>
</html>
