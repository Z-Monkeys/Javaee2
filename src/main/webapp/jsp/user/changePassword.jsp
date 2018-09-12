<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <script>
    	function valPassword(input,span){
			var inputValue = document.getElementById(input).value;
			var spanObj=document.getElementById(span);
			var pattern=/^(\w){6,20}$/;
			
			if(inputValue==null || inputValue==""){
				spanObj.innerHTML="*不能为空";
				return false;
			}else if(inputValue.match(pattern)==null){
				spanObj.innerHTML="*至少输入6-20个字母、数字、下划线";
				return false;
			}else{
				spanObj.innerHTML="*ok";
				return true;
			}
	 	}
    	
    	function passwordDifferent(input1,input2,span){
    		var input1Value = document.getElementById(input1).value;
    		var input2Value = document.getElementById(input2).value;
    		var spanObj=document.getElementById(span);
    		if(input2Value==null||input2Value==""){
    			spanObj.innerHTML="*不能为空";
    			return false;
    		}else if(input1Value==input2Value){
    			spanObj.innerHTML="*新旧密码不能相同";
    			return false;
    		}else{
    			spanObj.innerHTML="*ok";
    			return true;
    		}
    	}
    	
    	function passwordSame(input1,input2,span){
    		var input1Value = document.getElementById(input1).value;
    		var input2Value = document.getElementById(input2).value;
    		var spanObj=document.getElementById(span);
    		if(input2Value==null||input2Value==""){
    			spanObj.innerHTML="*不能为空";
    			return false;
    		}else if(input1Value==input2Value){
    			spanObj.innerHTML="*ok";
    			return true;
    		}else{
    			spanObj.innerHTML="*新密码不一致，请确认新密码";
    			return false;
    		}
    	}
    	
    	function submit1(){
    		result=valPassword("oldPassword","oldPasswordSpan");
    		result=valPassword("newPassword1","newPasswordSpan1")&&result;
    		result=passwordDifferent("oldPassword","newPassword1","newPasswordSpan1")&&result;
    		result=passwordSame("newPassword1","newPassword2","newPasswordSpan2")&&result;
    		if(result==true){
    			return true;
    		}else{
    			return false;
    		}
    	}
    	
    </script>

  </head>
  
  <body>
    	<jsp:include page="/head.jsp"></jsp:include> 
    	<form action="/news/servlet/UserServlet?type1=changePassword" method="post" id="myForm" onsubmit="return submit1()">
    		<table>
    			<tr>
    				<td>旧密码</td>
    				<td>
    					<input type="password" name="oldPassword" id="oldPassword" onBlur="valPassword('oldPassword','oldPasswordSpan');">
    					<span id="oldPasswordSpan"></span>
    				</td>
    			</tr>
    			<tr>
    				<td>新密码</td>
    				<td>
    					<input type="password" name="newPassword1" id="newPassword1" onBlur="valPassword('newPassword1','newPasswordSpan1');">
    					<span id="newPasswordSpan1"></span>
    				</td>
    			</tr>
    			<tr>
    				<td>确认新密码</td>
    				<td>
    					<input type="password" name="newPassword2" id="newPassword2" onBlur="passwordSame('newPassword1','newPassword2','newPasswordSpan2');">
    					<span id="newPasswordSpan2"></span>
    				</td>
    			</tr>
    			<tr>
    				<td>
    					<input type="submit" value="确认修改">
    				</td>
    				<td></td>
    			</tr>
    		</table>
    	</form>
  </body>
</html>
