<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <link href="index.css" rel="stylesheet" type="text/css">
    <title>新闻发布系统主页</title> 
	 </head>
	 <body class="body" style="width:800px; text-align:center;">
	 <div style="width:900px; margin:0 auto; text-align: center; font-weight: bold; color: red; font-size:50px;">
	   <form name="form1" action ="logout.jsp" method="post">
<input style=" float:right;" type="submit" name="submit" value="退出">
<div style="color:red; font-size:20px; float:right;">
<%=(String)session.getAttribute("username")%>
  </div>

</form>
<br>
 <br>


<div style="text-align:center; ">
<img  src="images/logo2.png"height="50">
<br>
<img style="margin:0 auto; height:50px; " src="images/logo4.png">
</div>

 <a style="color:red; font-size:20px;" href="index.jsp">计算机党总支主页</a>
<br>

<a style="color:red; font-size:20px;" href="addStuInfo.jsp">发布文章</a>
<br>
<a style="color:red; font-size:20px;" href="delete.jsp">删除文章</a>
<br>
<a style="color:red; font-size:20px;" href="showInfo.jsp">查看文章</a>
<br>
<a style="color:red; font-size:20px;" href="update.jsp">修改文章</a>
<br>


</div> 

    
    
    
  </body>
</html>


