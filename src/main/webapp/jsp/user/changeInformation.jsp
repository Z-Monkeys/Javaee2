<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head></head>
  
  <body>
    	<jsp:include page="/head.jsp"></jsp:include> 
    	<form action="/news/servlet/UserServlet?type1=changeInformation2" method="post" enctype="multipart/form-data">
    		<table width="1000" border="1" align="center" cellspacing="0" cellpadding="0">
    		<tr>
    			<td>修改个人信息</td>
    			<td></td>
    		</tr>
    		
    		<tr>
    			<td>用户头像:</td>
    			<td>
    				<img src="${user.getHeadIconUrl()}" height="100"/>
    				<input type="file" id="myFile" name="myFile" onchange="preview();"><br>
    				预览：<img id="newImage" name="newImage" height="100">
    			</td>
    		</tr>
    		<c:if test="${user.getType()=='user'}">
    			<tr>
    				<td>性别:</td>
    				<td>
    					<select name="sex" id="sex">
    						<option value="男">男</option>
    						<option value="女">女</option>
    					</select>
    				</td>
    			</tr>
    			<tr>
    				<td>爱好:</td>
    				<td>
    					<input type="text" name="hobby" value="${userInformation.getHobby()}"/>
    				</td>
    			</tr>
    		</c:if>
    		<tr>
    			<td>
    				<input type="submit" value="提交"/>
    			</td>
    			<td></td>
    		</tr>
    	</table>
    	</form>	
  </body>
</html>
<script language=javascript>
	function preview(){
		var file=document.getElementById("myFile").files[0];
		var preview=document.getElementById("newImage");
		var reader=new FileReader();
		reader.onloadend=function(){
			preview.src=reader.result;
		}
		if(file){
			reader.readAsDataURL(file);
		}else{
			preview.src="";
		}
	}
	
	var sex=document.getElementById("sex");
	for(var i=0;i<sex.options.length;i++){
		if(sex.options[i].value=="${userInformation.sex}"){
			sex.options[i].selected=true;
			break;
		}
	}
</script>
 