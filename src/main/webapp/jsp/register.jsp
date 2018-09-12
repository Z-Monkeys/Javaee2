<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<script type="text/javascript">
	function valName(){
		var str1=document.getElementById("name").value;//获取文本框的内容
		
		if(document.getElementById("name").value==null || document.getElementById("name").value==""){
			document.getElementById("namespan").innerHTML="*不能为空";
			return false;
		}else {
			document.getElementById("namespan").innerHTML="ok";
			return true;
		}
	}
	
	function valPassword(){
		var str = document.getElementById("password").value;
		var pattern=/^(\w){6,20}$/;
		
		if(document.getElementById("password").value==null || document.getElementById("password").value==""){
			document.getElementById("passwordspan").innerHTML="*不能为空";
			return false;
		}else if(str.match(pattern)==null){
			document.getElementById("passwordspan").innerHTML="*至少输入6-20个字母、数字、下划线";
			return false;
		}else{
			document.getElementById("passwordspan").innerHTML="ok";
			return true;
		}
	}
	
	function passwordSame(){
		var str = document.getElementById("password").value;
		if(document.getElementById("password2").value==null || document.getElementById("password2").value==""){
			document.getElementById("password2span").innerHTML="*不能为空";
			return false;
		}else if(document.getElementById("password").value==document.getElementById("password2").value){			
			document.getElementById("password2span").innerHTML="ok";
			return true ;
		}else{
			document.getElementById("password2span").innerHTML="*两次密码不一样";
			return false;
		}
				
	}
		
	function submit1(){
		result1=valName();
		result1=valPassword() && result1;
		result1=passwordSame() && result1;
		if( result1)
			return true;//提交
		else 
			return false;//拒绝提交
	}	
</script>
	</head>
	<body style="background: url(/news/images/news.jpg) no-repeat;background-size: cover">
		<jsp:include page="/head.jsp"></jsp:include> 
		<form action="/news/servlet/UserServlet?type1=register" method="post" onsubmit="return submit1()">
			<table width="400" height="300" border="1" align="center" bordercolor="white" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<p style="font-size: 30px; color: white;padding-left: 160px;">注册</p>
					</td>
				</tr>
				<tr>
					<td style="color: white">
					用户类型:<select name="type">
							<option value="user">普通用户</option>
							<option value="manager">管理员</option>
							<option value="newsAuthor">新闻发布员</option>
						</select>
					</td>
				</tr>
				<tr>
					<td style="color: white">
						账号:<input type="text" name="name" id="name"/>
						<span id="namespan"></span>
					</td>
				</tr>
				<tr>
					<td style="color: white">
						密码:<input type="password" name="password" id="password"/>
						<span id="passwordspan"></span>
					</td>
				</tr>
				<tr>
					<td style="color: white">
						密码:<input type="password" name="password2" id="password2" placeholder="再次输入密码"/>
						<span id="password2span"></span>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="注册"/>
					</td>
				</tr>
				<tr>
					<td style="color: white;font-weight: bold">
						已有账号？<a href="/news/jsp/login.jsp" style="text-decoration: none;color: red">去登陆</a>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
<script> 
  var result ='<%=request.getParameter("result")%>';
  if(result=='error'){
  	alert("用户名已存在!");
  }else if(result=='error2'){
  	alert("注册失败!")
  }
</script>












