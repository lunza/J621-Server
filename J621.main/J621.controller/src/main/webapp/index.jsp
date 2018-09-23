<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ include file="../common.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	$(function() {
		$('#btn').click(function() {
			
			layer.msg('加载中', {
				  icon: 1
				  ,shade: 0.01
				});
			
		});
	})
	


</script>
<title>主页</title>
</head>
<body>
<button id="btn" >测试</button>
 <h1>你好，世界！</h1>
	
	<INPUT name="pclog" type="button" class="button button-glow button-rounded button-raised button-primary" value="注册" onClick="location.href='resign.jsp'">
	<br>
	<INPUT name="pclog" type="button" class="button button-glow button-rounded button-raised button-primary" value="登录" onClick="location.href='login.jsp'">
	<br>
</body>
</html>
