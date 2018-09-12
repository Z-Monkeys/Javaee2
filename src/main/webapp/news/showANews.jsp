<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   

  </head>
  
  <body>
   		<jsp:include page="/head.jsp"></jsp:include> 
   		<table width="1000" align="center" border="1">
   			<tr>
   				<td align="center">标题:${news.getCaption()}</td>
   			</tr>	
   			<tr>
   				<td align="center">作者:${news.getAuthor()}&nbsp;&nbsp;&nbsp;&nbsp;时间:${news.getNewsTime()}</td>
   			</tr>
   			<tr>
   				<td>${news.getContent()}</td>				
   			</tr>
   		</table>
  
   	<jsp:include page='<%="/servlet/CommentServlet?type1=showComment"%>' flush="true">
   		<jsp:param name="newsId" value="${news.newsId}"/>
   		<jsp:param name="page" value="${param.page}"/>
   		<jsp:param name="pageSize" value="${param.pageSize}"/>
   		<jsp:param name="totalPageCount" value="${param.totalPageCount}"/>
   	</jsp:include>
   	<jsp:include page="/comment/addComment.jsp" flush="true">
   		<jsp:param name="newsId" value="${news.newsId}"/>
   		<jsp:param name="page" value="1"/>
   		<jsp:param name="pageSize" value="${param.pageSize}"/>
   		<jsp:param name="totalPageCount" value="${param.totalPageCount}"/>
   	</jsp:include>
  </body>
</html>
