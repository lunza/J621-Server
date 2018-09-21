<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String webName = request.getSession().getAttribute("webName").toString();
%>
<%
	String userId = request.getSession().getAttribute("userId").toString();
%>
<%
	String isOver = request.getSession().getAttribute("isOver").toString();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>下载</title>
<base href="<%=basePath%>">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>
<link rel="stylesheet" href= "<%=basePath%>css/main.css" type="text/css" />
<script type="text/javascript">

	$(function() {
		
		$('#submitBtn').click(function() {
			$.ajax({
				url : 'J621/service/download',
				data : $('#myForm').serialize(),
				type : "GET",
				dataType : "json",
				timeout : 100000, //超时时间设置，单位毫秒
				//async : false,//同步
				beforeSend : function() {
					$(".sk-double-bounce").show();
				}
			});
		});
		
		
	})
	
	
	
</script>



</head>
<body>
	<div id="webName" align="right" style="margin-top: 0px">
		欢迎
		<%=webName%>
		!
	</div>
	<form id="myForm">
		<input type="hidden" name="userId" value="<%=userId%>" /> 起始页：<input
			type="text" name="startIndex" value="1" /><br> 结束页：<input
			type="text" name="endIndex" value="1" /> <br> 关键字：<input
			type="text" name="key" value="cuntboy" /> <br> 最低评分：<input
			type="text" name="minScore" value="20" /> <br> 本地根路径：<input
			type="text" name="localAddr" value="H:\\pic\\" /> <br> 线程：<input
			type="text" name="threadPoolSize" value="8" /> <br>
		<button id="submitBtn">下载</button>
	</form>
	<br>


	<div class="sk-double-bounce">
		<div class="sk-child sk-double-bounce1">111</div>
		<div class="sk-child sk-double-bounce2"></div>
	</div>
</body>
</html>
