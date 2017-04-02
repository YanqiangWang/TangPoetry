<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<link href="//cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.css"
			rel="stylesheet">
		<script
			src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
		<script
			src="https://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</head>
	<body>
		<a href="${pageContext.request.contextPath}/PoetryPageServlet?currPoetryPage=1" class="list-group-item active">作品列表（${sessionScope.poetName}：共${sessionScope.totalPoetryNum}首）</a>
		${sessionScope.poetryListCode }
		<div style="text-align: center">
			<ul class="pagination">
				${sessionScope.poetryPageCode }
			</ul>
		</div>
	</body>
</html>