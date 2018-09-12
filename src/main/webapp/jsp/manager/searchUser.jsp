<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body>
  <jsp:include page="/head2.jsp"></jsp:include>
    <form action="/news/servlet/UserServlet?type1=searchUser&page=1&pageSize=2" method="post">
    	<table align="center">
    		<tr>
    			<td></td>
				<td>
					查询条件
				</td>
			</tr>
    		<tr>
    			<td>用户类型:</td>
    			<td>
					用户类型:<select name="type">
							<option value="all">全部</option>
							<option value="user">普通用户</option>
							<option value="manager">管理员</option>
							<option value="newsAuthor">新闻发布员</option>
						</select>
				</td>
    		</tr>
    		<tr>
    			<td>用户名:</td>
    			<td>
				<input type="text" name="name"/>
				</td>
    		</tr>
    		<tr>
    			<td>账户可用性:</td>
    			<td>
    			<select name="enable">
    				<option value="all">全部</option>
    				<option value="use">使用</option>
    				<option value="stop">停用</option>
    			</select>
    			</td>
    		</tr>
    		<tr>
    			<td>注册日期</td>
    			<td>
    				介于<input type="date" name="lowDate">与<input type="date" name="upDate">之间
    			</td>  			
    		</tr>
    		<tr>
    			<td>
    				<input type="submit" value="submit">
    			</td>
    		</tr>
    	</table>
    </form>
  </body>
</html>
