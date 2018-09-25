<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%
	String os = System.getProperty("os.name");
	String path = request.getContextPath();
	if (os.toLowerCase().startsWith("linux")) {
		path = "/apphost" + path;
	}

	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" lang="zh-CN">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<base href="<%=basePath%>">
<link rel="stylesheet" href="<%=basePath%>static/css/button.css"
	type="text/css" />
<link rel="stylesheet" href="<%=basePath%>static/css/main.css"
	type="text/css" />
<!-- Bootstrap -->
<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="<%=basePath%>static/layer/layer.js"></script>
<script src="<%=basePath%>static/js/main.js"></script>

</head>
<body>

	<div id="footer" class="container" style="text-align:center;">
		<nav class="navbar navbar-default navbar-fixed-bottom">
		<div class="navbar-inner navbar-content-center">
			<p class="text-muted credit" style="padding: 10px;">开发者：轮砸，狗头鱼，香蕉</p>
		</div>

		</nav>
	</div>
</body>
</html>