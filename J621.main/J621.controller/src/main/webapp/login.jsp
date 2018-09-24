<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ include file="../common.jsp" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>登录</title>

<style type="text/css">
body {
  padding-top: 40px;
  padding-bottom: 40px;
  background-color: #eee;
}
</style>

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

					if(json.result=="登录成功"){
						//layer.alert(json.result, {icon: 6});
						layer.msg(json.result, {
							  time: 0 //不自动关闭
							  ,btn: ['确认']
						      ,area:['200px']
							  ,yes: function(index){
							    event.preventDefault();
							    layer.close(index);
								//alert("${pageContext.request.contextPath}");
								$(location).attr('href','<%=basePath%>J621/user/dologin?userId='+json.userId);
							  }
							});
						
					}else{
						layer.msg(json.result, {
							  time: 0 //不自动关闭
							  ,btn: ['确认']
						      ,area:['200px']
							  ,yes: function(index){
							    event.preventDefault();
							    layer.close(index);
							  }
							});
					}
					
				}
			});
		});
		return false;
	})
	


</script>

</head>
<body>
<div class="container">
 
      <form id = 'myForm' class="form-signin">
        <h2 class="form-signin-heading">欢迎使用下载器</h2>
        <label for="inputEmail" class="sr-only">账号</label>
        <input name = 'username' type="text" id="inputEmail" class="form-control" placeholder="请输入用户名" required autofocus>
        <label for="inputPassword" class="sr-only">密码</label>
        <input name = 'password' type="password" id="inputPassword" class="form-control" placeholder="请输入密码" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="button" id = 'submitBtn'>登录</button>
      </form>

    </div> <!-- /container -->
	


</body>
</html>
