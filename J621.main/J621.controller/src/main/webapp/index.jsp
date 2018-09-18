<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>下载</title>
</head>
<body>
	<form  method="get" action="<%=request.getContextPath()%>/J621/download">

		起始页：<input type="text" name="startIndex" value="1" /><br> 
		结束页：<input type="text" name="endIndex" value="1" /> <br> 
		关键字：<input type="text" name="key" value="cuntboy" /> <br> 
		最低评分：<input type="text" name="minScore" value="20" /> <br>
		本地根路径：<input type="text" name="localAddr" value="H:\\e621\\" /> <br> 
		起始文件名：<input type="text" name="startFileName" value="1" /> <br> 
		线程：<input type="text" name="threadPoolSize" value="8" /> <br> 
		<input type="submit" value="下载"><br>
	</form>
	<br>
</body>
</html>
