<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>

<html>
	<head>
		<meta charset="utf-8">
	</head>
	<body style="background: url(/news/images/news.jpg) no-repeat;background-size: cover">
		<jsp:include page="/head.jsp"></jsp:include> 
		<form action="/news/servlet/UserServlet?type1=login" method="post">
			<table width="300" height="300" border="1" align="center" bordercolor="white" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<p style="font-size: 30px; color: white;padding-left: 110px;">登陆</p>
					</td>
				</tr>
				<tr>
					<td style="color: white">
						账号:<input type="text" name="name" id="name"/>
					</td>
				</tr>
				<tr>
					<td style="color: white">
						密码:<input type="password" name="password" id="password"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="登陆">
					</td>
				</tr>
				<tr>
					<td style="color: white;font-weight: bold">
						没有账号？<a href="/news/jsp/register.jsp" style="text-decoration: none;color: red">去注册</a>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
