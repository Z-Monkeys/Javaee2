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
    	<form action="/news/servlet/NewsServlet?type1=addNews" method="post">
    		<table width="1000" align="center" border="1">
    			<tr>
    				<td>标题:</td>
    				<td>
    					<input type="text" name="caption" id="caption">
    				</td>
    			</tr>
    			<tr>
    				<td>类型:</td>
    				<td>
    					<select name="newsType" id="newsType">
    						<c:forEach items="${newsTypes}" var="newsType">
    							<option value="${newsType.getName()}">${newsType.getName() }</option>
    						</c:forEach>
    					</select>
    				</td>
    			</tr>
    			<tr>
    				<td>作者:</td>
    				<td>
    					<input type="text" name="author" id="author">
    				</td>
    			</tr>
    			<tr>
    				<td>日期:</td>
    				<td>
    					<input type="datetime-local" name="newsTime" id="newsTime">
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
    				<td></td>
    				<td>
    					<input type="submit" value="添加新闻">
    				</td>
    			</tr>
    		</table>
    	</form>	
    	<script type="text/javascript">
    		var ue=UE.getEditor('content');
    	</script>
  </body>
</html>
