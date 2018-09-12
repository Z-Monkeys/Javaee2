<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <link href="/news/css/1.css" rel="stylesheet" type="text/css">
	<script>
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
  	
  	function praise(commentId,newsId){
  		document.getElementById("myForm").action="/news/servlet/CommentServlet?type1=praise&newsId="+newsId+"&commentId="+commentId;
  		document.getElementById("myForm").submit();
  	}
  	
  	function replay(commentId){
  		document.getElementById("commentId").value=commentId;
  		document.getElementById("replay").style.display="block";
  	}
  	
  	function cancel(){
  		document.getElementById("replay").style.display="none";
  	}
	</script>
  </head>
  
  <body>
  	<div id="responses" style="display:block">
  		<form action="/news/servlet/NewsServlet?type1=showANews&newsId=${param.newsId}" id="myForm" method="post">
  			<table align="center" border="1">
  				<tr>
  					<td>评论</td>
  				</tr>
  				<c:forEach items="${comments}" var="comment">
  					<tr>
  						<td>${comment.getUserName()}在
  						<fmt:formatDate type="both" dateStyle="long" timeStyle="long" value="${comment.time}"/>回复
  						${comment.getContent()}<br>
  						点赞(<a href="javascript:void(0);" onclick="praise(${comment.getCommentId()},${param.newsId});">${comment.getPraise()}</a>)
  						&nbsp;&nbsp;
  						<a href="javascript:void(0);" onclick="replay(${comment.getCommentId()});">回复</a>
  						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  						${comment.getStair()}楼
  						</td>
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
  		</form>
  	</div>
  		<div class="replay" id="replay">
  			<form action="/news/servlet/CommentServlet?type1=addComment" method="post">
  				<table align="center">
  					<tr>
  						<td>
  							<textarea rows="10" cols="80" name="content"></textarea>
  						</td>
  					</tr>
  					<tr>
  						<td>
  							<input type="submit" name="submit" value="提交">&nbsp;&nbsp;
  							<input type="button" onclick="cancel();" value="取消">
  						</td>
  					</tr>
  				</table>
  				<input type="hidden" name="newsId" id="newsId" value="${param.newsId }">
  				<input type="hidden" name="page" id="page" value="${param.page }">
  				<input type="hidden" name="pageSize" id="pageSize" value="${param.pageSize }">
  				<input type="hidden" name="totalPageCount" id="totalPageCount" value="${param.totalPageCount }">
  				<input type="hidden" name="commentId" id="commentId" >
  			</form>
  		</div>
  </body>
</html>