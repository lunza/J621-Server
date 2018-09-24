<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ include file="../common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>注册</title>
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
				url : 'J621/user/resign',
				data : $('#myForm').serialize(),
				type : "GET",
				dataType : "json",
				timeout : 100000, //超时时间设置，单位毫秒
				//async : false,//同步
				
				success : function(json) {

					if (json.result == "注册成功") {
						//layer.alert(json.result, {icon: 6});
						layer.msg(json.result, {
							time : 0 //不自动关闭
							,
							btn : [ '确认' ],
							area : [ '200px' ],
							yes : function(index) {
								event.preventDefault();
								layer.close(index);
								//alert("${pageContext.request.contextPath}");
								$(location).attr('href', 'login.jsp');
							}
						});

					} else {
						layer.msg(json.result, {
							time : 0 //不自动关闭
							,
							btn : [ '确认' ],
							area : [ '200px' ],
							yes : function(index) {
								event.preventDefault();
								layer.close(index);
							}
						});
					}

				}
			});
		});
	})
</script>

</head>
<body>



	<form id="myForm" class="form-horizontal">
		<div class="container">

			<div class="row" style="padding: 20px 0">
				<h3>注册</h3>

			</div>

			<div class="form-group">
				<label class="control-label col-lg-1" for="username">用户名</label>
				<div class="col-lg-5 col-md-6">
					<input class="form-control" name="username" type="text">

				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-lg-1" for="password">密码</label>
				<div class="col-lg-5 col-md-6">
					<input class="form-control" name="password" type="text">
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-lg-1" for="password2">确认密码</label>
				<div class="col-lg-5 col-md-6">
					<input class="form-control" name="password2" type="text">
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-lg-1" for="name">昵称</label>
				<div class="col-lg-5 col-md-6">
					<input class="form-control" name="name" type="text">
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-lg-1" for="email">邮箱</label>
				<div class="col-lg-5 col-md-6">
					<input class="form-control" name="email">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-lg-1" for="sex">性别</label>
				<div class="col-lg-5 col-md-6">
 
					<select
						class="form-control select2" style="width: 100%;"
						id="sex" name = sex>
						<option selected="selected" value='1'>男</option>
						<option value='0'>女</option>
					</select>
				</div>
			</div>

			<button class="btn btn-lg btn-primary btn-block" type="button"
				id='submitBtn' name="submit">注册</button>
		</div>
	</form>
</body>
</html>
