<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="//cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.css"
	rel="stylesheet">
</head>
<body>
	<div class="jumbotron">
		<s:if test="#session.isContentNotEmpty">
			<div style="text-align:center">
				<h2>
					<s:property value="#session.poetryName" />
				</h2>
				<h3>
					<s:property value="#session.poetName" />
				</h3>
				<s:property value="#session.poetryContent" escape="false" />
			</div>
		</s:if>
		<s:else>
			<h2>欢迎使用全唐诗搜索网站！</h2>
		本网站功能如下：<br />
			<ol>
				<li>依次点击左侧诗人、作品列表，可查阅相应的唐诗；</li>
				<li>在搜索框中输入您想查询的内容，可查询相应的信息:
					<ul>
						<li>通过作者搜索该作者所有的作品，</li>
						<li>通过诗词名称搜索唐诗全文并显示，</li>
						<li>通过诗歌的名句搜索该唐诗的作者、题目和全文；</li>
					</ul>
				</li>
				<li>点击列表标题栏可以返回列表首页，点击网站logo可回到主页面。</li>
			</ol>
		</s:else>
	</div>
</body>
</html>