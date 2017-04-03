<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
	<a
		href="${pageContext.request.contextPath}/PoetPage.action?currPoetPage=1"
		class="list-group-item active">诗人（共<s:property
			value="#session.totalPoetNum" />位）
	</a>

	<s:iterator value="#session.poetListNames" id="name" status="st">
		<a
			href="PoetList.action?poetItemId=
					<s:property value ='#st.count'/>"
			class="list-group-item"><s:property value="name" /></a>
	</s:iterator>

	<div style="text-align: center">
		<ul class="pagination">
			<s:if test="#session.poetNumListLength<=0">
				未查询到数据
			</s:if>
			<s:else>
				<s:if test="#session.poetLeft">
					<li><a
						href="PoetPage.action?currPoetPage=<s:property value='#session.currPoetPage-1'/>">&laquo;</a></li>
				</s:if>
				<s:else>
					<li class="disabled"><a href="#">&laquo;</a></li>
				</s:else>
				<s:iterator value="#session.poetNumList" id="num" status="st">
					<s:if test="#session.currPoetPage==#session.poetNumList[#st.index]">
						<li class="active"><a
							href="PoetPage.action?currPoetPage=<s:property value='#session.poetNumList[#st.index]'/>"><s:property
									value="#session.poetNumList[#st.index]" /></a></li>
					</s:if>
					<s:else>
						<li><a
							href="PoetPage.action?currPoetPage=<s:property value='#session.poetNumList[#st.index]'/>"><s:property
									value="#session.poetNumList[#st.index]" /></a></li>
					</s:else>
				</s:iterator>
				<s:if test="#session.poetRight">
					<li><a
						href="PoetPage.action?currPoetPage=<s:property value='#session.currPoetPage+1'/>">&raquo;</a></li>
				</s:if>
				<s:else>
					<li class="disabled"><a href="#">&raquo;</a></li>
				</s:else>
			</s:else>
		</ul>
	</div>
</body>
</html>