<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ include file="/common.jsp"%>

<%
	String webName = request.getSession().getAttribute("webName").toString();
%>
<%
	String userId = request.getSession().getAttribute("userId").toString();
%>
<%
	String isOver = request.getSession().getAttribute("isOver").toString();
%>
<%
	String picCount = request.getSession().getAttribute("picCount").toString();
%>
<%
	String maxCount = request.getSession().getAttribute("maxCount").toString();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>下载</title>
<style type="text/css">
body {
	padding-top: 10px;
	padding-bottom: 40px;
	background-color: #eee;
}
</style>

<script type="text/javascript">
	$(function() {

		$('#submitBtn').click(function() {
			$.ajax({
				url : 'J621/service/download',
				data : $('#myForm').serialize(),
				type : "GET",
				dataType : "json",
				timeout : 3600000, //超时时间设置，单位毫秒
				//async : false,//同步
				beforeSend : function() {
					$('hidden_div').css('display', 'block');

					var index = layer.msg('下载中。。。', {
						icon : 16,
						shade : 0.01,
						time : false
					});
				},
				success : function(json) {
					$('hidden_div').css('display', 'none');
					if(json.result=="下载成功"){
						
					layer.closeAll();
					var index = layer.msg(json.result, {
						icon : 1,
						shade : 0.01,
						time : 1000
					});
					
					
					document.write("<form action=<%=basePath%>J621/service/downloadZipFile method=post name=form1 style='display:none'>");  
					
					document.write("<input type='hidden' name='filePath' value='"+ json.srcFile +"'/>");  
					document.write("</form>");  
					document.form1.submit();
					
					}else{
						layer.closeAll();
						var index = layer.msg(json.result, {
							icon : 2,
							shade : 0.01,
							time : 2000
						});
					}
				},
				error : function(json) {
					var index = layer.msg(json.result, {
						icon : 1,
						shade : 0.01,
						time : 2000
					});
					layer.closeAll();
				}
			});
		});
	})
</script>

</head>
<body>
<div class="nav navbar-nav navbar-left hidden-sm">
    <h5>当前已下载<span class="label label-danger"><%=picCount%></span>张图片</h5><br>
     <h5>共可下载<span class="label label-danger"><%=maxCount%></span>张图片</h5>
</div>
<div class="nav navbar-nav navbar-right hidden-sm">
    <h5>欢迎<%=webName%>!&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h5>
</div>

	
	<form id="myForm" class="form-horizontal">
		<div class="container">

			<div class="row" style="padding: 20px 0">
				<h3>输入参数</h3>
				<input type="hidden" name="userId" value="<%=userId%>" />
			</div>

			<div class="row form-group">
				<label class="control-label col-lg-1" for="startIndex">起始页</label>
				<div class="col-lg-5 col-md-6">
					<input class="form-control" name="startIndex" value="1" type="text">
				</div>
			</div>

			<div class="row form-group">
				<label class="control-label col-lg-1" for="endIndex">结束页</label>
				<div class="col-lg-5 col-md-6">
					<input class="form-control" name="endIndex" value="1" type="text">
				</div>
			</div>

			<div class="row form-group">
				<label class="control-label col-lg-1" for="keyses">关键字</label>
				<div class="col-lg-5 col-md-6">
					<input class="form-control" name="keyses" value="shaorune"
						type="text">
				</div>
			</div>

			<div class="row form-group">
				<label class="control-label col-lg-1" for="minScore">最低评分</label>
				<div class="col-lg-5 col-md-6">
					<input class="form-control" name="minScore" value="200" type="text">
				</div>
			</div>

			<div class="row form-group">
				<label class="control-label col-lg-1" for="threadPoolSize">线程</label>
				<div class="col-lg-5 col-md-6">
					<input class="form-control" name="threadPoolSize" value="8"
						type="text" readonly unselectable="on">
				</div>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="button"
				id='submitBtn'>开始下载</button>
				
			<label class="control-label" style="color:red">注： ① 每个账号下载过的图片不会再次下载</label><br>
			<label class="control-label" style="color:red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;② 当起始页等于结束页时 下载一页图片</label><br>
			<label class="control-label" style="color:red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;③ 支持多关键字下载，关键字之间以空格分隔</label><br>
		</div>
	</form>
	
	<div class="hidden_div" id="hiddenDivId"></div>

</body>
</html>
