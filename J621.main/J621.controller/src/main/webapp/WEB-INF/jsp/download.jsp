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
				timeout : 100000, //超时时间设置，单位毫秒
				//async : false,//同步
				beforeSend : function() {
					var index = layer.msg('下载中。。。', {
						icon : 16,
						shade : 0.01,
						time : false
					});
				},
				success : function(json) {
					layer.closeAll();
					var index = layer.msg(json.result, {
						icon : 1,
						shade : 0.01,
						time : 1000
					});
				},
				error : function(json) {
					var index = layer.msg(json.result, {
						icon : 1,
						shade : 0.01,
						time : 1000
					});
					layer.closeAll();
				}
			});
		});
	})
</script>

</head>
<body>
	<div id="webName" align="right" style="margin-top: 0px">
		欢迎<%=webName%>!
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
					<input class="form-control" name="startIndex" value="1"  type="text">
				</div>
			</div>
			
			<div class="row form-group">
				<label class="control-label col-lg-1" for="endIndex">结束页</label>
				<div class="col-lg-5 col-md-6">
					<input class="form-control" name="endIndex" value="1" type="text">
				</div>
			</div>
			
			<div class="row form-group">
				<label class="control-label col-lg-1" for="key">关键字</label>
				<div class="col-lg-5 col-md-6">
					<input class="form-control" name="key" value="cuntboy"  type="text">
				</div>
			</div>
			
			<div class="row form-group">
				<label class="control-label col-lg-1" for="minScore">最低评分</label>
				<div class="col-lg-5 col-md-6">
					<input class="form-control" name="minScore" value="200"  type="text">
				</div>
			</div>
			
			<div class="row form-group">
				<label class="control-label col-lg-1" for="localAddr">本地路径</label>
				<div class="col-lg-5 col-md-6">
					<input class="form-control" name="localAddr" value="E:\\pics\\"  type="text">
				</div>
			</div>
			
			<div class="row form-group">
				<label class="control-label col-lg-1" for="threadPoolSize">线程</label>
				<div class="col-lg-5 col-md-6">
					<input class="form-control" name="threadPoolSize" value="8"  type="text" disabled>
				</div>
			</div>
			 <button class="btn btn-lg btn-primary btn-block" type="button" id = 'submitBtn'>色图来</button>
		</div>
	</form>
</body>
</html>
