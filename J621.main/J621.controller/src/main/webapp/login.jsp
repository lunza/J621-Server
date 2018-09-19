<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>下载</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>

<script type="text/javascript">
$(function(){
    $('#submitBtn').click(function(){
    	
        $.ajax({
            url: 'J621/user/login',
					data : $('#myForm').serialize(),
					type : "GET",
					success:function(data){
						alert("111"+data);
					},
					error:function(data){
						alert("222"+data);
					}

				});
			});
		})
	</script>

</head>
<body>

	<form id="myForm" >

		用户名：<input type="text" name="username" value="admin" /><br>
		 密码：<input type="text" name="password" value="123" /> <br> 
			<button id = "submitBtn" type="submit" >提交1</button>
	</form>


</body>
</html>
