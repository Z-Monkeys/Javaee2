<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<script type="text/javascript">
  	
  	function getOnePage(type){
  		var page=document.getElementById("page");
  		var totalPageCount=document.getElementById("totalPageCount");
  		var pageValue=parseInt(page.value);
  		if(type=="first"){
  			page.value="1";
  		}else if(type=="pre"){
  			pageValue-=1;
  			page.value=pageValue.toString();
  		}else if(type=="next"){
  			pageValue+=1;
  			page.value=pageValue.toString();
  		}else if(type=="last"){
  			page.value=totalPageCount.value;
  		}
  		document.getElementById("myForm").submit();
  	}
  	function order(orderFieldName){
  		var order=document.getElementById("order");
  		var page=document.getElementById("page");
  		var orderField=document.getElementById("orderField");
  		if(order.value=="asc"){
  			order.value="desc";
  		}else{
  			order.value="asc";
  		}
  		if(orderFieldName!=""){
  			orderField.value=orderFieldName;
  		}
  		page.value="1";
  		document.getElementById("myForm").submit();
  	}
  	</script>
  </head>
  
  <body>
    	<jsp:include page="/head.jsp"></jsp:include> 
    	<form action="/news/servlet/NewsServlet?type1=showNews" id="myForm" method="post">
    		<table align="center" border="1">
    			<tr>
    				<td>
    					<a href="javascript:void(0);" onclick="order('newsId');">新闻编号</a>
    				</td>
    				<td>标题</td>
    				<td>作者</td>
    				<td>日期</td>
    			</tr>
    			<c:forEach items="${newses}" var="news">
    				<tr>
    					<td><c:out value="${news.getNewsId()}"></c:out></td>
    					<td>
    						<a href="/news/servlet/NewsServlet?type1=showANews&newsId=${news.getNewsId()}&page=1&pageSize=2">
    							<c:out value="${news.getCaption()}"></c:out>
    						</a>
    					</td>
    					<td><c:out value="${news.getAuthor()}"></c:out></td>
    					<td><c:out value="${news.getNewsTime()}"></c:out></td>
    				</tr>
    			</c:forEach>
    		</table>
    		<table align="center" border="1">
    			<tr>
    				<c:if test="${pageInformation.page>1}">
    					<td>
    						<a href="javascript:void(0);"onclick="getOnePage('first');">首页</a>
    					</td>
    				</c:if>
    				<c:if test="${pageInformation.page>1}">
    					<td>
    						<a href="javascript:void(0);"onclick="getOnePage('pre');">上一页</a>
    					</td>
    				</c:if>
    				<c:if test="${pageInformation.page<pageInformation.totalPageCount}">
    					<td>
    						<a href="javascript:void(0);"onclick="getOnePage('next');">下一页</a>
    					</td>
    				</c:if>
    				<c:if test="${pageInformation.page<pageInformation.totalPageCount}">
    					<td>
    						<a href="javascript:void(0);"onclick="getOnePage('last');">尾页</a>
    					</td>
    				</c:if>
    				<td>共${pageInformation.totalPageCount}页</td>
    			</tr>
    		</table>
    		<input type="hidden" name="page" id="page" value="${pageInformation.page}">
    		<input type="hidden" name="pageSize" id="pageSize" value="${pageInformation.pageSize}">
    		<input type="hidden" name="totalPageCount" id="totalPageCount" value="${pageInformation.totalPageCount}">
    		<input type="hidden" name="allRecordCount" id="allRecordCount" value="${pageInformation.allRecordCount}">
    		<input type="hidden" name="orderField" id="orderField" value="${pageInformation.orderField}">
    		<input type="hidden" name="order" id="order" value="${pageInformation.order}">
    		<input type="hidden" name="ids" id="ids" value="${pageInformation.ids}">
    		<input type="hidden" name="searchSql" id="searchSql" value="${pageInformation.searchSql}">
    		<input type="hidden" name="result" id="result" value="${pageInformation.result}">
    	</form>
  </body>
</html>
