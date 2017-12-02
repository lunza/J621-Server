<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Servlet 上传文件</title>
</head>
<body>
	<form name="uploadForm" method="POST"
		action="<%=request.getContextPath()%>/J621/download">

		起始页：<input type="text" name="START_INDEX" value="1" /><br> 结束页：<input
			type="text" name="END_INDEX" value="1" /> <br> 关键字：<input
			type="text" name="KEY" value="cuntboy" /> <br> 最低评分：<input
			type="text" name="MIN_SCORE" value="20" /> <br> 本地根路径：<input
			type="text" name="LOCAL_ADDR" value="G:\\e621\\" /> <br> 起始文件名：<input
			type="text" name="START_FILE_NAME" value="1" /> <br> 线程：<input
			type="text" name="THREAD_POOL_SIZE" value="8" /> <br> <input
			type="submit" name="submit" value="下载"><br>
	</form>
	<br>
</body>
</html>
