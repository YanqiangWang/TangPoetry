<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>全唐诗搜索网站</title>

		<!-- bootstrap -->
		<link href="//cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.css"
			rel="stylesheet">
		<script
			src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
		<script
			src="https://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script type="text/javascript">
		var basePath = "${pageContext.request.contextPath}";
		var str1 = "/SearchServlet?method=";
		var str2 = "&searchText=";
		function method_author(){
		location.href=basePath + str1 + "author" + str2+ document.getElementById("searchText").value;
		}
		function method_title(){
		location.href=basePath + str1 + "title" + str2 + document.getElementById("searchText").value;
		}
		function method_content(){
		location.href=basePath + str1 + "content" + str2 + document.getElementById("searchText").value;
		}
		</script>
	</head>
	<body>
		<div class="container">

			<!-- logo -->
			<div class="row clearfix">
				<div class="col-md-12 column">
					<img class="img-responsive center-block" alt="全唐诗搜索网站"
						src="${pageContext.request.contextPath}/images/logo.png"
						style="cursor: pointer"
						onclick="window.location.href='${pageContext.request.contextPath}/InitServlet'" />
				</div>
			</div>

			<!-- 搜索框及选项卡 -->
			<div class="row clearfix">
				<div class="col-md-12 column">
					<form class="bs-example bs-example-form" role="form">
						<div class="row">
							<!-- 占空位 -->
							<div class="col-lg-9">
							</div>
							<!-- 搜索框及选项卡 -->
							<div class="col-lg-3">
								<div class="input-group">
									<!-- 搜索框 -->
									<input type="text" class="form-control" id="searchText" />
									<!-- 选项卡 -->
									<div class="input-group-btn">
										<button type="button" class="btn btn-default dropdown-toggle"
											data-toggle="dropdown">
											查询
											<span class="caret"></span>
										</button>
										<ul class="dropdown-menu dropdown-menu-right"
											style="min-width: 100%">
											<li>
												<a style="cursor: pointer" onclick="method_author()">作者</a>
											</li>
											<li>
												<a style="cursor: pointer" onclick="method_title()">标题</a>
											</li>
											<li>
												<a style="cursor: pointer" onclick="method_content()">内容</a>
											</li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
			<p>
			<p>
				<!-- 网站主要部分 -->
			<div class="container">
				<div class="row clearfix">
					<!-- 诗人 -->
					<div class="col-md-2 column">
						<jsp:include page="poet.jsp"></jsp:include>
					</div>
					<!-- 诗题 -->
					<div class="col-md-3 column">
						<jsp:include page="poetry.jsp"></jsp:include>
					</div>
					<!-- 诗 -->
					<div class="col-md-7 column">
						<iframe frameborder="1" name="content"
							src="${pageContext.request.contextPath}/content.jsp"
							height="500px" style="border-radius: 5px" width="100%"></iframe>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>