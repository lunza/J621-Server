<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>注册</title>
</head>
<body>
	<form  method="get" action="<%=request.getContextPath()%>/J621/resign">

		用户名：<input type="text" name="username" value="admin" /><br> 
		密码：<input type="text" name="password" value="123" /> <br> 
		确认密码：<input type="text" name="password2" value="123" /> <br> 
		邮箱：<input type="text" name="email" value="a1299021@163.com" /> <br>
		性别：<input type="text" name="sex" value="男" /> <br> 
		
		<input type="submit" value="注册"><br>
	</form>
	
	
</body>
</html>
