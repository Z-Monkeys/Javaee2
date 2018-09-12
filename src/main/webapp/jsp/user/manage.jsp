<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    

  </head>
  
  <body style="background: url(/news/images/news.jpg) no-repeat;background-size: cover"> 
		<jsp:include page="/head.jsp"></jsp:include> 	
			<table width="300" height="300" border="1" align="center" bordercolor="white" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<p style="font-size: 30px; color: white;padding-left: 110px;">个人管理</p>
					</td>
				</tr>
				<tr>
					<td>
						<a href="/news/jsp/user/changePassword.jsp" style="text-decoration: none;color: white">修改密码</a>						
					</td>
				</tr>
				<tr>
					<td>
						<a href="/news/servlet/UserServlet?type1=showInformation" style="text-decoration: none;color: white">展示个人信息</a>
					</td>
				</tr>
				<tr>
					<td>
						<a href="/news/servlet/UserServlet?type1=changeInformation1" style="text-decoration: none;color: white">修改个人信息</a>
					</td>
				</tr>
				
			</table>
		
	</body>
</html>
