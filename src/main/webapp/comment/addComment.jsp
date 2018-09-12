<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
  </head>
  
  <body>
    <form action="/news/servlet/CommentServlet?type1=addComment" method="post">
    	<table align="center" width="1000">
    		<tr>
  				<td>
  					<textarea rows="10" cols="155" name="content"></textarea>
  				</td>
  			</tr>
  			<tr>
  				<td>
  					<input type="submit" name="submit" value="提交">				
  				</td>
  			</tr>
    	</table>
    	<input type="hidden" name="newsId" id="newsId" value="${param.newsId }">
  		<input type="hidden" name="page" id="page" value="${param.page }">
  		<input type="hidden" name="pageSize" id="pageSize" value="${param.pageSize }">
  		<input type="hidden" name="totalPageCount" id="totalPageCount" value="${param.totalPageCount }">
    </form>
  </body>
</html>
