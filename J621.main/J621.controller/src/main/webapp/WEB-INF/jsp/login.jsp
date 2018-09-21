<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>登录</title>
 <base href="<%=basePath%>">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>

<script type="text/javascript">
	$(function() {
		$('#submitBtn').click(function() {
			$.ajax({
				url : 'J621/user/loginCheck',
				data : $('#myForm').serialize(),
				type : "GET",
				dataType : "json",
				async : false,//同步
				success : function(json) {
					alert(json.result);
					//alert("${pageContext.request.contextPath}");
					window.location.href= 'J621/user/dologin';
					
				}
			});
		});
	})
	


</script>

</head>
<body>

	<form id="myForm">

		用户名：<input type="text" name="username" value="admin" /><br>
		 密码：<input type="text" name="password" value="123" /> <br>
		<button id="submitBtn" >提交1</button>
	</form>


</body>
</html>
