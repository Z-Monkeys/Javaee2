<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    

  </head>
  
  <body style="background: url(/news/images/news.jpg) no-repeat;background-size: cover">
  	<jsp:include page="/head3.jsp"></jsp:include>
			<table width="300" height="300" border="1" align="center" bordercolor="white" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<p style="font-size: 30px; color: white;padding-left: 110px;">新闻管理</p>
					</td>
				</tr>
				<tr>
					<td>
						<a href="/news/news/manage/addNews.jsp" style="text-decoration: none;color: white">添加新闻</a>						
					</td>
				</tr>
				
				<tr>
					<td>
						<a href="/news/servlet/NewsServlet?type1=manageNews&page=1&pageSize=2" style="text-decoration: none;color: white">管理新闻</a>
					</td>
				</tr>
				
			</table>
		
	</body>
</html>
