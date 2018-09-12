<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page import="tools.WebProperties" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  		<script src='<%=WebProperties.propertiesMap.get("ueditConfigJs") %>' type="text/javascript"></script>
 		<script src='<%=WebProperties.propertiesMap.get("ueditJs") %>' type="text/javascript"></script>
 		<script src='<%=WebProperties.propertiesMap.get("ueditLang") %>' type="text/javascript"></script>
  </head>
  
  <body>
  	<jsp:include page="/head3.jsp"></jsp:include>
    	<form action="/news/servlet/NewsServlet?type1=editNews" id="myForm" method="post">
    		<table width="1000" align="center" border="1">
    			<tr>
    				<td>标题:</td>
    				<td>
    					<input type="text" name="caption" id="caption" value="${news.getCaption() }">
    				</td>
    			</tr>
    			<tr>
    				<td>类型:</td>
    				<td>
    					<select name="newsType" id="newsType">
    						<c:forEach items="${newsTypes}" var="newsType">
    							<c:choose>
    								<c:when test="${newsType.getName()==news.getNewsType()}">
    									<option value="${newsType.getName()}" selected>${newsType.getName() }</option>
    								</c:when>
    								<c:otherwise>
    									<option value="${newsType.getName()}">${newsType.getName() }</option>
    								</c:otherwise>
    							</c:choose>  							
    						</c:forEach>
    					</select>
    				</td>
    			</tr>
    			<tr>
    				<td>作者:</td>
    				<td>
    					<input type="text" name="author" id="author" value="${news.getAuthor()}">
    				</td>
    			</tr>
    			<tr>
    				<td>日期:</td>
    				<td>
    					<input type="datetime-local" name="newsTime" id="newsTime" value="${news.getNewsTime()}">
    				</td>
    			</tr>
    			<tr>
    				<td colspan="2">
    					<div>
    						<script id="content" type="text/plain" style="width:1000px;height:800px"></script>
    					</div>  
    				</td>
    			</tr>
    			<tr>
    				<td>
    					<input type="button" value="取消修改" onclick="cancel()">
    				</td>
    				<td>
    					<input type="submit" value="修改新闻">
    				</td>
    			</tr>
    		</table>
    		<input type="hidden" name="newsId" id="newsId" value="${news.getNewsId()}">
    	</form>	
    	<script type="text/javascript">
    		var ue=UE.getEditor('content');
    		
    		ue.ready(function(){
    			ue.setContent('${news.getContent()}');
    		});
    		
    		function cancel(){
    			document.getElementById("myForm").action="/news/servlet/NewsServlet?type1=manageNews&page=1&pageSize=2";
    			document.getElementById("myForm").submit();
    		}
    	</script>
  </body>
</html>
